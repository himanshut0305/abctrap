package com.qedplus.particles.extras;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteConstraintException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qedplus.particles.R;
import com.qedplus.particles.dao.BookDao;
import com.qedplus.particles.dao.ChapterDao;
import com.qedplus.particles.dao.QPTDao;
import com.qedplus.particles.dao.RevisionSlideDao;
import com.qedplus.particles.dao.SchoolClassDao;
import com.qedplus.particles.dao.TopicDao;
import com.qedplus.particles.db.Book;
import com.qedplus.particles.db.Chapter;
import com.qedplus.particles.db.ConfirmInteraction;
import com.qedplus.particles.db.Diagram;
import com.qedplus.particles.db.MCQOption;
import com.qedplus.particles.db.MultipleChoiceQuestion;
import com.qedplus.particles.db.QPT;
import com.qedplus.particles.db.RevealInteraction;
import com.qedplus.particles.db.RevisionPoint;
import com.qedplus.particles.db.RevisionSlide;
import com.qedplus.particles.db.SchoolClass;
import com.qedplus.particles.db.Topic;
import com.qedplus.particles.db.User;
import com.qedplus.particles.model.SchoolBO;
import com.qedplus.particles.payload.BookResponse;
import com.qedplus.particles.payload.ChapterResponse;
import com.qedplus.particles.payload.OptionResponse;
import com.qedplus.particles.payload.QuestionResponse;
import com.qedplus.particles.payload.SlideResponse;
import com.qedplus.particles.payload.TopicResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

public abstract class Utility {
    public static class Theme {
        public int theme;
        public int altTheme;
        public int blackTheme;
        public int primaryColor;
        public int primaryDarkColor;
        public int loginBackground;
        public int gradientBackground;
        public int cardGradient;
        public int progressBarBackground;
    }

    public static Theme getTheme(Context cxt) {
        PrefManager prefManager = new PrefManager(cxt);
        SchoolBO schoolBO = prefManager.getSchoolDetails();
        Theme t = new Theme();

        switch (schoolBO.getTheme()) {
            case "RED" :
                t.theme = R.style.RedOnRedAppTheme;
                t.altTheme = R.style.WhiteAndRedAppTheme;
                t.blackTheme = R.style.BlackOnRedAppTheme;
                t.primaryColor = cxt.getResources().getColor(R.color.red);
                t.primaryDarkColor = cxt.getResources().getColor(R.color.darkred);
                t.loginBackground = R.drawable.login_background_red;
                t.gradientBackground = R.drawable.gradient_background_red;
                t.cardGradient = R.drawable.gradient_red_on_red;
                t.progressBarBackground = R.drawable.progress_bar_gradient_red;
                break;
            case "GREEN" :
                t.theme = R.style.GreenOnGreenAppTheme;
                t.altTheme = R.style.WhiteAndGreenAppTheme;
                t.blackTheme = R.style.BlackOnGreenAppTheme;
                t.primaryColor = cxt.getResources().getColor(R.color.green);
                t.primaryDarkColor = cxt.getResources().getColor(R.color.darkgreen);
                t.loginBackground = R.drawable.login_background_green;
                t.gradientBackground = R.drawable.gradient_background_green;
                t.cardGradient = R.drawable.gradient_green_on_green;
                t.progressBarBackground = R.drawable.progress_bar_gradient_green;
                break;
            case "BLUE" :
                t.theme = R.style.BlueOnBlueAppTheme;
                t.altTheme = R.style.WhiteAndBlueAppTheme;
                t.blackTheme = R.style.BlackOnBlueAppTheme;
                t.primaryColor = cxt.getResources().getColor(R.color.blue);
                t.primaryDarkColor = cxt.getResources().getColor(R.color.darkblue);
                t.loginBackground = R.drawable.login_background_blue;
                t.gradientBackground = R.drawable.gradient_background_blue;
                t.cardGradient = R.drawable.gradient_blue_on_blue;
                t.progressBarBackground = R.drawable.progress_bar_gradient_blue;
                break;
            case "INDIGO" :
                t.theme = R.style.IndigoOnIndigoAppTheme;
                t.altTheme = R.style.WhiteAndIndigoAppTheme;
                t.blackTheme = R.style.BlackOnIndigoAppTheme;
                t.primaryColor = cxt.getResources().getColor(R.color.indigo);
                t.primaryDarkColor = cxt.getResources().getColor(R.color.darkindigo);
                t.loginBackground = R.drawable.login_background_indigo;
                t.gradientBackground = R.drawable.gradient_background_indigo;
                t.cardGradient = R.drawable.gradient_indigo_on_indigo;
                t.progressBarBackground = R.drawable.progress_bar_gradient_indigo;
                break;
            case "ORANGE" :
                t.theme = R.style.OrangeOnOrangeAppTheme;
                t.altTheme = R.style.WhiteAndOrangeAppTheme;
                t.blackTheme = R.style.BlackOnRedAppTheme;
                t.primaryColor = cxt.getResources().getColor(R.color.orange);
                t.primaryDarkColor = cxt.getResources().getColor(R.color.darkorange);
                t.loginBackground = R.drawable.login_background_orange;
                t.gradientBackground = R.drawable.gradient_background_orange;
                t.cardGradient = R.drawable.gradient_orange_on_orange;
                t.progressBarBackground = R.drawable.progress_bar_gradient_orange;
                break;
            default:
                t.theme = R.style.BlackAndWhiteAppTheme;
                t.primaryColor = cxt.getResources().getColor(R.color.black);
                t.primaryDarkColor = cxt.getResources().getColor(R.color.soot);
                t.loginBackground = R.drawable.login_background_red;
                t.gradientBackground = R.drawable.gradient_background_red;
                t.cardGradient = R.drawable.gradient_red_on_red;
        }

        return  t;
    }

    public static LinkedHashMap<String, ArrayList<String>> getSubjects() {
        LinkedHashMap<String, ArrayList<String>> subjectList = new LinkedHashMap<>();

        ArrayList<String> science = new ArrayList<String>();
        science.add("Physics");
        science.add("Chemistry");
        science.add("Biology");
        science.add("Environmental Science");

        ArrayList<String> socialScience = new ArrayList<>();
        socialScience.add("History");
        socialScience.add("Civics");
        socialScience.add("Geography");
        socialScience.add("Economics");

        ArrayList<String> english = new ArrayList<>();
        english.add("Prose");
        english.add("Poetry");
        english.add("Drama");
        english.add("Grammar");

        ArrayList<String> other = new ArrayList<>();
        other.add("Mathematics");
        other.add("Hindi");
        other.add("Information Technology");

        subjectList.put("Science", science);
        subjectList.put("Social Science", socialScience);
        subjectList.put("English Literature and Grammar", english);
        subjectList.put("Other", other);

        return subjectList;
    }

    public static String getProperty(String key, Context context) {
        try {
            Properties properties = new Properties();;
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("app.properties");
            properties.load(inputStream);
            return properties.getProperty(key);
        }
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static File getImageFromURL(String url, URL fallbackURL, String fileName) {
        fileName = fileName.replace(" - ", "_");
        fileName = fileName.replace(" ", "_").toLowerCase();

        if (url.contains("localhost")) {
            url = url.replace("localhost", fallbackURL.getHost());
        }

        try {
            java.net.URL downloadUrl = new java.net.URL(url);
            HttpURLConnection connection = (HttpURLConnection) downloadUrl.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/qedplus");
            if (!myDir.exists()) {
                myDir.mkdirs();
            }

            String fname = "diagram_" + fileName +".png";
            File file = new File (myDir, fname);

            if (file.exists ())
                file.delete ();

            FileOutputStream out = new FileOutputStream(file);
            myBitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();

            return file;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<Book> getBooksForCurrentUser(Context context, User user) {
        AppDatabase db = AppDatabase.getAppDatabase(context);
        BookDao bookDao = db.bookDao();
        SchoolClassDao schoolClassDao = db.schoolClassDao();

        SchoolClass schoolClass = schoolClassDao.findById(user.getSchoolClassId());
        List<Book> books = bookDao.findBySchoolYear(schoolClass.getSchoolYear());
        return books;
    }

    public static Book getBookForSubject(Context context, String schoolCode, String subjectName, int year) {
        //Log.e("Flow :", "getBookForSubject");
        try {
            URL url = new URL(getProperty("IP", context) + "/api/get/bookBySubjectSchoolYear");

            JSONObject params = new JSONObject();
            params.put("subjectName", subjectName);
            params.put("schoolCode", schoolCode);
            params.put("year", year);

            HttpURLConnection con = getAuthorisedConnection(context, url, params);
            con.connect();

            int responseCode = con.getResponseCode();
            //Log.e("Response Code :", responseCode + "");

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                //Log.e("Response :", response.toString());
                BookResponse bookResponse = new ObjectMapper().readValue(response.toString(), BookResponse.class);

                if(bookResponse != null) {
                    Log.e("BookResponse :", bookResponse.toString());
                }
                else {
                    Log.e("BookResponse :", "No Book Found");
                }
            }
        }
        catch (IOException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (JSONException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (SQLiteConstraintException e) {
            Log.e("Error :", e.getMessage());
        }

        return null;
    }

    public static List<Chapter> getChaptersForBook(Context context, String bookName) {
        //Log.e("Flow :", "getChaptersForBook");
        AppDatabase db = AppDatabase.getAppDatabase(context);

        try {
            URL url = new URL(getProperty("IP", context) + "/api/get/bookChaptersByBookName");

            JSONObject params = new JSONObject();
            params.put("bookName", bookName);

            HttpURLConnection con = getAuthorisedConnection(context, url, params);
            con.connect();

            int responseCode = con.getResponseCode();
            //Log.e("Response Code :", responseCode + "");

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                //Log.e("Response :", response.toString());
                List<ChapterResponse> chapterResponses = Arrays.asList(new ObjectMapper().readValue(response.toString(), ChapterResponse[].class));

                Book book = db.bookDao().findByName(bookName);
                ChapterDao chapterDao = db.chapterDao();

                Collections.sort(chapterResponses, new Comparator<ChapterResponse>() {
                    @Override
                    public int compare(ChapterResponse o1, ChapterResponse o2) {
                        return o1.getChapterIndex() - o2.getChapterIndex();
                    }
                });

                for (ChapterResponse chapterResponse : chapterResponses) {
                    Chapter chapter = new Chapter(chapterResponse.getName(), chapterResponse.getChapterIndex(), book.getSubjectId(), chapterResponse.getVersion());
                    try {
                        chapterDao.insertAll(chapter);
                    }
                    catch (SQLiteConstraintException e) {
                        Log.e("InsertEx :", chapter.getName() + " " + chapter.getChapterIndex() + " " + e.getMessage());
                    }
                }

                return chapterDao.findAllBySubject(book.getSubjectId());
            }
        }
        catch (IOException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (JSONException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (SQLiteConstraintException e) {
            Log.e("Error :", e.getMessage());
        }

        return null;
    }

    public static List<Topic> getTopicsForChapter(Context context, String bookName, Chapter chapter) {
        AppDatabase db = AppDatabase.getAppDatabase(context);

        try {
            URL url = new URL(getProperty("IP", context) + "/api/get/bookTopicsByBookAndChapterName");

            JSONObject params = new JSONObject();
            params.put("bookName", bookName);
            params.put("chapterName", chapter.getName());

            HttpURLConnection con = getAuthorisedConnection(context, url, params);
            con.connect();

            int responseCode = con.getResponseCode();

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                List<TopicResponse> topicResponses = Arrays.asList(new ObjectMapper().readValue(response.toString(), TopicResponse[].class));
                TopicDao topicDao = db.topicDao();

                Collections.sort(topicResponses, new Comparator<TopicResponse>() {
                    @Override
                    public int compare(TopicResponse o1, TopicResponse o2) {
                        return o1.getTopicIndex() - o2.getTopicIndex();
                    }
                });

                for (TopicResponse topicResponse : topicResponses) {
                    QPTDao qptDao = db.qptDao();

                    Topic topic = new Topic(topicResponse.getName(), topicResponse.getTopicIndex(), topicResponse.getDescription(), chapter.getChapterId(), topicResponse.isDoesPrecedeQPT(), topicResponse.getVersion());
                    topic.setTopicStatus(Topic.TopicActive);

                    if (topicResponse.getQpt() != null) {
                        QPT qpt = qptDao.getByIndexAndChapter(topicResponse.getQpt().getQptIndex(), chapter.getChapterId());
                        long qptId;

                        if (qpt == null) {
                            qpt = new QPT(topicResponse.getQpt().getQptIndex(), chapter.getChapterId(), 10);
                            qpt.setQptStatus(QPT.QPTUnlocked);
                            qptId = qptDao.insertAll(qpt)[0];
                        }
                        else {
                            qptId = qpt.getQptId();
                        }

                        topic.setQptId(qptId);
                    }

                    try {
                        topicDao.insertAll(topic);
                    }
                    catch (SQLiteConstraintException e) {
                        Log.e("TopicEx", e.getMessage());
                    }
                }

                return topicDao.findAllByChapter(chapter.getChapterId());
            }
        }
        catch (MalformedURLException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (ProtocolException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (IOException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (JSONException e) {
            Log.e("Error :", e.getMessage());
        }

        return null;
    }

    public static void getSlidesForTopic(Context context, String bookName, String chapterName, Topic topic) {
        //Log.e("Flow :", "getSlidesForTopic");

        AppDatabase db = AppDatabase.getAppDatabase(context);

        try {
            URL url = new URL(getProperty("IP", context) + "/api/get/slidesForTopic");

            JSONObject params = new JSONObject();
            params.put("bookName", bookName);
            params.put("bookChapterName", chapterName);
            params.put("bookTopicName", topic.getName());

            HttpURLConnection con = getAuthorisedConnection(context, url, params);
            con.connect();

            int responseCode = con.getResponseCode();
            //Log.e("Response Code :", responseCode + "");

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                List<SlideResponse> slideResponses = Arrays.asList(new ObjectMapper().readValue(response.toString(), SlideResponse[].class));

                for (SlideResponse sr : slideResponses) {
                    RevisionSlide revisionSlide = new RevisionSlide(sr.getSlideIndex(), (long) topic.getTopicId(), sr.slideType());
                    revisionSlide.setInteractive(sr.isInteractive());

                    if (sr.getSlideType().equals("Question")) {
                        QuestionResponse q = sr.getQuestion();

                        if(q != null) {
                            if (q.getQuestionType().equals("Undetermined") || q.getQuestionType().contains("MCQ_")) {
                                MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(q.getQuestion(), q.getInstruction(), "Self Explanatory", q.getMCQType(), QuestionCategory.CONCEPTUAL, QuestionLevel.MEDIUM, topic.getTopicId());

                                if(q.getDiagram() != null) {
                                    File dig = Utility.getImageFromURL(q.getDiagram().getUrl(), url, chapterName + topic.getName() + sr.getSlideIndex());
                                    Diagram diagram = new Diagram(q.getDiagram().getCaption(), dig.getAbsolutePath(), "");
                                    long diagramId = db.diagramDao().insertAll(diagram)[0];
                                    multipleChoiceQuestion.setDiagramId(diagramId);
                                }

                                long mcqID = db.multipleChoiceQuestionDao().insertAll(multipleChoiceQuestion)[0];

                                List<OptionResponse> options = Arrays.asList(q.getOptions());
                                for (OptionResponse or : options) {
                                    MCQOption mcqOption = new MCQOption(or.getOptionText(), or.isCorrect(), or.getExplanation(), mcqID);
                                    db.mcqOptionDao().insertAll(mcqOption);
                                }

                                revisionSlide.setMcqId(mcqID);
                            }
                            else if (q.getQuestionType().equals("ONE_WORD_ANSWER")) {

                            }
                        }
                    } else if (sr.getSlideType().equals("Revision")) {
                        if (sr.getRevisionPoint1() != null) {
                            RevisionPoint rp1 = new RevisionPoint(sr.getRevisionPoint1().getPreface(), sr.getRevisionPoint1().getExplanation(), "");
                            long rp1Id = db.revisionPointDao().insertAll(rp1)[0];
                            revisionSlide.setRevisionPoint1Id(rp1Id);
                        }

                        if (sr.getRevisionPoint2() != null) {
                            RevisionPoint rp2 = new RevisionPoint(sr.getRevisionPoint2().getPreface(), sr.getRevisionPoint2().getExplanation(), "");
                            long rp2Id = db.revisionPointDao().insertAll(rp2)[0];
                            revisionSlide.setRevisionPoint2Id(rp2Id);
                        }

                        if (sr.getRevealInteraction() != null) {
                            RevealInteraction ri = new RevealInteraction(sr.getRevealInteraction().getQuestion(), sr.getRevealInteraction().getAnswer());
                            long riId = db.revealInteractionDao().insertAll(ri)[0];
                            revisionSlide.setRevealInteractionId(riId);
                        }

                        if (sr.getConfirmInteraction() != null) {
                            ConfirmInteraction ci = new ConfirmInteraction(sr.getConfirmInteraction().getQuestion());
                            long ciId = db.confirmInteractionDao().insertAll(ci)[0];
                            revisionSlide.setConfirmInteractionId(ciId);
                        }

                        if (sr.getDiagram() != null) {
                            File dig = Utility.getImageFromURL(sr.getDiagram().getUrl(), url, chapterName + topic.getName() + sr.getSlideIndex());
                            Diagram diagram = new Diagram(sr.getDiagram().getCaption(), dig.getAbsolutePath(), "");
                            long diagramId = db.diagramDao().insertAll(diagram)[0];
                            revisionSlide.setDiagramId(diagramId);
                        }
                    }

                    db.revisionSlideDao().insertAll(revisionSlide);
                }
            }
        }
        catch (IOException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (JSONException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (SQLiteConstraintException e) {
            Log.e("Error :", e.getMessage());
        }
    }

    public static void getQuestionForTopic(Context context, String bookName, String chapterName, Topic topic) {
        //Log.e("Flow :", "getQuestionForTopic");
        AppDatabase db = AppDatabase.getAppDatabase(context);

        try {
            URL url = new URL(getProperty("IP", context) + "/api/get/questionsForTopic");

            JSONObject params = new JSONObject();
            params.put("bookName", bookName);
            params.put("bookChapterName", chapterName);
            params.put("bookTopicName", topic.getName());

            HttpURLConnection con = getAuthorisedConnection(context, url, params);
            con.connect();

            int responseCode = con.getResponseCode();
            //Log.e("Response Code :", responseCode + "");
            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                //Log.e("Response :", response.toString());

                List<QuestionResponse> questionResponses = Arrays.asList(new ObjectMapper().readValue(response.toString(), QuestionResponse[].class));

                for (QuestionResponse qr : questionResponses) {
                    if (qr.getQuestionType().equals("Undetermined") || qr.getQuestionType().contains("MCQ_")) {
                        MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(qr.getQuestion(), qr.getInstruction(), "Self Explanatory", qr.getMCQType(), QuestionCategory.CONCEPTUAL, QuestionLevel.MEDIUM, topic.getTopicId());
                        long mcqID = db.multipleChoiceQuestionDao().insertAll(multipleChoiceQuestion)[0];

                        List<OptionResponse> options = Arrays.asList(qr.getOptions());
                        for (OptionResponse or : options) {
                            MCQOption mcqOption = new MCQOption(or.getOptionText(), or.isCorrect(), or.getExplanation(), mcqID);
                            db.mcqOptionDao().insertAll(mcqOption);
                        }
                    }
                    else if (qr.getQuestionType().equals("ONE_WORD_ANSWER")) {

                    }
                }
            }
        }
        catch (IOException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (JSONException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (SQLiteConstraintException e) {
            Log.e("Error :", e.getMessage());
        }
    }

    private static HttpURLConnection getAuthorisedConnection(Context context, URL url, JSONObject params) {
        PrefManager prefManager = new PrefManager(context);

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("authorization", "Bearer " + prefManager.getUserDetails().getAccessToken());
            con.setRequestProperty("Content-Type", "application/json");

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(params.toString());
            writer.flush();
            writer.close();
            os.close();

            return con;
        }
        catch (MalformedURLException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (ProtocolException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (IOException e) {
            Log.e("Error :", e.getMessage());
        }

        return null;
    }

    public static boolean getSlidesForTopicIfUpdated(Context context, String bookName, String chapterName, Topic topic) {
        Log.e("Flow :", "getSlidesForTopicIfUpdated");

        AppDatabase db = AppDatabase.getAppDatabase(context);

        boolean foundToUpdate = false;

        try {
            URL url = new URL(getProperty("IP", context) + "/api/get/slidesForTopicIfUpdated");

            JSONObject params = new JSONObject();

            params.put("bookName", bookName);
            params.put("bookChapterName", chapterName);
            params.put("bookTopicName", topic.getName());
            params.put("version", topic.getVersion());

            Log.e("Params : ", params.toString(4));

            HttpURLConnection con = getAuthorisedConnection(context, url, params);
            con.connect();

            int responseCode = con.getResponseCode();
            Log.e("Response Code :", responseCode + "");

            if (responseCode == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                in.close();
                Log.e("Response :", response.toString());

                if(!response.toString().equals("")) {
                    foundToUpdate = true;

                    RevisionSlideDao revisionSlideDao = db.revisionSlideDao();
                    List<RevisionSlide> slidesToBeDeleted = revisionSlideDao.findAllByTopic(topic.getTopicId());

                    for(RevisionSlide revisionSlide : slidesToBeDeleted) {
                        revisionSlideDao.delete(revisionSlide);
                    }

                    List<SlideResponse> slideResponses = Arrays.asList(new ObjectMapper().readValue(response.toString(), SlideResponse[].class));

                    for (SlideResponse sr : slideResponses) {
                        RevisionSlide revisionSlide = new RevisionSlide(sr.getSlideIndex(), (long) topic.getTopicId(), sr.slideType());
                        revisionSlide.setInteractive(true);

                        if (sr.getSlideType().equals("Question")) {
                            QuestionResponse q = sr.getQuestion();
                            if(q != null) {
                                if (q.getQuestionType().equals("Undetermined") || q.getQuestionType().contains("MCQ_")) {
                                    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(q.getQuestion(), q.getInstruction(), "Self Explanatory", q.getMCQType(), QuestionCategory.CONCEPTUAL, QuestionLevel.MEDIUM, topic.getTopicId());

                                    if(q.getDiagram() != null) {
                                        File dig = Utility.getImageFromURL(q.getDiagram().getUrl(), url, chapterName + topic.getName() + new Date().getTime());
                                        Diagram diagram = new Diagram(sr.getDiagram().getCaption(), dig.getAbsolutePath(), "");
                                        long diagramId = db.diagramDao().insertAll(diagram)[0];
                                        multipleChoiceQuestion.setDiagramId(diagramId);

                                        Log.e("Saving Dig : ", dig.getAbsolutePath());
                                    }

                                    long mcqID = db.multipleChoiceQuestionDao().insertAll(multipleChoiceQuestion)[0];

                                    List<OptionResponse> options = Arrays.asList(q.getOptions());
                                    for (OptionResponse or : options) {
                                        MCQOption mcqOption = new MCQOption(or.getOptionText(), or.isCorrect(), or.getExplanation(), mcqID);
                                        db.mcqOptionDao().insertAll(mcqOption);
                                    }

                                    revisionSlide.setMcqId(mcqID);

                                    Log.e("Dig Ques : ", multipleChoiceQuestion.toString());
                                }
                                else if (q.getQuestionType().equals("ONE_WORD_ANSWER")) {

                                }
                            }
                        }
                        else if (sr.getSlideType().equals("Revision")) {
                            if (sr.getRevisionPoint1() != null) {
                                RevisionPoint rp1 = new RevisionPoint(sr.getRevisionPoint1().getPreface(), sr.getRevisionPoint1().getExplanation(), "");
                                long rp1Id = db.revisionPointDao().insertAll(rp1)[0];
                                revisionSlide.setRevisionPoint1Id(rp1Id);
                            }

                            if (sr.getRevisionPoint2() != null) {
                                RevisionPoint rp2 = new RevisionPoint(sr.getRevisionPoint2().getPreface(), sr.getRevisionPoint2().getExplanation(), "");
                                long rp2Id = db.revisionPointDao().insertAll(rp2)[0];
                                revisionSlide.setRevisionPoint2Id(rp2Id);
                            }

                            if (sr.getRevealInteraction() != null) {
                                RevealInteraction ri = new RevealInteraction(sr.getRevealInteraction().getQuestion(), sr.getRevealInteraction().getAnswer());
                                long riId = db.revealInteractionDao().insertAll(ri)[0];
                                revisionSlide.setRevealInteractionId(riId);
                            }

                            if (sr.getConfirmInteraction() != null) {
                                ConfirmInteraction ci = new ConfirmInteraction(sr.getConfirmInteraction().getQuestion());
                                long ciId = db.confirmInteractionDao().insertAll(ci)[0];
                                revisionSlide.setConfirmInteractionId(ciId);
                            }

                            if (sr.getDiagram() != null) {
                                File dig = Utility.getImageFromURL(sr.getDiagram().getUrl(), url, chapterName + topic.getName() + sr.getSlideIndex());
                                Diagram diagram = new Diagram(sr.getDiagram().getCaption(), dig.getAbsolutePath(), "");
                                long diagramId = db.diagramDao().insertAll(diagram)[0];
                                revisionSlide.setDiagramId(diagramId);
                            }
                        }

                        db.revisionSlideDao().insertAll(revisionSlide);
                    }
                }
            }
        }
        catch (IOException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (JSONException e) {
            Log.e("Error :", e.getMessage());
        }
        catch (SQLiteConstraintException e) {
            Log.e("Error :", e.getMessage());
        }

        return foundToUpdate;
    }

    private static String getTimeStamp(Date date) {
        String timestamp = "";
        timestamp = timestamp + (date.getYear() + 1900) + "-";

        if(date.getMonth() < 10)
            timestamp = timestamp + "0" + date.getMonth() + "-";
        else
            timestamp = timestamp + date.getMonth() + "-";


        if(date.getDate() < 10)
            timestamp = timestamp + "0" + date.getDate() + "T";
        else
            timestamp = timestamp + date.getDate() + "T";


        if(date.getHours() < 10)
            timestamp = timestamp + "0" + date.getHours() + ":";
        else
            timestamp = timestamp + date.getHours() + ":";


        if(date.getMinutes() < 10)
            timestamp = timestamp + "0" + date.getMinutes() + ":";
        else
            timestamp = timestamp + date.getMinutes() + ":";


        if(date.getSeconds() < 10)
            timestamp = timestamp + "0" + date.getSeconds() + "Z";
        else
            timestamp = timestamp + date.getSeconds() + "Z";

        return timestamp;
    }
}