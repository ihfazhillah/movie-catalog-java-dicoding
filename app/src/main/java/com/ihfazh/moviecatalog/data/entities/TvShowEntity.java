package com.ihfazh.moviecatalog.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tv_show")
public class TvShowEntity {

    @PrimaryKey
    @ColumnInfo
    @NonNull
    private String id;

    @ColumnInfo
    private String poster_url;
    @ColumnInfo
    private String title;
    @ColumnInfo
    private String status;
    @ColumnInfo
    private String type;
    @ColumnInfo
    private String overview;
    @ColumnInfo
    private String score;

    @ColumnInfo
    private boolean bookmarked = false;

    public boolean isBookmarked() {
        return bookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        this.bookmarked = bookmarked;
    }

    public TvShowEntity() {
    }

    public TvShowEntity(String poster_url, String title, String status, String type, String overview, String score) {
        this.poster_url = poster_url;
        this.title = title;
        this.status = status;
        this.type = type;
        this.overview = overview;
        this.score = score;
    }

    public TvShowEntity(String id, String poster_url, String title, String status, String type, String overview, String score) {
        this.poster_url = poster_url;
        this.title = title;
        this.status = status;
        this.type = type;
        this.overview = overview;
        this.score = score;
        this.id = id;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
