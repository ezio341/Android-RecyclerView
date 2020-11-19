package com.argadiaz.quiz2.eplapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Team implements Parcelable {
    private String name;
    private List<Player> playerList;
    private int imgId;

    public Team(String name, List<Player> playerList, int imgId) {
        this.name = name;
        this.playerList = playerList;
        this.imgId = imgId;
    }

    protected Team(Parcel in) {
        name = in.readString();
        playerList = in.createTypedArrayList(Player.CREATOR);
        imgId = in.readInt();
    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeTypedList(playerList);
        dest.writeInt(imgId);
    }
}
