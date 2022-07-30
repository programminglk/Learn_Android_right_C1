package com.example.programminglknotebook.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String userName="";
    private String header="";
    private String content="";
    private int id = -1;

    protected Note(Parcel in) {
        userName = in.readString();
        header = in.readString();
        content = in.readString();
        id = in.readInt();
    }

    public Note() {

    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(header);
        parcel.writeString(content);
        parcel.writeInt(id);
    }
}
