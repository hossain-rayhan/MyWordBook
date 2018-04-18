package com.appiscool.mywordbook.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.j256.ormlite.field.DatabaseField;

/**
 * Created by Rayhan on 6/13/2015.
 */
public class Word implements Parcelable {

    @DatabaseField(id = true)
    private String word;
    @DatabaseField
    private String meaning;
    @DatabaseField
    private String sentence;
    @DatabaseField
    private int isFavorite;

    public Word() {
    }

    public Word(String word, String meaning, String sentence, int isFavorite) {
        this.word = word;
        this.meaning = meaning;
        this.sentence = sentence;
        this.isFavorite = isFavorite;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(word);
        parcel.writeString(meaning);
        parcel.writeString(sentence);
        parcel.writeInt(isFavorite);
    }

    public Word(Parcel in) {
        word = in.readString();
        meaning = in.readString();
        sentence = in.readString();
        isFavorite = in.readInt();
    }

    public static final Parcelable.Creator<Word> CREATOR = new Parcelable.Creator<Word>() {
        public Word createFromParcel(Parcel in) {
            return new Word(in);
        }

        public Word[] newArray(int size) {
            return new Word[size];
        }
    };
}
