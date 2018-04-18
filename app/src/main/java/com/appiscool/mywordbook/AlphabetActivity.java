package com.appiscool.mywordbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.appiscool.mywordbook.adapter.AlphabetGridAdapter;
import com.appiscool.mywordbook.helper.DbManager;
import com.appiscool.mywordbook.model.Alphabet;
import com.appiscool.mywordbook.model.Word;

import java.util.ArrayList;
import java.util.List;


public class AlphabetActivity extends AppCompatActivity {

    private GridView gridViewAlphabet;
    private AlphabetGridAdapter alphabetAdapter;
    List<Alphabet> alphabetList = new ArrayList<Alphabet>();
    List<Word> allWords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        DbManager.init(this);
        allWords = DbManager.getInstance().getAllWords();
        int[] alphabetCounter = new int[26];
        for (Word word : allWords) {
            alphabetCounter[(int) word.getWord().toUpperCase().charAt(0) - 65]++;
        }
        for (int i = 0; i < 26; i++) {
            alphabetList.add(new Alphabet(Character.toString((char) (i + 65)), String.valueOf(alphabetCounter[i])));
        }

        gridViewAlphabet = (GridView) findViewById(R.id.gv_alphabet);
        alphabetAdapter = new AlphabetGridAdapter(this, R.layout.grid_item_alphabet, alphabetList);
        gridViewAlphabet.setAdapter(alphabetAdapter);

        gridViewAlphabet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ArrayList<Word> words = new ArrayList<Word>();
                char alphabet = (char) (position + 65);
                for (Word word : allWords) {
                    if (word.getWord().toUpperCase().charAt(0) == alphabet) {
                        words.add(word);
                    }
                }

                Intent intent = new Intent(getApplicationContext(), WordActivity.class);

                intent.putParcelableArrayListExtra("wordList", words);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_alphabet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}