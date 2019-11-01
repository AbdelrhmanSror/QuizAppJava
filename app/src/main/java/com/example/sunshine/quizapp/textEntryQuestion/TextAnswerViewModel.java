package com.example.sunshine.quizapp.textEntryQuestion;

import androidx.lifecycle.ViewModel;
import com.example.sunshine.quizapp.model.Question;
import java.util.ArrayList;
import java.util.Collections;

public class TextAnswerViewModel extends ViewModel {
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
     * var to store number of correct question
     */
    private int numOfCorrectAnswers = 0;

    /**
     * var to indicate that game has finished
     */
    private boolean isGameFinished = false;



    public TextAnswerViewModel() {
        questions = new ArrayList<>();
        listOfQuestion();
        shuffleQuestions();

    }

    /**
     * first answer in list is always the correct answer
     */
    private void listOfQuestion() {
        questions.add(new Question("Adele performed the theme song to which James Bond film?"
                , Collections.singletonList("Skyfall")));
        questions.add(new Question("Other than eggs, what is a primary ingredient in Eggs Florentine?"
                , Collections.singletonList("Spinach")));
        questions.add(new Question("Politics\n" +
                "What was the first successful vaccine developed in history?"
                , Collections.singletonList("Smallpox")));
        questions.add(new Question("Nation\n" +
                "Which African nation gained independence from France in 1962?"
                , Collections.singletonList("Algeria")));
        questions.add(new Question("Discovery\n" +
                "What scientist is well known for 'discovering' gravity?"
                , Collections.singletonList("Isaac Newton")));
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
    }

    /**
     * check if the user choosed the correct answer or not
     *
     * @param answer is string answer user has entered
     */
    void checkCorrectAnswer(String answer) {
        if (answer.toLowerCase().equals(currentQuestion.getAnswers().get(0).toLowerCase())) {
            ++numOfCorrectAnswers;
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
     * getters for currentQuestion to use it directly in xml through dataBinding
     */
    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    int getNumOfCorrectAnswers() {
        return numOfCorrectAnswers;
    }

    /**
     * reset the game
     */
    void reset() {
        this.questionIndex = -1;
        numOfCorrectAnswers=0;
    }

    boolean isGameFinished() {
        return isGameFinished;
    }

    void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

}
