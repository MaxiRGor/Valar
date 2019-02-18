package harelchuk.maxim.quizwithmoxy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("id_question")
    @Expose
    private int id_question;

    @SerializedName("question_text")
    @Expose
    private String question_text;

    @SerializedName("answer_one")
    @Expose
    private String answer_one;

    @SerializedName("answer_two")
    @Expose
    private String answer_two;

    @SerializedName("answer_three")
    @Expose
    private String answer_three;

    @SerializedName("answer_four")
    @Expose
    private String answer_four;

    @SerializedName("right_answer")
    @Expose
    private int right_answer;

    @SerializedName("category")
    @Expose
    private String category;


    public int getId_question() {
        return id_question;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public String getAnswer_one() {
        return answer_one;
    }

    public String getAnswer_two() {
        return answer_two;
    }

    public String getAnswer_three() {
        return answer_three;
    }

    public String getAnswer_four() {
        return answer_four;
    }

    public String getCategory() {
        return category;
    }

    public int getRight_answer() {
        return right_answer;
    }

}
