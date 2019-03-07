package com.app.uzmav.chillfam.QuizTing;

import android.provider.BaseColumns;

public final class QuizContract {

    private QuizContract(){

    }

    //create an inner class for each diff table in database

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "Quiz_Questions";
        public static final String COLOMN_QUESTION = "Question";
        public static final String OPTION_1 = "option1";
        public static final String OPTION_2 = "option2";
        public static final String OPTION_3 = "option3";
        public static final String COLOMN_ANSWER_NUMB = "AnswerNumb";
        public static final String COLOMN_DIFFICULTY_OPTN = "Difficulty";


    }

}
