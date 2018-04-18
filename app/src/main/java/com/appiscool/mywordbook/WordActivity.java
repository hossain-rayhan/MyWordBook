package com.appiscool.mywordbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.appiscool.mywordbook.adapter.WordListAdapter;
import com.appiscool.mywordbook.model.Word;

import java.util.ArrayList;
import java.util.List;

public class WordActivity extends AppCompatActivity {

    ListView lvWord;
    WordListAdapter wordListAdapter;
    List<Word> wordList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        wordList = new ArrayList<Word>();
        Intent intent = getIntent();
        wordList = intent.getParcelableArrayListExtra("wordList");

        lvWord = (ListView)findViewById(R.id.lv_word);
        wordListAdapter = new WordListAdapter(this,R.layout.list_item_word,wordList);

        lvWord.setAdapter(wordListAdapter);

    }

}
