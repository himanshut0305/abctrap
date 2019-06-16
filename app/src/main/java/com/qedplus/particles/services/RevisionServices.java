package com.qedplus.particles.services;

import android.content.Context;
import android.util.Log;

import com.qedplus.particles.dao.ChapterDao;
import com.qedplus.particles.dao.ConfirmInteractionDao;
import com.qedplus.particles.dao.DiagramDao;
import com.qedplus.particles.dao.MCQOptionDao;
import com.qedplus.particles.dao.MultipleChoiceQuestionDao;
import com.qedplus.particles.dao.OneWordAnswerQuestionDao;
import com.qedplus.particles.dao.RevealInteractionDao;
import com.qedplus.particles.dao.RevisionPointDao;
import com.qedplus.particles.dao.RevisionSlideDao;
import com.qedplus.particles.dao.SubjectDao;
import com.qedplus.particles.dao.TopicDao;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.ConfirmInteraction;
import com.qedplus.particles.db.Diagram;
import com.qedplus.particles.db.MCQOption;
import com.qedplus.particles.db.MultipleChoiceQuestion;
import com.qedplus.particles.db.OneWordAnswerQuestion;
import com.qedplus.particles.db.RevealInteraction;
import com.qedplus.particles.db.RevisionPoint;
import com.qedplus.particles.db.RevisionSlide;
import com.qedplus.particles.db.Subject;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.extras.AppDatabase;
import com.qedplus.particles.extras.MCQType;
import com.qedplus.particles.extras.SlideType;
import com.qedplus.particles.model.ConfirmInteractionBO;
import com.qedplus.particles.model.DiagramBO;
import com.qedplus.particles.model.MCQOptionBO;
import com.qedplus.particles.model.MultipleChoiceQuestionBO;
import com.qedplus.particles.model.OneWordAnswerQuestionBO;
import com.qedplus.particles.model.RevealInteractionBO;
import com.qedplus.particles.model.RevisionPointBO;
import com.qedplus.particles.model.RevisionSlideBO;

import java.security.acl.LastOwnerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class RevisionServices {
    public interface UITask {
        void task(ArrayList<RevisionSlideBO> revisionSlideBOS);
    }

    private UITask uiTask;

    public RevisionServices(UITask uiTask) {
        this.uiTask = uiTask;
    }

    public void getSlidesForTopic(final Context cxt, final String subjectName, final int schoolYear, final int chapterIndex, final int topicIndex){
        Log.e("SubjectName", subjectName);
        Log.e("SchoolYearResponse", schoolYear + "");
        Log.e("ChapterIndex", chapterIndex + "");
        Log.e("TopicIndex", topicIndex + "");

        DBService dbService = new DBService(new DBService.BackgroundDBTask() {
            ArrayList<RevisionSlideBO> revisionSlideBOS = new ArrayList<>();

            @Override
            public void task() {
                AppDatabase db = AppDatabase.getAppDatabase(cxt);

                SubjectDao subjectDao = db.subjectDao();
                ChapterDao chapterDao = db.chapterDao();
                TopicDao topicDao = db.topicDao();

                Subject subject = subjectDao.getByNameAndYear(subjectName, schoolYear);
                Chapter chapter = chapterDao.getByIndexAndSubject(chapterIndex, subject.getSubjectId());
                Topic topic = topicDao.getByChapterAndIndex(topicIndex, chapter.getChapterId());

                Log.e("GotSubject", subject.getName());
                Log.e("GotChapter", chapter.getName());
                Log.e("GotTopic", topic.getName());

                int topicId = topic.getTopicId();

                RevisionSlideDao revisionSlideDao = db.revisionSlideDao();

                RevisionPointDao revisionPointDao = db.revisionPointDao();
                DiagramDao diagramDao = db.diagramDao();
                RevealInteractionDao revealInteractionDao = db.revealInteractionDao();
                ConfirmInteractionDao confirmInteractionDao = db.confirmInteractionDao();

                MultipleChoiceQuestionDao multipleChoiceQuestionDao = db.multipleChoiceQuestionDao();
                OneWordAnswerQuestionDao oneWordAnswerQuestionDao = db.oneWordAnswerQuestionDao();
                MCQOptionDao mcqOptionDao = db.mcqOptionDao();

                ArrayList<RevisionSlide> revisionSlides = (ArrayList<RevisionSlide>) revisionSlideDao.findAllByTopic(topicId);
                Collections.sort(revisionSlides, new Comparator<RevisionSlide>() {
                    @Override
                    public int compare(RevisionSlide o1, RevisionSlide o2) {
                        return o1.getSlideIndex() - o2.getSlideIndex();
                    }
                });

                Log.e("NORS : ", revisionSlides.size() + " Slides were in db");
                Log.e("RevisionSlides", revisionSlides.toString());

                for(RevisionSlide slide : revisionSlides) {
                    Log.e("RevisionServices", "THE SLIDE : " + slide.toString());

                    if(slide.getSlideType().equals(SlideType.REVISION_POINT)) {
                        Log.e("RevisionServices", "TYPE : " + slide.getSlideType());

                        if(slide.getRevisionPoint1Id() != null) {
                            Log.e("RevisionServices", "Has RP 1");

                            RevisionPoint point1 = revisionPointDao.findById(slide.getRevisionPoint1Id());
                            RevisionPointBO point1BO = new RevisionPointBO(point1.getPreface(), point1.getExplanation());

                            LinkedHashSet<RevisionPointBO> l = new LinkedHashSet<>();
                            l.add(point1BO);

                            boolean hasSecondPart = false;
                            if(slide.getRevisionPoint2Id() != null) {
                                Log.e("RevisionServices", "Has RP 2");

                                RevisionPoint point2 = revisionPointDao.findById(slide.getRevisionPoint2Id());
                                RevisionPointBO point2BO = new RevisionPointBO(point2.getPreface(), point2.getExplanation());

                                l.add(point2BO);

                                try {
                                    revisionSlideBOS.add(new RevisionSlideBO(l));
                                    hasSecondPart = true;
                                }
                                catch (RevisionSlideBO.BadSlideException e) {
                                    Log.e("Exception", e.getMessage());
                                    e.printStackTrace();
                                }
                            }
                            else if (slide.getConfirmInteractionId() != null) {
                                Log.e("RevisionServices", "Has Confirm Interaction");

                                ConfirmInteraction ci = confirmInteractionDao.findById(slide.getConfirmInteractionId());
                                ConfirmInteractionBO ciBO = new ConfirmInteractionBO(ci.getQuery(), ci.getAffirmativeResponse(), ci.getNegativeResponse());
                                revisionSlideBOS.add(new RevisionSlideBO(l, ciBO));

                                hasSecondPart = true;
                            }
                            else if (slide.getRevealInteractionId() != null) {
                                Log.e("RevisionServices", "Has Reveal Interaction");

                                RevealInteraction ri = revealInteractionDao.findById(slide.getRevealInteractionId());
                                RevealInteractionBO riBO = new RevealInteractionBO(ri.getQuery(), ri.getAnswer());
                                revisionSlideBOS.add(new RevisionSlideBO(l, riBO));

                                hasSecondPart = true;
                            }
                            else if (slide.getDiagramId() != null) {
                                Log.e("RevisionServices", "Has Diagram");

                                Diagram d = diagramDao.findById(slide.getDiagramId());
                                DiagramBO dBO = new DiagramBO(d.getUri(), d.getPostface());
                                revisionSlideBOS.add(new RevisionSlideBO(l, dBO));

                                hasSecondPart = true;
                            }

                            if(!hasSecondPart) {
                                try {
                                    revisionSlideBOS.add(new RevisionSlideBO(l));
                                }
                                catch (RevisionSlideBO.BadSlideException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        else {
                            RevisionPointBO rpbo = new RevisionPointBO("", "");
                            LinkedHashSet<RevisionPointBO> l = new LinkedHashSet<>();
                            l.add(rpbo);

                            if (slide.getConfirmInteractionId() != null) {
                                Log.e("RevisionServices", "Has Confirm Interaction");

                                ConfirmInteraction ci = confirmInteractionDao.findById(slide.getConfirmInteractionId());
                                ConfirmInteractionBO ciBO = new ConfirmInteractionBO(ci.getQuery(), ci.getAffirmativeResponse(), ci.getNegativeResponse());
                                revisionSlideBOS.add(new RevisionSlideBO(l, ciBO));
                            }
                            else if (slide.getRevealInteractionId() != null) {
                                Log.e("RevisionServices", "Has Reveal Interaction");

                                RevealInteraction ri = revealInteractionDao.findById(slide.getRevealInteractionId());
                                RevealInteractionBO riBO = new RevealInteractionBO(ri.getQuery(), ri.getAnswer());
                                revisionSlideBOS.add(new RevisionSlideBO(l, riBO));
                            }
                            else if (slide.getDiagramId() != null) {
                                Log.e("RevisionServices", "Has Diagram");

                                Diagram d = diagramDao.findById(slide.getDiagramId());
                                DiagramBO dBO = new DiagramBO(d.getUri(), d.getPostface());
                                revisionSlideBOS.add(new RevisionSlideBO(l, dBO));
                            }
                        }
                    }
                    else if(slide.getSlideType().equals(SlideType.REVISION_QUESTION)) {
                        if(slide.getMcqId() != null) {
                            MultipleChoiceQuestion mcq = multipleChoiceQuestionDao.findById(slide.getMcqId());
                            ArrayList<MCQOption> mcqOptions = (ArrayList<MCQOption>) mcqOptionDao.findAllByQuestion(mcq.getMultipleChoiceQuestionId());
                            HashSet<MCQOptionBO> mcqOptionBOS = new HashSet<>();

                            for(MCQOption option : mcqOptions) {
                                MCQOptionBO mcqOptionBO = new MCQOptionBO(option.getOption(), option.getCorrect(), option.getExplanation());
                                mcqOptionBOS.add(mcqOptionBO);
                            }

                            MultipleChoiceQuestionBO mcqBO = new MultipleChoiceQuestionBO(mcq.getQuestion(), mcqOptionBOS, mcq.getExplanation(), mcq.getMcqType(), mcq.getInstruction(), mcq.getLevel(), mcq.getCategory());
                            mcqBO.setInteractive(slide.isInteractive());
                            mcqBO.setAlternateDisplay(mcq.isAlternateDisplay());

                            if(mcq.getDiagramId() != null) {
                                Diagram quesDiagram = diagramDao.findById(mcq.getDiagramId());
                                DiagramBO quesDiagramBO = new DiagramBO(quesDiagram.getUri(), quesDiagram.getPostface());
                                mcqBO.setDiagram(quesDiagramBO);

                                Log.e("Getting DIg : ", quesDiagram.getUri());
                            }

                            revisionSlideBOS.add(new RevisionSlideBO(mcqBO));
                        }
                        else if (slide.getOwaqId() != null) {
                            OneWordAnswerQuestion owaq = oneWordAnswerQuestionDao.findById(slide.getOwaqId());
                            HashSet<String> acceptableAnswers = new HashSet<>(Arrays.asList(owaq.getAcceptableAnswers().split("###")));

                            OneWordAnswerQuestionBO owaqBO = new OneWordAnswerQuestionBO(owaq.getQuestion(), acceptableAnswers, owaq.getInstruction(), owaq.getExplanation(), owaq.getLevel(), owaq.getCategory());
                            revisionSlideBOS.add(new RevisionSlideBO(owaqBO));
                        }
                        else {
                            Log.e("QED_Error", "The Slide is Revision Question Type but is neither MCQ or OWAQ");
                        }
                    }
                }

                Log.e("NORS : ", revisionSlideBOS.size() + " Slides retrieved");
            }

            @Override
            public void afterTask() {
                uiTask.task(revisionSlideBOS);
            }
        });

        dbService.execute();
    }
}
