package com.example.sunshine.quizapp.model;
import java.util.List;

/**
 * class represent the question and its answers which include correct answer and incorrect answers
 */
public class Question {
    private String question;
   private List<String>answers;


    public Question(String question, List<String>answers) {
        this.question = question;
      this.answers=answers;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
