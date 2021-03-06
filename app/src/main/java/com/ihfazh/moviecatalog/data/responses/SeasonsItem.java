package com.ihfazh.moviecatalog.data.responses;

import com.google.gson.annotations.SerializedName;

public class SeasonsItem{

	@SerializedName("air_date")
	private Object airDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("episode_count")
	private int episodeCount;

	@SerializedName("name")
	private String name;

	@SerializedName("season_number")
	private int seasonNumber;

	@SerializedName("id")
	private int id;

	@SerializedName("poster_path")
	private Object posterPath;

	public Object getAirDate(){
		return airDate;
	}

	public String getOverview(){
		return overview;
	}

	public int getEpisodeCount(){
		return episodeCount;
	}

	public String getName(){
		return name;
	}

	public int getSeasonNumber(){
		return seasonNumber;
	}

	public int getId(){
		return id;
	}

	public Object getPosterPath(){
		return posterPath;
	}
}