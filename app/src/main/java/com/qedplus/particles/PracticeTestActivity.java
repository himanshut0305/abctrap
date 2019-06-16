package com.qedplus.particles;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qedplus.particles.extras.MCQType;
import com.qedplus.particles.model.MCQOptionBO;
import com.qedplus.particles.model.MultipleChoiceQuestionBO;
import com.qedplus.particles.model.OneWordAnswerQuestionBO;
import com.qedplus.particles.model.Question;
import com.qedplus.particles.services.PracticeServices;

import java.util.HashSet;
import java.util.Iterator;

import katex.hourglass.in.mathlib.MathView;

public class PracticeTestActivity extends AppCompatActivity {
    public static String IK_QUESTIONS_ASKED = "QuestionsAsked";
    public static String IK_QUESTIONS_ANSWERED = "QuestionsAnswered";

    Iterator iterator;
    LayoutInflater inflater;
    LinearLayout parentLayout;

    HashSet<Question> questions = new HashSet<>();

    HashSet<CheckBox> optionCheckBoxes = new HashSet<>();
    HashSet<CheckBox> selectedCheckBoxes = new HashSet<>();
    HashSet<MCQOptionBO> selectedOptions = new HashSet<>();

    Button submitButton;

    boolean hasAnswered = false;
    boolean hasSubmitted = false;
    boolean endSession = false;

    Question currentQuestion;
    String oneWordAnswer;

    int questionsToAsk = 5;
    int questionsAsked = 0;
    int questionsAnsweredCorrectly = 0;

    Intent finishedTestIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        parentLayout = findViewById(R.id.practice_questions_container);
        inflater = LayoutInflater.from(getApplicationContext());
        submitButton = findViewById(R.id.submit_answer_button);

        PracticeServices practiceServices = new PracticeServices(new PracticeServices.UITask() {
            @Override
            public void task(HashSet<Question> retrievesQuestions, int toAsk) {
                questions.addAll(retrievesQuestions);
                questionsToAsk = toAsk;
                iterator = questions.iterator();
                loadQuestion();
            }
        });

        String subjectName = getIntent().getStringExtra(TopicsActivity.IK_SUBJECT_NAME);

        int schoolYear = getIntent().getIntExtra(TopicsActivity.IK_SCHOOL_YEAR, 0);
        int chapterIndex = getIntent().getIntExtra(TopicsActivity.IK_CHAPTER_INDEX, 0);
        int topicIndex = getIntent().getIntExtra(TopicsActivity.IK_TOPIC_INDEX, 0);
        int qptIndex = getIntent().getIntExtra(TopicsActivity.IK_QPT_INDEX, 0);

        finishedTestIntent = new Intent(this, FinishedTestActivity.class);

        finishedTestIntent.putExtra(TopicsActivity.IK_SUBJECT_NAME, subjectName);
        finishedTestIntent.putExtra(TopicsActivity.IK_SCHOOL_YEAR, schoolYear);
        finishedTestIntent.putExtra(TopicsActivity.IK_CHAPTER_INDEX, chapterIndex);
        finishedTestIntent.putExtra(TopicsActivity.IK_TOPIC_INDEX, topicIndex);
        finishedTestIntent.putExtra(TopicsActivity.IK_QPT_INDEX, qptIndex);

        practiceServices.getQuestionsByTopic(getApplicationContext(), subjectName, schoolYear, chapterIndex, topicIndex, qptIndex);

        final Intent i = new Intent(PracticeTestActivity.this, MainActivity.class);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(endSession) {
                    startActivity(i);
                    return;
                }

                if (hasSubmitted) {
                    hideNotification();
                    loadQuestion();
                    return;
                }

                if(hasAnswered) {
                    checkAnswer();
                    hasSubmitted = true;
                    submitButton.setText("Continue");
                }
            }
        });
    }

    public void loadQuestion() {
        if(questionsAsked >= questionsToAsk) {
            finishedTestIntent.putExtra(IK_QUESTIONS_ASKED, questionsAsked);
            finishedTestIntent.putExtra(IK_QUESTIONS_ANSWERED, questionsAnsweredCorrectly);
            startActivity(finishedTestIntent);
            return;
        }

        if(iterator.hasNext()) {
            questionsAsked++;
            parentLayout.removeAllViews();
            hasSubmitted = false;
            hasAnswered = false;

            Question question = (Question) iterator.next();
            currentQuestion = question;

            if(question.getClass() == MultipleChoiceQuestionBO.class) {
                submitButton.setText("SELECT AND SUBMIT");
                submitButton.setBackgroundResource(R.drawable.qr_continue_button_background_dark_grey);

                MultipleChoiceQuestionBO mcq = (MultipleChoiceQuestionBO) question;
                if (mcq.getMcqType().equals(MCQType.SINGLE_ANSWER) || mcq.getMcqType().equals(MCQType.MULTIPLE_ANSWER)) {
                    View v = inflater.inflate(R.layout.question_slide_mcq_single_answer, parentLayout, false);

                    LinearLayout questionLayout = v.findViewById(R.id.mcq_single_answer_linear_layout);
                    MathView questionTextView = v.findViewById(R.id.question_text_view);
                    TextView instructionTextView = v.findViewById(R.id.instruction_text_view);

                    questionTextView.setDisplayText(mcq.getQuestion());
                    instructionTextView.setText(mcq.getInstruction());
                    parentLayout.addView(v);

                    Log.e("OPTION", mcq.getOptions().toString());

                    for(MCQOptionBO option : mcq.getOptions()) {
                        View m = inflater.inflate(R.layout.qpt_mcq_option, parentLayout, false);
                        TextView optionTextView = m.findViewById(R.id.qpt_mcq_option_text_view);
                        final CheckBox checkBox = m.findViewById(R.id.option_checkbox);

                        setHTMLText(optionTextView, option.getOption());
                        checkBox.setTag(option);
                        questionLayout.addView(m);

                        optionCheckBoxes.add(checkBox);

                        if(mcq.getMcqType().equals(MCQType.MULTIPLE_ANSWER)) {
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked) {
                                        selectedCheckBoxes.add(checkBox);
                                        selectedOptions.add((MCQOptionBO) checkBox.getTag());
                                    }
                                    else {
                                        selectedCheckBoxes.remove(checkBox);
                                        selectedOptions.remove(checkBox.getTag());
                                    }

                                    if(selectedCheckBoxes.isEmpty()) {
                                        submitButton.setText("SELECT AND SUBMIT");
                                        submitButton.setBackgroundResource(R.drawable.qr_continue_button_background_dark_grey);
                                    }
                                    else {
                                        hasAnswered = true;
                                        submitButton.setText("SUBMIT");
                                        submitButton.setBackgroundResource(R.drawable.custom_button);
                                    }
                                }
                            });
                        }
                        else if (mcq.getMcqType().equals(MCQType.SINGLE_ANSWER)) {
                            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    if (isChecked) {
                                        selectedCheckBoxes.add(checkBox);
                                        selectedOptions.add((MCQOptionBO) checkBox.getTag());
                                    }
                                    else {
                                        selectedCheckBoxes.remove(checkBox);
                                        selectedOptions.remove(checkBox.getTag());
                                    }

                                    for(CheckBox cbx : optionCheckBoxes) {
                                        cbx.setChecked(false);
                                    }

                                    for(CheckBox cbx : selectedCheckBoxes) {
                                        cbx.setChecked(true);
                                    }

                                    if(selectedCheckBoxes.isEmpty()) {
                                        submitButton.setText("SELECT AND SUBMIT");
                                        submitButton.setBackgroundResource(R.drawable.qr_continue_button_background_dark_grey);
                                    }
                                    else {
                                        hasAnswered = true;
                                        submitButton.setText("SUBMIT");
                                        submitButton.setBackgroundResource(R.drawable.custom_button);
                                    }
                                }
                            });
                        }
                    }
                }
                else if (mcq.getMcqType().equals(MCQType.TRUE_FALSE)) {
                    View v = inflater.inflate(R.layout.question_slide_mcq_true_false, parentLayout, false);
                    parentLayout.addView(v);
                }
            }
            else if (question.getClass() == OneWordAnswerQuestionBO.class) {
                submitButton.setText("Write and Submit");
                submitButton.setBackgroundResource(R.drawable.qr_continue_button_background_dark_grey);

                OneWordAnswerQuestionBO owaq = (OneWordAnswerQuestionBO) question;

                View v = inflater.inflate(R.layout.question_slide_one_word, parentLayout, false);
                MathView questionTextView = v.findViewById(R.id.question_text_view);
                TextView instructionTextView = v.findViewById(R.id.instruction_text_view);

                questionTextView.setDisplayText(owaq.getQuestion());
                setHTMLText(instructionTextView, owaq.getInstruction());

                EditText answerEditText = v.findViewById(R.id.one_word_answer);

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
                            submitButton.setText("SUBMIT");
                            submitButton.setBackgroundResource(R.drawable.custom_button);
                        }
                    }

                    @Override
                    public void afterTextChanged(Editable s) { }
                });

                parentLayout.addView(v);
            }
        }
        else {
            parentLayout.removeAllViews();
            parentLayout.addView(inflater.inflate(R.layout.apology, parentLayout, false));
            endSession = true;
            submitButton.setText("End Session");
            submitButton.setBackgroundResource(R.drawable.custom_button);
        }
    }

    public void checkAnswer() {
        switch (currentQuestion.getQuestionType()) {
            case JumbledWordsQuestion:
                break;
            case OneWordAnswerQuestion:
                OneWordAnswerQuestionBO owaq = (OneWordAnswerQuestionBO) currentQuestion;
                boolean hasAnsweredOWAQCorrectly = owaq.isCorrectAnswer(oneWordAnswer);
                showNotification(hasAnsweredOWAQCorrectly, owaq.getExplanation());
                break;
            case MatchTheColumnQuestion:
                break;
            case MultipleChoiceQuestion:
                MultipleChoiceQuestionBO mcq = (MultipleChoiceQuestionBO) currentQuestion;
                boolean hasAnsweredMCQCorrectly = mcq.isCorrectAnswer(selectedOptions);

                if(mcq.getMcqType() == MCQType.MULTIPLE_ANSWER) {
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
            questionsAnsweredCorrectly++;
            l.setBackgroundColor(getResources().getColor(R.color.green));
        }
        else {
            l.setBackgroundColor(getResources().getColor(R.color.red));
        }

        t.setDisplayText(notificationText);
        float cardHeight = notificationCardView.getMeasuredHeight();

        ValueAnimator anim = ValueAnimator.ofFloat(400f, (-100f - cardHeight));
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float val = (float) valueAnimator.getAnimatedValue();
                notificationCardView.setTranslationY(val);
            }
        });

        anim.setDuration(250);
        anim.start();
    }

    public void hideNotification() {
        final CardView notificationCardView = findViewById(R.id.notification_card_view);
        float cardHeight = notificationCardView.getMeasuredHeight();

        ValueAnimator anim = ValueAnimator.ofFloat((-100f - cardHeight), 400f);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float val = (float) valueAnimator.getAnimatedValue();
                notificationCardView.setTranslationY(val);
            }
        });

        anim.setDuration(400);
        anim.start();
    }

    public void setHTMLText(TextView textView, String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(text));
        }
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

            AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(PracticeTestActivity.this, R.style.myDialog));
            builder.setMessage("All Progress in this session will be lost. You'll have to start the topic from beginning. Do you really want to quit?")
                    .setPositiveButton("Quit", dialogClickListener)
                    .setNegativeButton("Cancel", dialogClickListener)
                    .show();
        }

        return super.onKeyDown(keyCode, event);
    }
}
