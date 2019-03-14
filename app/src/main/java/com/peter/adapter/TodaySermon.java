package com.peter.adapter;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class TodaySermon implements Serializable, Parcelable {
    private String verse, verseNarration, thoughtOfDay, prayerOfDay;

    public TodaySermon() {
    }

    public TodaySermon(String verse, String verseNarration, String thoughtOfDay, String prayerOfDay) {
        this.verse = verse;
        this.verseNarration = verseNarration;
        this.thoughtOfDay = thoughtOfDay;
        this.prayerOfDay = prayerOfDay;
    }

    protected TodaySermon(Parcel in) {
        verse = in.readString();
        verseNarration = in.readString();
        thoughtOfDay = in.readString();
        prayerOfDay = in.readString();
    }

    public static final Creator<TodaySermon> CREATOR = new Creator<TodaySermon>() {
        @Override
        public TodaySermon createFromParcel(Parcel in) {
            return new TodaySermon(in);
        }

        @Override
        public TodaySermon[] newArray(int size) {
            return new TodaySermon[size];
        }
    };

    public String getVerse() {
        return verse;
    }

    public void setVerse(String verse) {
        this.verse = verse;
    }

    public String getVerseNarration() {
        return verseNarration;
    }

    public void setVerseNarration(String verseNarration) {
        this.verseNarration = verseNarration;
    }

    public String getThoughtOfDay() {
        return thoughtOfDay;
    }

    public void setThoughtOfDay(String thoughtOfDay) {
        this.thoughtOfDay = thoughtOfDay;
    }

    public String getPrayerOfDay() {
        return prayerOfDay;
    }

    public void setPrayerOfDay(String prayerOfDay) {
        this.prayerOfDay = prayerOfDay;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(verse);
        dest.writeString(verseNarration);
        dest.writeString(thoughtOfDay);
        dest.writeString(prayerOfDay);
    }
}
