package com.ihfazh.moviecatalog.data.responses;

import com.google.gson.annotations.SerializedName;

public class CreatedByItem{

	@SerializedName("gender")
	private int gender;

	@SerializedName("credit_id")
	private String creditId;

	@SerializedName("name")
	private String name;

	@SerializedName("profile_path")
	private String profilePath;

	@SerializedName("id")
	private int id;

	public int getGender(){
		return gender;
	}

	public String getCreditId(){
		return creditId;
	}

	public String getName(){
		return name;
	}

	public String getProfilePath(){
		return profilePath;
	}

	public int getId(){
		return id;
	}
}