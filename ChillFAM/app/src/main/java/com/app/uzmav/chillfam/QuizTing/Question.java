package com.app.uzmav.chillfam.QuizTing;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {

    private String Question, optn1, optn2, optn3;
    private int answerNmbr;



    //empty constructor


    public Question() {

    }

    //constructor
    public Question(String question, String optn1, String optn2, String optn3, int answerNmbr) {
        Question = question;
        this.optn1 = optn1;
        this.optn2 = optn2;
        this.optn3 = optn3;
        this.answerNmbr = answerNmbr;
    }

    protected Question(Parcel in) {
        Question = in.readString();
        optn1 = in.readString();
        optn2 = in.readString();
        optn3 = in.readString();
        answerNmbr = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Question);
        dest.writeString(optn1);
        dest.writeString(optn2);
        dest.writeString(optn3);
        dest.writeInt(answerNmbr);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getOptn1() {
        return optn1;
    }

    public void setOptn1(String optn1) {
        this.optn1 = optn1;
    }

    public String getOptn2() {
        return optn2;
    }

    public void setOptn2(String optn2) {
        this.optn2 = optn2;
    }

    public String getOptn3() {
        return optn3;
    }

    public void setOptn3(String optn3) {
        this.optn3 = optn3;
    }

    public int getAnswerNmbr() {
        return answerNmbr;
    }

    public void setAnswerNmbr(int answerNmbr) {
        this.answerNmbr = answerNmbr;
    }
}
