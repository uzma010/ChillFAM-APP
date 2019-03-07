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
                QuizContract.QuestionsTable.COLOMN_ANSWER_NUMB + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_TABLE);
        fillQuestionsTable();

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);

        // change the version and update the oncreate with anther colomn if needed

    }



    private void fillQuestionsTable() {
        //Manual Write Questions
        // q1-
        Question  q1 = new Question("A is correct", "A", "B", "C",1); // question, 3 objects and the correct answer
        AddQuestion(q1);
        //Q2
        Question  q2 = new Question("B is correct", "A", "B", "C",2); // question, 3 objects and the correct answer
        AddQuestion(q2);

        //Q3
        Question  q3 = new Question(" C is correct", "A", "B", "C",3); // question, 3 objects and the correct answer
        AddQuestion(q3);
        //Q4
        Question  q4 = new Question("A is correct", "A", "B", "C",1); // question, 3 objects and the correct answer
        AddQuestion(q4);

        //Q5
        Question  q5 = new Question("B is correct", "A", "B", "C",2); // question, 3 objects and the correct answer
        AddQuestion(q5);
    }

    private void AddQuestion(Question Question ){

        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLOMN_QUESTION, Question.getQuestion() );
        cv.put(QuizContract.QuestionsTable.OPTION_1, Question.getOptn1() );
        cv.put(QuizContract.QuestionsTable.OPTION_2, Question.getOptn2() );
        cv.put(QuizContract.QuestionsTable.OPTION_3, Question.getOptn3() );
        cv.put(QuizContract.QuestionsTable.COLOMN_ANSWER_NUMB, Question.getAnswerNmbr() );
        //insert values to db
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);


    }


    public ArrayList<Question> getAllTheQuestions(){
        ArrayList<Question> questionList = new ArrayList<>();
        // refernce db to get data
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

                questionList.add(question);

            }while(cs.moveToNext()); // then move to the next one if exsists

        }

        cs.close();
        return questionList;
    }



}



