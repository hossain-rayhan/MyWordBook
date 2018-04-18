package com.appiscool.mywordbook.helper;

import android.content.Context;

import com.appiscool.mywordbook.model.Word;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayhan on 6/13/2015.
 */
public class DbManager {
    static private DbManager instance;
    private DbHelper helper;

    private DbManager(Context ctx) {
        helper = new DbHelper(ctx);
    }

    static public void init(Context ctx) {
        if (null == instance) {
            instance = new DbManager(ctx);
        }
    }

    static public DbManager getInstance() {
        return instance;
    }

    private DbHelper getHelper() {
        return helper;
    }

    public List<Word> getAllWords() {
        List<Word> wordList = null;
        try {
            wordList = getHelper().getWordDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wordList;
    }

    public void updateWord(Word word) {
        try {
            getHelper().getWordDao().update(word);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Word> getFavoriteWords() {
        List<Word> wordList = new ArrayList<Word>();
        QueryBuilder<Word, String> hospitalQueryBuilder = getHelper().getWordDao().queryBuilder();
        Where<Word, String> where = hospitalQueryBuilder.where();
        try {
            where.eq("isFavorite", "1");
            wordList = hospitalQueryBuilder.query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wordList;
    }

}

