package com.appiscool.mywordbook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.appiscool.mywordbook.R;
import com.appiscool.mywordbook.model.Alphabet;

import java.util.List;

/**
 * Created by Rayhan on 6/13/2015.
 */
public class AlphabetGridAdapter extends ArrayAdapter<Alphabet> {

    private Context context;
    private int layoutResourceId;

    public AlphabetGridAdapter(Context context, int layout, List<Alphabet> hospitals) {
        super(context, layout, hospitals);
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
        final Alphabet alphabet = getItem(position);
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    layoutResourceId, parent, false);
            holder = new ViewHolder();

            holder.tvAlphabet = (TextView) convertView.findViewById(R.id.tv_alphabet);
            holder.tvCount = (TextView) convertView.findViewById(R.id.tv_count);

            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        holder.tvAlphabet.setText(alphabet.getAlphabet());
        holder.tvCount.setText("(" + alphabet.getCount() + ")");

        convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 150));
        return convertView;
    }

    private class ViewHolder {
        TextView tvAlphabet;
        TextView tvCount;
    }

}
