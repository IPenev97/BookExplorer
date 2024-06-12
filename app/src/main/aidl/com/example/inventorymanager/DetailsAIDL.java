package com.example.inventorymanager;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DetailsAIDL implements Parcelable {

    private int id;

    private int bookId;
    private String summary;
    private String publishDate;
    private String genre;

    public int getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(int isFavorite) {
        this.isFavorite = isFavorite;
    }

    private int isFavorite;

    public DetailsAIDL() {
    }

    public DetailsAIDL(int id, int bookId, String summary, String publishDate, String genre, int isFavorite) {
        this.id = id;
        this.bookId = bookId;
        this.summary = summary;
        this.publishDate = publishDate;
        this.genre = genre;
        this.isFavorite = isFavorite;
    }

    protected DetailsAIDL(Parcel in) {
        id = in.readInt();
        bookId = in.readInt();
        summary = in.readString();
        publishDate = in.readString();
        genre = in.readString();
        isFavorite = in.readInt();
    }

    public static final Creator<DetailsAIDL> CREATOR = new Creator<DetailsAIDL>() {
        @Override
        public DetailsAIDL createFromParcel(Parcel in) {
            return new DetailsAIDL(in);
        }

        @Override
        public DetailsAIDL[] newArray(int size) {
            return new DetailsAIDL[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(bookId);
        parcel.writeString(summary);
        parcel.writeString(publishDate);
        parcel.writeString(genre);
        parcel.writeInt(isFavorite);
    }


}
