package com.appiscool.mywordbook.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Rayhan on 6/13/2015.
 */
public class CSVToDbHelper {
    public static void sBulkInsert(Context ctx, int resourceId, SQLiteDatabase mDatabase) {
        InputStream mInsertStream;
        BufferedReader mInsertReader;
        try {
            mInsertStream = ctx.getResources().openRawResource(resourceId);
            mInsertReader = new BufferedReader(new InputStreamReader(mInsertStream));
            while (mInsertReader.ready()) {
                String insertStmt = mInsertReader.readLine();
                mDatabase.execSQL(insertStmt);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
