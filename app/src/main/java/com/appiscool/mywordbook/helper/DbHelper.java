package com.appiscool.mywordbook.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.appiscool.mywordbook.R;
import com.appiscool.mywordbook.model.Word;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rayhan on 6/13/2015.
 */
public class DbHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "greonei.sqlite";
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private Dao<Word, String> wordDao = null;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Word.class);
        } catch (SQLException e) {
            Log.e(DbHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        List<String> allSql = new ArrayList<String>();

        try {

            TableUtils.clearTable(connectionSource, Word.class);
            CSVToDbHelper.sBulkInsert(context, R.raw.word_smart_i_bulk, sqLiteDatabase);
            Log.e("Upgrade", "Success");

            for (String sql : allSql) {
                sqLiteDatabase.execSQL(sql);
            }
        } catch (Exception e) {
            Log.e(DbHelper.class.getName(), "exception during onUpgrade", e);
            throw new RuntimeException(e);
        }
    }


    public Dao<Word, String> getWordDao() {
        if (null == wordDao) {
            try {
                wordDao = getDao(Word.class);
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        return wordDao;
    }

}
