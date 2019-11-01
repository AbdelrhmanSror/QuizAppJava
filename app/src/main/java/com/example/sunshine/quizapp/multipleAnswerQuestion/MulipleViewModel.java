package com.example.sunshine.quizapp.multipleAnswerQuestion;

import android.view.View;
import android.widget.CheckBox;

import androidx.lifecycle.ViewModel;

import com.example.sunshine.quizapp.model.Question;
import com.example.sunshine.quizapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MulipleViewModel extends ViewModel {
    private ArrayList<Question> questions;
    /**
     * current question to be displayed
     */
    private Question currentQuestion;
    /**
     * question index to use it to select question from list
     */
    private int questionIndex = -1;
    /**
     * list of answers for current question to manipulate with
     */
    private List<String> answers = new ArrayList<>();

    private int numOfCorrectAnswers = 0;
    /**
     * var to indicate that game has finished
     */
    private boolean isGameFinished = false;
    /**
     * var indicates that first correct answer has been selected
     */
    private boolean firstCorrectAnswer;
    /**
     * var indicates that second correct answer has been selected
     */
    private boolean secondCorrectAnswer;

    /**
     * state of check box to preserve in array when configurations change
     */
    private boolean[] checkBoxState = new boolean[4];


    public MulipleViewModel() {
        questions = new ArrayList<>();
        listOfQuestion();
        shuffleQuestions();

    }

    /**
     * first answer and second in list is always the correct answer
     */
    private void listOfQuestion() {
        questions.add(new Question(" Indian History \n" +
                "The 1513 Battle of Flodden Field was the largest battle (in terms of numbers) ever fought between which two countries?"
                , Arrays.asList("England ",
                "Scotland",
                "America"
                , "France")));
        questions.add(new Question("History about Gautama Buddha\n" +
                "Buddha means?"
                , Arrays.asList("awakened one",
                "enlightened one",
                "Sangha"
                , "Dhamma")));
        questions.add(new Question("academic discipline\n" +
                "What is the limitation of academic discipline?"
                , Arrays.asList("Study limitations",
                "Static concepts",
                "hard Work"
                , "lots of pre-research studies to the concerned topic, et1c")));
        questions.add(new Question("Hundred Years War\n" +
                "War (from 1337 to 1453) was fought between"
                , Arrays.asList("England",
                "Franch",
                "Germany"
                , "Russia")));
    }

    /**
     * randomize the questions so every time user start new game he finds new question at the beginning
     */
    private void shuffleQuestions() {
        Collections.shuffle(questions);
        setCurrentQuestion();

    }

    void setCurrentQuestion() {
        ++questionIndex;
        currentQuestion = questions.get(questionIndex);
        //clear the previous answers list in case user cheesed to try again so he starts with new shuffled list
        answers.clear();
        answers.addAll(currentQuestion.getAnswers());
        //shuffling the answers for every question
        Collections.shuffle(answers);
        resetCheckBox();


    }
    private void resetCheckBox()
    {
        //clear all checked box
        checkBoxState=new boolean[]{false,false,false,false};
    }

    /**
     * check if the user choosed the correct answer or not
     *
     * @param answerIndex is id of checkBox button of selected answer
     */
    private void checkAnswer(int answerIndex) {
        if (answers.get(answerIndex).equals(currentQuestion.getAnswers().get(0))) {
            firstCorrectAnswer = true;
        } else if (answers.get(answerIndex).equals(currentQuestion.getAnswers().get(1))) {
            secondCorrectAnswer = true;
        }
    }

    /**
     * unCheck the previous answer if user unchecked the checkBox
     *
     * @param answerIndex is the id of check Box that has been selected
     */
    private void unCheckAnswer(int answerIndex) {
        if (answers.get(answerIndex).equals(currentQuestion.getAnswers().get(0))) {
            firstCorrectAnswer = false;
        } else if (answers.get(answerIndex).equals(currentQuestion.getAnswers().get(1))) {
            secondCorrectAnswer = false;
        }
    }

    void checkCorrectAnswer() {
        int count=0;
        //before  checking the correct answer i have to check if the user exactly select two answer or not
        for (boolean i :checkBoxState)
        {
            if(i)
                count++;
        }
        if(count==2) {
            if (firstCorrectAnswer && secondCorrectAnswer) {
                ++numOfCorrectAnswers;
            }
        }
    }

    /**
     * check if we finished all the list of question or not
     */
    Boolean nextQuestion() {
        // Advance to the next question
        return questionIndex < questions.size() - 1;
    }

    /**
     * getters for currentQuestion and answers to use it directly in xml through dataBinding
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public List<String> getAnswers() {
        return answers;
    }

    int getNumOfCorrectAnswers() {
        return numOfCorrectAnswers;
    }

    /**
     * reset the game
     */
    void reset() {
        this.questionIndex = -1;
        numOfCorrectAnswers = 0;
        resetCheckBox();

    }

    boolean isGameFinished() {
        return isGameFinished;
    }

    void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

    /**
     * click listener for check box selected
     *
     * @param view is the check box that has been selected
     */
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        // Check which checkbox was clicked
        switch (view.getId()) {
            case R.id.checkBox:
                if (checked) {
                    checkAnswer(0);
                    checkBoxState[0] = true;
                } else {
                    unCheckAnswer(0);
                    checkBoxState[0] = false;
                }
                break;
            case R.id.checkBox1:
                if (checked) {
                    checkAnswer(1);
                    checkBoxState[1] = true;
                } else {
                    unCheckAnswer(1);
                    checkBoxState[1] = false;
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    checkAnswer(2);
                    checkBoxState[2] = true;

                } else {
                    unCheckAnswer(2);
                    checkBoxState[2] = false;
                }

                break;
            case R.id.checkBox3:
                if (checked) {
                    checkAnswer(3);
                    checkBoxState[3] = true;
                } else {
                    unCheckAnswer(3);
                    checkBoxState[3] = false;
                }
                break;
        }
    }

    public boolean[] getCheckBoxState() {
        return checkBoxState;
    }

}
