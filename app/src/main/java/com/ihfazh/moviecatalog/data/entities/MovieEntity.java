package com.ihfazh.moviecatalog.data.entities;

public class MovieEntity {
    private String posterUrl;
    private String title;
    private String length;
    private String status;
    private String language;
    private String budget;
    private String overview;
    private String score;
    private String id;

    public MovieEntity() {
    }

    public MovieEntity(String posterUrl, String title, String length, String status, String language, String budget, String overview, String score) {
        this.posterUrl = posterUrl;
        this.title = title;
        this.length = length;
        this.status = status;
        this.language = language;
        this.budget = budget;
        this.overview = overview;
        this.score = score;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
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

    public void setId(String id) {
        this.id = id;
    }



}
