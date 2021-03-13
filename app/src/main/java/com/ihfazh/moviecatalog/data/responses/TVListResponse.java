package com.ihfazh.moviecatalog.data.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVListResponse{

	@SerializedName("page")
	private int page;

	@SerializedName("total_pages")
	private int totalPages;

	@SerializedName("results")
	private List<TVResultItem> results;

	@SerializedName("total_results")
	private int totalResults;

	public int getPage(){
		return page;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public List<TVResultItem> getResults(){
		return results;
	}

	public int getTotalResults(){
		return totalResults;
	}
}