package com.example.alpha;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;


public class Cricket_live_scores implements Parcelable {
    String team1,team2,stat;
    Match m;
    Cricket_live_scores(String team1,String team2,String stat,Match m)
    {
        this.team1=team1;
        this.team2=team2;
        this.stat=stat;
        this.m=m;
    }

    protected Cricket_live_scores(Parcel in) {
        team1 = in.readString();
        team2 = in.readString();
        stat = in.readString();
        m=in.readParcelable(getClass().getClassLoader());
    }

    public static final Parcelable.Creator<Cricket_live_scores> CREATOR = new Parcelable.Creator<Cricket_live_scores>() {
        @Override
        public Cricket_live_scores createFromParcel(Parcel in) {
            return new Cricket_live_scores(in);
        }

        @Override
        public Cricket_live_scores[] newArray(int size) {
            return new Cricket_live_scores[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(team1);
        dest.writeString(team2);
        dest.writeString(stat);
        dest.writeParcelable((Parcelable) m,1);
    }
}
