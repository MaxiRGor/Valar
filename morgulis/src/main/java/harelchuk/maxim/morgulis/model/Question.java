package harelchuk.maxim.morgulis.model;

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

    @SerializedName("level")
    @Expose
    private int level;

    @SerializedName("in_book")
    @Expose
    private boolean in_book;

    @SerializedName("in_serial")
    @Expose
    private boolean in_serial;

    @SerializedName("category")
    @Expose
    private int category;


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

    public int getCategory() {
        return category;
    }

    public int getLevel() {
        return level;
    }

    public boolean getIn_serial() {
        return in_serial;
    }

    public boolean getIn_book() {
        return in_book;
    }

    public int getRight_answer() {
        return right_answer;
    }

}
