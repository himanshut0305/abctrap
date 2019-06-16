package com.qedplus.particles;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.qedplus.particles.extras.MCQType;
import com.qedplus.particles.extras.Utility;
import com.qedplus.particles.model.MCQOptionBO;
import com.qedplus.particles.model.MultipleChoiceQuestionBO;
import com.qedplus.particles.model.OneWordAnswerQuestionBO;
import com.qedplus.particles.model.RevisionSlideBO;
import com.qedplus.particles.services.RevisionServices;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import katex.hourglass.in.mathlib.MathView;

public class RevisionActivity extends AppCompatActivity {
    String oneWordAnswer = "";
    Integer currentSlideIndex = 0, revealBoxHeight = 1, answersChecked = 0, answersToBeChecked = 0;
    Boolean hasSecondPart = false, hasQuestionToSolve = false, hasAnswered = false, hasConfirmInteraction = false, hasRevealInteraction = false, hasAnswerToReveal = false, hasQueryToConfirm = false, isInteractiveQuestion = false, hadQuestionToSolve = false, hasDiagram = false;

    ArrayList<RevisionSlideBO> revisionSlideBOS = new ArrayList<>();

    ScrollView scrollableContainer;
    LinearLayout revisionSlideContainer;
    Button continueButton;
    View progressBar;
    LayoutInflater inflater;

    TextView selectedSingleOption;
    List<TextView> currentOptionsTextViews = new ArrayList<>();
    HashSet<MCQOptionBO> selectedOptions = new HashSet<>();
    HashSet<TextView> selectedOptionsTextViews = new HashSet<>();

    Utility.Theme theme;
    float pixelDensity;

    Intent finishedRevisionIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Revision", "onCreate");
        theme = Utility.getTheme(getApplicationContext());

        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_revision);

        pixelDensity = getResources().getDisplayMetrics().density;

        RevisionServices revisionServices = new RevisionServices(new RevisionServices.UITask() {
            @Override
            public void task(ArrayList<RevisionSlideBO> revisionSlides) {
                if(revisionSlides.size() > 0 && revisionSlides != null) {
                    revisionSlideBOS.addAll(revisionSlides);
                    if(revisionSlideBOS.get(currentSlideIndex).isPointSlide()) {
                        loadFirstPartOfPointSlide(currentSlideIndex);
                    }
                    else {
                        loadQuestionSlide(currentSlideIndex);
                    }
                }
            }
        });

        String subjectName = getIntent().getStringExtra(TopicsActivity.IK_SUBJECT_NAME);

        int schoolYear = getIntent().getIntExtra(TopicsActivity.IK_SCHOOL_YEAR, 0);
        int chapterIndex = getIntent().getIntExtra(TopicsActivity.IK_CHAPTER_INDEX, 0);
        int topicIndex = getIntent().getIntExtra(TopicsActivity.IK_TOPIC_INDEX, 0);

        finishedRevisionIntent = new Intent(RevisionActivity.this, FinishedRevisionActivity.class);

        finishedRevisionIntent.putExtra(TopicsActivity.IK_SUBJECT_NAME, subjectName);
        finishedRevisionIntent.putExtra(TopicsActivity.IK_SCHOOL_YEAR, schoolYear);
        finishedRevisionIntent.putExtra(TopicsActivity.IK_CHAPTER_INDEX, chapterIndex);
        finishedRevisionIntent.putExtra(TopicsActivity.IK_TOPIC_INDEX, topicIndex);

        revisionServices.getSlidesForTopic(getApplicationContext(), subjectName, schoolYear, chapterIndex, topicIndex);

        continueButton = findViewById(R.id.continue_revision_button);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setBackgroundResource(theme.progressBarBackground);

        revisionSlideContainer = findViewById(R.id.revision_slide_container);

        inflater = LayoutInflater.from(getApplicationContext());
    }

    public void onContinueClick(View v) {
        Log.e("Revision", "onContinueClick");
        navigateForward(1);
    }

    LinearLayout linearLayout = null;
    ScrollView scrollView = null;

    private void initialiseScrollView() {
        scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        scrollView.setLayoutParams(layoutParams);

        linearLayout = new LinearLayout(this);
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(linearParams);

        scrollView.addView(linearLayout);
    }

    public void loadFirstPartOfPointSlide(int slideIndex) {
        Log.e("Revision", "loadFirstPartOfPointSlide");

        RevisionSlideBO slide = revisionSlideBOS.get(slideIndex);

        String g = slide.getFirstRevisionPoint().getGreeting();
        String p = slide.getFirstRevisionPoint().getThePoint();

        if(linearLayout == null)
            initialiseScrollView();

        boolean hasNothing = true;
        if(g != null && !g.equals("")) {
            View greetingLayout = inflater.inflate(R.layout.greeting_textview, revisionSlideContainer, false);
            TextView greetingTextView = greetingLayout.findViewById(R.id.revision_point_greeting);
            setHTMLText(greetingTextView, g);

            linearLayout.addView(greetingLayout);
            hasNothing = false;
        }

        if(p != null && !p.equals("")) {
            View paragraphLayout = inflater.inflate(R.layout.revision_point_textview, revisionSlideContainer, false);
            MathView paragraphTextView = paragraphLayout.findViewById(R.id.revision_point_text);
            paragraphTextView.setTextSize(16);
            paragraphTextView.setDisplayText(p);

            linearLayout.addView(paragraphLayout);
            revisionSlideContainer.addView(scrollView);
            slideInFromLeft(revisionSlideContainer, 400);
            hasNothing = false;
        }
        else {
            revisionSlideContainer.addView(scrollView);
        }

        if(slide.getSecondRevisionPoint() != null) {
            continueButton.setText("Okay");
            hasSecondPart = true;
        }
        else if(slide.getConfirmInteraction() != null) {
            hasSecondPart = true;
            hasConfirmInteraction = true;
        }
        else if(slide.getRevealInteraction() != null) {
            hasSecondPart = true;
            hasRevealInteraction = true;
        }
        else if(slide.getDiagram() != null) {
            hasSecondPart = true;
            hasDiagram = true;
        }
        else {
            currentSlideIndex++;
        }

        if(hasNothing) {
            navigateForward(1);
        }
    }

    public void loadSecondPartOfPointSlide(int slideIndex) {
        Log.e("Revision", "loadSecondPartOfPointSlide");

        RevisionSlideBO slide = revisionSlideBOS.get(slideIndex);

        String g = slide.getSecondRevisionPoint().getGreeting();
        String p = slide.getSecondRevisionPoint().getThePoint();

        if(g != null && !g.equals("")) {
            View greetingLayout = inflater.inflate(R.layout.greeting_textview, revisionSlideContainer, false);
            TextView greetingTextView = greetingLayout.findViewById(R.id.revision_point_greeting);
            setHTMLText(greetingTextView, g);
            linearLayout.addView(greetingLayout);
        }

        if(p != null && !p.equals("")) {
            final View paragraphLayout = inflater.inflate(R.layout.revision_point_textview, revisionSlideContainer, false);
            MathView paragraphTextView = paragraphLayout.findViewById(R.id.revision_point_text);
            paragraphTextView.setTextSize(16);
            paragraphTextView.setDisplayText(p);

            linearLayout.addView(paragraphLayout);
            slideInFromLeft(paragraphLayout, 240);
        }

        continueButton.setText("Continue");
        currentSlideIndex++;
    }

    public void loadConfirmInteraction(int slideIndex) {
        Log.e("Revision", "loadConfirmInteraction");

        RevisionSlideBO slide = revisionSlideBOS.get(slideIndex);

        String q = slide.getConfirmInteraction().getQuery();
        String a = slide.getConfirmInteraction().getAffirmativeResponse();
        String n = slide.getConfirmInteraction().getNegativeResponse();

        if(linearLayout == null)
            initialiseScrollView();

        final View confirmLayout = inflater.inflate(R.layout.interaction_confirm, revisionSlideContainer, false);

        MathView queryTextView = confirmLayout.findViewById(R.id.confirm_query_textview);
        Button affirmBtn = confirmLayout.findViewById(R.id.affirm_btn);
        Button rejectBtn = confirmLayout.findViewById(R.id.reject_btn);

        linearLayout.addView(confirmLayout);

        queryTextView.setTextSize(16);
        queryTextView.setDisplayText(q);
        affirmBtn.setText(a);
        rejectBtn.setText(n);

        slideInFromLeft(confirmLayout, 240);

        affirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSlideIndex+=2;
                hasQueryToConfirm = false;
                continueButton.setBackgroundResource(R.drawable.custom_button);
                continueButton.setText("CONTINUE");
                navigateForward(2);
            }
        });

        rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSlideIndex++;
                hasQueryToConfirm = false;
                continueButton.setBackgroundResource(R.drawable.custom_button);
                continueButton.setText("CONTINUE");
                navigateForward(1);
            }
        });

        hasQueryToConfirm = true;
        continueButton.setText("CONFIRM");
        continueButton.setBackgroundResource(R.drawable.qr_continue_button_background_dark_grey);
    }

    public void loadRevealInteraction(int slideIndex) {
        Log.e("Revision", "loadRevealInteraction");

        RevisionSlideBO slide = revisionSlideBOS.get(slideIndex);
        String q = slide.getRevealInteraction().getQuery();
        final String a = slide.getRevealInteraction().getAnswer();

        if(linearLayout == null)
            initialiseScrollView();

        revealBoxHeight = a.length();

        final View revealLayout = inflater.inflate(R.layout.interaction_reveal, revisionSlideContainer, false);
        final MathView queryTextView = revealLayout.findViewById(R.id.reveal_query_textview);
        final MathView answerTextView = revealLayout.findViewById(R.id.reveal_answer_textview);

        final LinearLayout revealBoxParent = revealLayout.findViewById(R.id.reveal_answer_parent_view);

        linearLayout.addView(revealLayout);
        queryTextView.setTextSize(16);
        queryTextView.setDisplayText(q);

        answerTextView.setTextSize(16);
        answerTextView.setDisplayText(a);
        slideInFromLeft(revealLayout, 240);

        ViewTreeObserver vto = revisionSlideContainer.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                    revisionSlideContainer.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                else {
                    revisionSlideContainer.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }

                //revealBoxHeight = revealBoxParent.getMeasuredHeight();
                //Log.e("Reveal Height", revealBoxHeight + "");
                revealBoxParent.setVisibility(View.GONE);
            }
        });

        hasAnswerToReveal = true;
        continueButton.setText("Show Answer");
    }

    public void loadDiagram(int slideIndex) {
        Log.e("Revision", "loadDiagram");

        RevisionSlideBO slide = revisionSlideBOS.get(slideIndex);

        String d = slide.getDiagram().getDiagramResourceId();
        String c = slide.getDiagram().getDiagramCaption();

        final View diagramLayout = inflater.inflate(R.layout.diagram, revisionSlideContainer, false);
        ImageView diagramImageView = diagramLayout.findViewById(R.id.diagram);
        TextView diagramCaptionTextView = diagramLayout.findViewById(R.id.diagram_caption);

        Bitmap diagram = BitmapFactory.decodeFile(d);
        diagramImageView.setImageBitmap(diagram);

        if(c != null)
            setHTMLText(diagramCaptionTextView, c);

        revisionSlideContainer.addView(diagramLayout);
        slideInFromLeft(diagramLayout, 240);

        currentSlideIndex++;
        continueButton.setText("CONTINUE");
    }

    public void revealAnswer() {
        Log.e("Revision", "revealAnswer");

        final LinearLayout answerTextView = findViewById(R.id.reveal_answer_parent_view);
        answerTextView.setVisibility(View.VISIBLE);

        ValueAnimator anim = ValueAnimator.ofInt(1, revealBoxHeight*2);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = answerTextView.getLayoutParams();
                layoutParams.height = val;
                answerTextView.setLayoutParams(layoutParams);
            }
        });

        anim.setDuration(240);
        anim.start();

        hasAnswerToReveal = false;
        continueButton.setText("CONTINUE");
        currentSlideIndex++;
    }

    public void loadQuestionSlide(int slideIndex) {
        Log.e("Revision", "loadQuestionSlide");

        RevisionSlideBO slide = revisionSlideBOS.get(slideIndex);

        switch (slide.getQuestion().getQuestionType()) {
            case JumbledWordsQuestion:
                break;
            case OneWordAnswerQuestion:
                OneWordAnswerQuestionBO oq = (OneWordAnswerQuestionBO) slide.getQuestion();
                loadOneWordQuestion(oq);
                break;
            case MatchTheColumnQuestion:
                break;
            case MultipleChoiceQuestion:
                MultipleChoiceQuestionBO q = (MultipleChoiceQuestionBO) slide.getQuestion();
                isInteractiveQuestion = q.getInteractive();
                continueButton.setText("Select and Submit");

                switch (q.getMcqType()) {
                    case  MCQType.TRUE_FALSE:
                        loadMCQTrueFalse(q);
                        break;
                    case  MCQType.SINGLE_ANSWER:
                        loadMCQ(q);
                        break;
                    case  MCQType.MULTIPLE_ANSWER:
                        loadMCQ(q);
                        break;
                }

                break;
        }

        continueButton.setBackgroundResource(R.drawable.qr_continue_button_background_dark_grey);
        hasQuestionToSolve = true;
        currentSlideIndex++;
    }

    public void loadMCQ(final MultipleChoiceQuestionBO question) {
        Log.e("Revision", "loadMCQ");

        String q = question.getQuestion();
        String i = question.getInstruction();

        HashSet<MCQOptionBO> options = question.getOptions();

        final View mcqSingleAnswerLayout = inflater.inflate(R.layout.question_slide_mcq_single_answer, revisionSlideContainer, false);

        MathView questionTextView = mcqSingleAnswerLayout.findViewById(R.id.question_text_view);
        TextView instructionTextView = mcqSingleAnswerLayout.findViewById(R.id.instruction_text_view);
        ImageView diagramImageView = mcqSingleAnswerLayout.findViewById(R.id.question_diagram);

        questionTextView.setTextSize(16);
        questionTextView.setDisplayText(q);
        setHTMLText(instructionTextView, i);

        if(question.getDiagram() != null) {
            String d = question.getDiagram().getDiagramResourceId();
            Bitmap diagram = BitmapFactory.decodeFile(d);
            diagramImageView.setImageBitmap(diagram);
        }

        revisionSlideContainer.addView(mcqSingleAnswerLayout);

        final LinearLayout mcqSingleAnswerLinearLayout = findViewById(R.id.mcq_single_answer_linear_layout);

        LinearLayout aolLayout = null;
        double optionWidth = 0;
        int numberOfOptions = options.size();

        if(question.isAlternateDisplay()) {
            View aol = inflater.inflate(R.layout.option_single_answer_alternate, mcqSingleAnswerLinearLayout, false);
            aolLayout = aol.findViewById(R.id.option_mcq_alternate_layout);
            mcqSingleAnswerLinearLayout.addView(aol);

            optionWidth = (revisionSlideContainer.getMeasuredWidth() - (32*pixelDensity) - (10*pixelDensity*(numberOfOptions-1))) / numberOfOptions;
        }

        for(MCQOptionBO option : options) {
            View optionMCQSingleAnswerLayout = inflater.inflate(R.layout.option_mcq_single_answer, mcqSingleAnswerLinearLayout, false);
            final TextView optionTextView = optionMCQSingleAnswerLayout.findViewById(R.id.option_single_answer_textview);
            setHTMLText(optionTextView, option.getOption());

            optionTextView.setTag(option);

            if(question.isAlternateDisplay()) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.rightMargin = (int)(10*pixelDensity);
                layoutParams.width = (int) optionWidth;
                optionTextView.setLayoutParams(layoutParams);

                aolLayout.addView(optionTextView);
            }
            else {
                mcqSingleAnswerLinearLayout.addView(optionTextView);
            }

            currentOptionsTextViews.add(optionTextView);

            optionTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(question.getMcqType().equals(MCQType.SINGLE_ANSWER))
                        onMCQSingleAnswerOptionSelect(optionTextView);
                    else
                        onMCQMultipleAnswerOptionSelect(optionTextView);
                }
            });

            if(isInteractiveQuestion) {
                answersToBeChecked++;
            }
        }

        slideInFromLeft(revisionSlideContainer, 400);
    }

    public void loadMCQTrueFalse(MultipleChoiceQuestionBO question) {
        Log.e("Revision", "loadMCQTrueFalse");

        String q = question.getQuestion();
        String i = question.getInstruction();
        HashSet<MCQOptionBO> options = question.getOptions();

        final View mcqTrueFalseLayout = inflater.inflate(R.layout.question_slide_mcq_true_false, revisionSlideContainer, false);

        MathView questionTextView = mcqTrueFalseLayout.findViewById(R.id.question_text_view);

        TextView instructionTextView = mcqTrueFalseLayout.findViewById(R.id.instruction_text_view);
        TextView trueTextView = mcqTrueFalseLayout.findViewById(R.id.btn_true_option);
        TextView falseTextView = mcqTrueFalseLayout.findViewById(R.id.btn_false_option);

        Iterator<MCQOptionBO> iterator = options.iterator();
        MCQOptionBO option1 = iterator.next();
        MCQOptionBO option2 = iterator.next();

        if(option1.getOption().equals("True")) {
            trueTextView.setTag(option1);
            falseTextView.setTag(option2);
        }
        else {
            trueTextView.setTag(option2);
            falseTextView.setTag(option1);
        }

        questionTextView.setTextSize(16);
        questionTextView.setDisplayText(q);

        setHTMLText(instructionTextView, i);
        revisionSlideContainer.addView(mcqTrueFalseLayout);

        currentOptionsTextViews.add(trueTextView);
        currentOptionsTextViews.add(falseTextView);

        trueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMCQSingleAnswerOptionSelect(v);
            }
        });

        falseTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMCQSingleAnswerOptionSelect(v);
            }
        });

        slideInFromLeft(revisionSlideContainer, 400);
    }

    public void loadOneWordQuestion(OneWordAnswerQuestionBO question) {
        String q = question.getQuestion();
        String i = question.getInstruction();

        final View OWQAnswerLayout = inflater.inflate(R.layout.question_slide_one_word, revisionSlideContainer, false);
        MathView questionTextView = OWQAnswerLayout.findViewById(R.id.question_text_view);
        TextView instructionTextView = OWQAnswerLayout.findViewById(R.id.instruction_text_view);

        questionTextView.setTextSize(16);
        questionTextView.setDisplayText(q);
        setHTMLText(instructionTextView, i);
        revisionSlideContainer.addView(OWQAnswerLayout);

        EditText answerEditText = OWQAnswerLayout.findViewById(R.id.one_word_answer);

        answerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String writtenAnswer = s.toString();
                writtenAnswer = writtenAnswer.trim();

                if(!writtenAnswer.equals("")) {
                    hasAnswered = true;
                    oneWordAnswer = writtenAnswer;
                    continueButton.setText("SUBMIT");
                    continueButton.setBackgroundResource(R.drawable.custom_button);
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    public void setHTMLText(TextView textView, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
        }
        else {
            textView.setText(Html.fromHtml(text));
        }
    }

    public void incrementProgressBar(int step) {
        int quantum = findViewById(R.id.progress_bar_background).getMeasuredWidth() / (revisionSlideBOS.size() - 1) - 8;

        ValueAnimator anim = ValueAnimator.ofInt(progressBar.getMeasuredWidth(), progressBar.getMeasuredWidth() + (quantum*step));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = progressBar.getLayoutParams();
                layoutParams.width = val;
                progressBar.setLayoutParams(layoutParams);
            }
        });

        anim.setDuration(400);
        anim.start();
    }

    public void onMCQSingleAnswerOptionSelect(View view) {
        Log.e("Revision", "onMCQSingleAnswerOptionSelect");

        selectedSingleOption = (TextView) view;
        selectedOptions.clear();
        selectedOptions.add((MCQOptionBO) selectedSingleOption.getTag());

        Log.e("Selected Option", selectedSingleOption.getTag().toString());

        for(TextView optionTextView : currentOptionsTextViews) {
            optionTextView.setBackgroundResource(R.drawable.white_background);
            optionTextView.setTextColor(getResources().getColor(R.color.black));
        }

        selectedSingleOption.setBackgroundResource(R.drawable.qr_selected_answer_background);
        selectedSingleOption.setTextColor(getResources().getColor(R.color.white));

        if(isInteractiveQuestion) {
            answersChecked++;
            checkAnswer();
            if (answersChecked == answersToBeChecked) {
                continueButton.setBackgroundResource(R.drawable.custom_button);
                continueButton.setText("CONTINUE");

                hasAnswered = false;
                hasQuestionToSolve = false;
                hadQuestionToSolve = true;
            }
        }
        else {
            hasAnswered = true;
            continueButton.setBackgroundResource(R.drawable.custom_button);
            continueButton.setText("Submit");
        }
    }

    public void onMCQMultipleAnswerOptionSelect(TextView view) {
        Log.e("Revision", "onMCQMultipleAnswerOptionSelect");

        if(selectedOptionsTextViews.contains(view)) {
            selectedOptionsTextViews.remove(view);
            selectedOptions.remove((MCQOptionBO) view.getTag());
        }
        else {
            selectedOptionsTextViews.add(view);
            selectedOptions.add((MCQOptionBO) view.getTag());
        }

        for(TextView optionTextView : currentOptionsTextViews) {
            optionTextView.setBackgroundResource(R.drawable.white_background);
            optionTextView.setTextColor(getResources().getColor(R.color.black));
        }

        for(TextView optionTextView : selectedOptionsTextViews) {
            optionTextView.setBackgroundResource(R.drawable.qr_selected_answer_background);
            optionTextView.setTextColor(getResources().getColor(R.color.white));
        }

        if(selectedOptionsTextViews.isEmpty()) {
            hasAnswered = false;
            continueButton.setText("SELECT AND SUBMIT");
            continueButton.setBackgroundResource(R.drawable.qr_continue_button_background_dark_grey);
        }
        else {
            hasAnswered = true;
            continueButton.setText("Submit");
            continueButton.setBackgroundResource(R.drawable.custom_button);
        }
    }

    public void checkAnswer() {
        Log.e("Revision", "checkAnswer");
        Log.e("Selected Options", selectedOptions.toString());

        RevisionSlideBO slide = revisionSlideBOS.get(currentSlideIndex-1);

        switch (slide.getQuestion().getQuestionType()) {
            case JumbledWordsQuestion:
                break;
            case OneWordAnswerQuestion:
                OneWordAnswerQuestionBO owaq = (OneWordAnswerQuestionBO) slide.getQuestion();
                boolean hasAnsweredOWAQCorrectly = owaq.isCorrectAnswer(oneWordAnswer);
                showNotification(hasAnsweredOWAQCorrectly, owaq.getExplanation());
                break;
            case MatchTheColumnQuestion:
                break;
            case MultipleChoiceQuestion:
                MultipleChoiceQuestionBO mcq = (MultipleChoiceQuestionBO) slide.getQuestion();
                boolean hasAnsweredMCQCorrectly = mcq.isCorrectAnswer(selectedOptions);

                if(mcq.getMcqType().equals(MCQType.MULTIPLE_ANSWER) && selectedOptions.iterator().next().getExplanation() == null) {
                    showNotification(hasAnsweredMCQCorrectly, mcq.getGenericExplanation());
                }
                else {
                    showNotification(hasAnsweredMCQCorrectly, selectedOptions.iterator().next().getExplanation());
                }

                break;
        }
    }

    public void showNotification(Boolean hasAnsweredCorrectly, String notificationText) {
        Log.e("Revision", "showNotification");

        final CardView notificationCardView = findViewById(R.id.notification_card_view);
        LinearLayout l = notificationCardView.findViewById(R.id.notification_layout);
        MathView t = notificationCardView.findViewById(R.id.notification_text_view);

        if(hasAnsweredCorrectly) {
            l.setBackgroundColor(getResources().getColor(R.color.green));
        }
        else {
            l.setBackgroundColor(getResources().getColor(R.color.red));
        }

        t.setTextSize(16);
        t.setDisplayText(notificationText);

        ValueAnimator anim = ValueAnimator.ofInt(30, -600);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) notificationCardView.getLayoutParams();
                layoutParams.topMargin = val;
                notificationCardView.setLayoutParams(layoutParams);
            }
        });

        anim.setDuration(250);
        anim.start();
    }

    public void hideNotification() {
        final CardView notificationCardView = findViewById(R.id.notification_card_view);

        ValueAnimator anim = ValueAnimator.ofInt(-600, 30);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) notificationCardView.getLayoutParams();
                layoutParams.topMargin = val;
                notificationCardView.setLayoutParams(layoutParams);
            }
        });

        anim.setDuration(250);
        anim.start();
    }

    public void onCancelClick(View v) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        onBackPressed();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(RevisionActivity.this, R.style.myDialog));
        builder.setMessage("All Progress in this session will be lost. You'll have to start the topic from beginning. Do you really want to quit?")
                .setPositiveButton("Quit", dialogClickListener)
                .setNegativeButton("Cancel", dialogClickListener)
                .show();
    }

    public void navigateForward(int step) {
        if(hasQueryToConfirm) {
            return;
        }

        if(hasAnswerToReveal) {
            revealAnswer();
            return;
        }

        if(hadQuestionToSolve) {
            hideNotification();
            selectedOptions.clear();
            hadQuestionToSolve = false;
        }

        if(!hasQuestionToSolve) {
            if (currentSlideIndex < revisionSlideBOS.size()) {
                if (revisionSlideBOS.get(currentSlideIndex).isPointSlide()) {
                    if (hasSecondPart) {
                        if(hasDiagram) {
                            loadDiagram(currentSlideIndex);
                            hasDiagram = false;
                        }
                        else if(hasConfirmInteraction) {
                            loadConfirmInteraction(currentSlideIndex);
                            hasConfirmInteraction = false;
                        }
                        else if(hasRevealInteraction) {
                            loadRevealInteraction(currentSlideIndex);
                            hasRevealInteraction = false;
                        }
                        else {
                            loadSecondPartOfPointSlide(currentSlideIndex);
                        }

                        hasSecondPart = false;
                    }
                    else {
                        incrementProgressBar(step);
                        revisionSlideContainer.removeAllViews();
                        if(linearLayout != null) {
                            linearLayout.removeAllViews();
                        }
                        loadFirstPartOfPointSlide(currentSlideIndex);
                    }
                }
                else {
                    incrementProgressBar(step);
                    revisionSlideContainer.removeAllViews();
                    if(linearLayout != null) {
                        linearLayout.removeAllViews();
                    }
                    loadQuestionSlide(currentSlideIndex);
                }
            } else {
                finish();
                startActivity(finishedRevisionIntent);
            }
        }
        else {
            if(hasAnswered) {
                checkAnswer();
                continueButton.setBackgroundResource(R.drawable.custom_button);
                continueButton.setText("Continue");
                hadQuestionToSolve = true;

                hasAnswered = false;
                hasQuestionToSolve = false;
            }
        }
    }

    private void slideInFromLeft(final View view, int duration) {
        ValueAnimator anim = ValueAnimator.ofInt(1000, 0);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                layoutParams.leftMargin = val;
                layoutParams.rightMargin = 0 - val;
                view.setLayoutParams(layoutParams);
            }
        });

        anim.setDuration(duration);
        anim.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5 && keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            onBackPressed();
                            return;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(RevisionActivity.this, R.style.myDialog));
            builder.setMessage("All Progress in this session will be lost. You'll have to start the topic from beginning. Do you really want to quit?")
                    .setPositiveButton("Quit", dialogClickListener)
                    .setNegativeButton("Cancel", dialogClickListener)
                    .show();
        }

        return super.onKeyDown(keyCode, event);
    }
}