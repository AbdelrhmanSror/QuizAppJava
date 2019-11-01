package com.example.sunshine.quizapp.singleAnswerQuestion;


import androidx.lifecycle.ViewModel;
import com.example.sunshine.quizapp.model.Question;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SingleViewModel extends ViewModel {

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

    /**
     * var to store number of correct question
     */
    private int numOfCorrectAnswers = 0;

    /**
     * var to indicate that game has finished
     */
    private boolean isGameFinished = false;

    /**
     * var to store the radio button id in case any configurations happened
     */
    private int selectedRadioId=0;


    public SingleViewModel() {
        questions = new ArrayList<>();
        listOfQuestion();
        shuffleQuestions();

    }

    /**
     * first answer in list is always the correct answer
     */
    private void listOfQuestion() {
        questions.add(new Question("US Presidents\n" +
                "Who is the only president to serve more than two terms?"
                , Arrays.asList("Franklin D. Roosevelt",
                "Abraham Lincoln",
                "George Washington"
                , "Ronald Reagan")));
        questions.add(new Question("World Leaders\n" +
                "Who is the current prime minister of the United Kingdom?"
                , Arrays.asList("Boris Johnson",
                "Gordon Brown",
                "David Cameron"
                , "Tony Blair")));
        questions.add(new Question("Politics\n" +
                "How many electoral votes are needed to become president of the United States?"
                , Arrays.asList("270",
                "538",
                "300"
                , "322")));
        questions.add(new Question("Nation\n" +
                "Which African nation gained independence from France in 1962?"
                , Arrays.asList("Algeria",
                "South Africa",
                "Kenya"
                , "Ethiopia")));
        questions.add(new Question("Discovery\n" +
                "What scientist is well known for 'discovering' gravity?"
                , Arrays.asList("Isaac Newton",
                "Albert Einstein",
                "Galileo Galilei"
                , "Aristotle")));
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
        answers.clear();
        answers.addAll(currentQuestion.getAnswers());
        //shuffling the answers for every question
        Collections.shuffle(answers);


    }

    /**
     * check if the user choosed the correct answer or not
     *
     * @param anwerindex is id of radio button of selected answer
     */
    void checkCorrectAnswer(int anwerindex) {
        if (answers.get(anwerindex).equals(currentQuestion.getAnswers().get(0))) {
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
        numOfCorrectAnswers=0;
    }

     boolean isGameFinished() {
        return isGameFinished;
    }

     void setGameFinished(boolean gameFinished) {
        isGameFinished = gameFinished;
    }

     public int getSelectedRadioId() {
        return selectedRadioId;
    }

     void setSelectedRadioId(int selectedRadioId) {
        this.selectedRadioId = selectedRadioId;
    }
}
