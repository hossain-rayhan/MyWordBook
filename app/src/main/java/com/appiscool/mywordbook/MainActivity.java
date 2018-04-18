package com.appiscool.mywordbook;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.appiscool.mywordbook.helper.CSVToDbHelper;
import com.appiscool.mywordbook.helper.DbHelper;
import com.appiscool.mywordbook.helper.DbManager;
import com.appiscool.mywordbook.helper.PreferenceHelper;
import com.appiscool.mywordbook.model.Word;
import com.appiscool.mywordbook.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    DbHelper mDbHelper;
    SQLiteDatabase mDatabase;
    PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceHelper = PreferenceHelper.getInstance(this);

        if (!preferenceHelper.getBoolean(Constant.IS_DB_CREATED)) {
            DbManager.init(this);
            mDbHelper = new DbHelper(getApplicationContext());
            mDatabase = mDbHelper.getWritableDatabase();
            CSVToDbHelper.sBulkInsert(this, R.raw.word_smart_i_bulk, mDatabase);
            preferenceHelper.setBoolean(Constant.IS_DB_CREATED, true);
            Log.e("First Time", "Db Created");
        }


        findViewById(R.id.ll_plate_1).setOnClickListener(this);
        findViewById(R.id.ll_plate_2).setOnClickListener(this);
        findViewById(R.id.ll_plate_3).setOnClickListener(this);
        findViewById(R.id.ll_plate_4).setOnClickListener(this);
        findViewById(R.id.ll_plate_5).setOnClickListener(this);
        findViewById(R.id.ll_plate_6).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_plate_1: {
                Intent alphabetIntent = new Intent(MainActivity.this, AlphabetActivity.class);
                startActivity(alphabetIntent);
                break;
            }
            case R.id.ll_plate_2: {
                Intent intent = new Intent(getApplicationContext(), WordActivity.class);
                List<Word> favoriteWords = new ArrayList<Word>();
                DbManager.init(this);
                favoriteWords = DbManager.getInstance().getFavoriteWords();
                ArrayList<Word> words = new ArrayList<Word>();
                for (Word word : favoriteWords) {
                    words.add(word);
                }
                intent.putParcelableArrayListExtra("wordList", words);
                startActivity(intent);
            }
            default:
                break;

        }
    }
}
