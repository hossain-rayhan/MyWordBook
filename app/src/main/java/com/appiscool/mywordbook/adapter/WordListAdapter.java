package com.appiscool.mywordbook.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appiscool.mywordbook.R;
import com.appiscool.mywordbook.helper.DbManager;
import com.appiscool.mywordbook.model.Word;

import java.util.List;

/**
 * Created by Rayhan on 6/15/2015.
 */
public class WordListAdapter extends ArrayAdapter<Word> {

    private Context context;
    private int layoutResourceId;

    public WordListAdapter(Context context, int layout, List<Word> wordList) {
        super(context, layout, wordList);
        this.context = context;
        layoutResourceId = layout;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Word word = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.tvWord = (TextView) convertView.findViewById(R.id.tv_word);
            holder.tvMeaning = (TextView) convertView.findViewById(R.id.tv_meaning);
            holder.tvSentence = (TextView) convertView.findViewById(R.id.tv_sentence);
            holder.ivFavorite = (ImageView) convertView.findViewById(R.id.iv_favorite);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.tvWord.setText(word.getWord().toUpperCase());
        holder.tvMeaning.setText(word.getMeaning());
        holder.tvSentence.setText(word.getSentence());

        final ViewHolder finalHolder = holder;

        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.iv_favorite:
                        word.setIsFavorite((word.getIsFavorite() + 1) % 2);
                        DbManager.init(context);
                        DbManager.getInstance().updateWord(word);
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView tvWord;
        TextView tvMeaning;
        TextView tvSentence;
        ImageView ivFavorite;
    }
}

