package chapter14;

import java.io.*;

public class QuizCard implements Serializable {

    private String question;
    private String answer;

    public QuizCard(String q, String a) {
        question = q;
        answer = a;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
