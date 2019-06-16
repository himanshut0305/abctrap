package com.qedplus.particles.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particles.dao.ChapterDao;
import com.qedplus.particles.dao.MCQOptionDao;
import com.qedplus.particles.dao.MultipleChoiceQuestionDao;
import com.qedplus.particles.dao.OneWordAnswerQuestionDao;
import com.qedplus.particles.dao.QPTDao;
import com.qedplus.particles.dao.SubjectDao;
import com.qedplus.particles.dao.TopicDao;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.MCQOption;
import com.qedplus.particles.db.MultipleChoiceQuestion;
import com.qedplus.particles.db.OneWordAnswerQuestion;
import com.qedplus.particles.db.QPT;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.model.MCQOptionBO;
import com.qedplus.particles.model.MultipleChoiceQuestionBO;
import com.qedplus.particles.model.OneWordAnswerQuestionBO;
import com.qedplus.particles.model.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class PracticeServices {
    public interface UITask {
        void task(HashSet<Question> questions, int toAsk);
    }

    private PracticeServices.UITask uiTask;

    public  PracticeServices(PracticeServices.UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void getQuestionsByTopic(final Context cxt, final String subjectName, final int schoolYear, final int chapterIndex, final int topicIndex, final int qptIndex) {
        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            HashSet<Question> questions = new HashSet<>();
            int toAsk = 5;
            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);

                SubjectDao subjectDao = db.subjectDao();
                ChapterDao chapterDao = db.chapterDao();
                TopicDao topicDao = db.topicDao();
                QPTDao qptDao = db.qptDao();

                Subject subject = subjectDao.getByNameAndYear(subjectName, schoolYear);
                Chapter chapter = chapterDao.getByIndexAndSubject(chapterIndex, subject.getSubjectId());
                ArrayList<Integer> topicIds = new ArrayList<>();

                if(qptIndex > 0) {
                    QPT qpt = qptDao.getByIndexAndChapter(qptIndex, chapter.getChapterId());
                    ArrayList<Topic> qptTopics = (ArrayList<Topic>) qptDao.getAllTopics(qpt.getQptId());

                    for(Topic topic : qptTopics) {
                        topicIds.add(topic.getTopicId());
                    }

                    toAsk = qpt.getNumOfQues();
                }
                else {
                    Topic topic = topicDao.getByChapterAndIndex(topicIndex, chapter.getChapterId());
                    topicIds.add(topic.getTopicId());
                }

                MultipleChoiceQuestionDao mcqDao = db.multipleChoiceQuestionDao();
                OneWordAnswerQuestionDao owaqDao = db.oneWordAnswerQuestionDao();
                MCQOptionDao mcqOptionDao = db.mcqOptionDao();

                for(int topicId : topicIds) {
                    ArrayList<MultipleChoiceQuestion> mcqs = (ArrayList<MultipleChoiceQuestion>) mcqDao.findAllByTopic(topicId);
                    HashSet<MultipleChoiceQuestionBO> mcqBOs = new HashSet<>();

                    for (MultipleChoiceQuestion mcq : mcqs) {
                        ArrayList<MCQOption> mcqOptions = (ArrayList<MCQOption>) mcqOptionDao.findAllByQuestion(mcq.getMultipleChoiceQuestionId());
                        HashSet<MCQOptionBO> mcqOptionBOS = new HashSet<>();

                        for (MCQOption option : mcqOptions) {
                            MCQOptionBO mcqOptionBO = new MCQOptionBO(option.getOption(), option.getCorrect(), option.getExplanation());
                            mcqOptionBOS.add(mcqOptionBO);
                        }

                        MultipleChoiceQuestionBO mcqBO = new MultipleChoiceQuestionBO(mcq.getQuestion(), mcqOptionBOS, mcq.getExplanation(), mcq.getMcqType(), mcq.getInstruction(), mcq.getLevel(), mcq.getCategory());
                        mcqBOs.add(mcqBO);

                        Log.e("mcq", mcq.toString());
                    }

                    questions.addAll(mcqBOs);

                    ArrayList<OneWordAnswerQuestion> owaqs = (ArrayList<OneWordAnswerQuestion>) owaqDao.findAllByTopic(topicId);
                    HashSet<OneWordAnswerQuestionBO> owaqBOs = new HashSet<>();

                    for (OneWordAnswerQuestion owaq : owaqs) {
                        HashSet<String> acceptableAnswers = new HashSet<>(Arrays.asList(owaq.getAcceptableAnswers().split("###")));

                        OneWordAnswerQuestionBO owaqBO = new OneWordAnswerQuestionBO(owaq.getQuestion(), acceptableAnswers, owaq.getInstruction(), owaq.getExplanation(), owaq.getLevel(), owaq.getCategory());
                        owaqBOs.add(owaqBO);


                        Log.e("owaq", owaq.toString());
                    }

                    questions.addAll(owaqBOs);
                }
            }

            @Override
            public void afterTask() {
                uiTask.task(questions, toAsk);
            }
        });

        dbService.execute();
    }
}
