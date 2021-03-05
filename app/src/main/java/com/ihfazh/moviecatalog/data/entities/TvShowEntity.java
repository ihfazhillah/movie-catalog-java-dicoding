package com.ihfazh.moviecatalog.data.entities;

public class TvShowEntity {
    private String id;
    private String poster_url;
    private String title;
    private String status;
    private String type;
    private String overview;
    private String score;

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
