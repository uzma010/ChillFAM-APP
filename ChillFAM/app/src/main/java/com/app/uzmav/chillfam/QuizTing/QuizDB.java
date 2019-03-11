//QuizDB



package com.app.uzmav.chillfam.QuizTing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class QuizDB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db; // hold ref to the database



    public QuizDB(@Nullable Context context) { //, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) { // will only call on the first time creating the database

        this.db = db;

        // sql commands

        final String SQL_CREATE_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLOMN_QUESTION+ " TEXT, " +
                QuizContract.QuestionsTable.OPTION_1 + " TEXT, " +
                QuizContract.QuestionsTable.OPTION_2 + " TEXT, " +
                QuizContract.QuestionsTable.OPTION_3 + " TEXT, " +
                QuizContract.QuestionsTable.COLOMN_ANSWER_NUMB + " INTEGER, " +
                QuizContract.QuestionsTable.COLOMN_DIFFICULTY_OPTN + " TEXT" +
                ")";

        db.execSQL(SQL_CREATE_TABLE);
        fillQuestionsTable();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);

        // change the version and update the onCreate with anther column if needed

    }



    private void fillQuestionsTable() {
        //Manual Write Questions
        // q1-
        Question  q1 = new Question("EASY: A is correct", "A", "B", "C",1, Question.DIFF_EASY); // question, 3 objects and the correct answer and a difficulty level
        AddQuestion(q1);

        // q2-
        Question  q2 = new Question("EASY: B is correct", "A", "B", "C",2, Question.DIFF_EASY); // question, 3 objects and the correct answer and a difficulty level
        AddQuestion(q2);

        // q3-
        Question  q3 = new Question("MEDIUM: B is correct", "A", "B", "C",2, Question.DIFF_MED); // question, 3 objects and the correct answer and a difficulty level
        AddQuestion(q3);

        // q4-
        Question  q4 = new Question("MEDIUM: A is correct", "A", "B", "C",1, Question.DIFF_MED); // question, 3 objects and the correct answer and a difficulty level
        AddQuestion(q4);

        // q5-
        Question  q5 = new Question("HARD: A is correct", "A", "B", "C",1, Question.DIFF_HARD); // question, 3 objects and the correct answer and a difficulty level
        AddQuestion(q5);

        // q6-
        Question  q6 = new Question("HARD: C is correct", "A", "B", "C",3, Question.DIFF_HARD); // question, 3 objects and the correct answer and a difficulty level
        AddQuestion(q6);


    }

    private void AddQuestion(Question Question ){

        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLOMN_QUESTION, Question.getQuestion() );
        cv.put(QuizContract.QuestionsTable.OPTION_1, Question.getOptn1() );
        cv.put(QuizContract.QuestionsTable.OPTION_2, Question.getOptn2() );
        cv.put(QuizContract.QuestionsTable.OPTION_3, Question.getOptn3() );
        cv.put(QuizContract.QuestionsTable.COLOMN_ANSWER_NUMB, Question.getAnswerNmbr() );
        cv.put(QuizContract.QuestionsTable.COLOMN_DIFFICULTY_OPTN, Question.getDifficulty() );

        //insert values to db
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);


    }


    public ArrayList<Question> getAllTheQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        // reference db to get data
        db = getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (cs.moveToFirst()){ // move to first entry if exsists and fill it
            do{
                Question question = new Question();
                question.setQuestion(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.COLOMN_QUESTION)));
                question.setOptn1(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.OPTION_1)));
                question.setOptn2(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.OPTION_2)));
                question.setOptn3(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.OPTION_3)));
                question.setAnswerNmbr(cs.getInt(cs.getColumnIndex(QuizContract.QuestionsTable.COLOMN_ANSWER_NUMB)));
                question.setDifficulty(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.COLOMN_DIFFICULTY_OPTN)));


                questionList.add(question);

            }while(cs.moveToNext()); // then move to the next one if exsists

        }

        cs.close();
        return questionList;
    }


    public ArrayList<Question> getDiffQuestions(String difficulty){
        ArrayList<Question> questionList = new ArrayList<>();
        // reference db to get data
        db = getReadableDatabase();
        //change the way we access our date to only get want is specified in the difficulty level.
        String[] selectionDiff = new String[]{difficulty}; // new string array with data only in the same difficulty level for our database query
        Cursor cs = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME + " WHERE " + QuizContract.QuestionsTable.COLOMN_DIFFICULTY_OPTN + " = ?", selectionDiff );

        if (cs.moveToFirst()){ // move to first entry if exsists and fill it
            do{
                Question question = new Question();
                question.setQuestion(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.COLOMN_QUESTION)));
                question.setOptn1(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.OPTION_1)));
                question.setOptn2(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.OPTION_2)));
                question.setOptn3(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.OPTION_3)));
                question.setAnswerNmbr(cs.getInt(cs.getColumnIndex(QuizContract.QuestionsTable.COLOMN_ANSWER_NUMB)));
                question.setDifficulty(cs.getString(cs.getColumnIndex(QuizContract.QuestionsTable.COLOMN_DIFFICULTY_OPTN)));

                questionList.add(question);

            }while(cs.moveToNext()); // then move to the next one if exsists

        }

        cs.close();
        return questionList;
    }


}



