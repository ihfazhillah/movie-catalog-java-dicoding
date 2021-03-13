package com.ihfazh.moviecatalog.data.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVDetail{

	@SerializedName("original_language")
	private String originalLanguage;

	@SerializedName("number_of_episodes")
	private int numberOfEpisodes;

	@SerializedName("networks")
	private List<NetworksItem> networks;

	@SerializedName("type")
	private String type;

	@SerializedName("backdrop_path")
	private Object backdropPath;

	@SerializedName("genres")
	private List<GenresItem> genres;

	@SerializedName("popularity")
	private double popularity;

	@SerializedName("production_countries")
	private List<ProductionCountriesItem> productionCountries;

	@SerializedName("id")
	private int id;

	@SerializedName("number_of_seasons")
	private int numberOfSeasons;

	@SerializedName("vote_count")
	private int voteCount;

	@SerializedName("first_air_date")
	private String firstAirDate;

	@SerializedName("overview")
	private String overview;

	@SerializedName("seasons")
	private List<SeasonsItem> seasons;

	@SerializedName("languages")
	private List<String> languages;

	@SerializedName("created_by")
	private List<CreatedByItem> createdBy;

	@SerializedName("last_episode_to_air")
	private LastEpisodeToAir lastEpisodeToAir;

	@SerializedName("poster_path")
	private String posterPath;

	@SerializedName("origin_country")
	private List<String> originCountry;

	@SerializedName("spoken_languages")
	private List<SpokenLanguagesItem> spokenLanguages;

	@SerializedName("production_companies")
	private List<ProductionCompaniesItem> productionCompanies;

	@SerializedName("original_name")
	private String originalName;

	@SerializedName("vote_average")
	private double voteAverage;

	@SerializedName("name")
	private String name;

	@SerializedName("tagline")
	private String tagline;

	@SerializedName("episode_run_time")
	private List<Integer> episodeRunTime;

	@SerializedName("next_episode_to_air")
	private Object nextEpisodeToAir;

	@SerializedName("in_production")
	private boolean inProduction;

	@SerializedName("last_air_date")
	private String lastAirDate;

	@SerializedName("homepage")
	private String homepage;

	@SerializedName("status")
	private String status;

	public String getOriginalLanguage(){
		return originalLanguage;
	}

	public int getNumberOfEpisodes(){
		return numberOfEpisodes;
	}

	public List<NetworksItem> getNetworks(){
		return networks;
	}

	public String getType(){
		return type;
	}

	public Object getBackdropPath(){
		return backdropPath;
	}

	public List<GenresItem> getGenres(){
		return genres;
	}

	public double getPopularity(){
		return popularity;
	}

	public List<ProductionCountriesItem> getProductionCountries(){
		return productionCountries;
	}

	public int getId(){
		return id;
	}

	public int getNumberOfSeasons(){
		return numberOfSeasons;
	}

	public int getVoteCount(){
		return voteCount;
	}

	public String getFirstAirDate(){
		return firstAirDate;
	}

	public String getOverview(){
		return overview;
	}

	public List<SeasonsItem> getSeasons(){
		return seasons;
	}

	public List<String> getLanguages(){
		return languages;
	}

	public List<CreatedByItem> getCreatedBy(){
		return createdBy;
	}

	public LastEpisodeToAir getLastEpisodeToAir(){
		return lastEpisodeToAir;
	}

	public String getPosterPath(){
		return posterPath;
	}

	public List<String> getOriginCountry(){
		return originCountry;
	}

	public List<SpokenLanguagesItem> getSpokenLanguages(){
		return spokenLanguages;
	}

	public List<ProductionCompaniesItem> getProductionCompanies(){
		return productionCompanies;
	}

	public String getOriginalName(){
		return originalName;
	}

	public double getVoteAverage(){
		return voteAverage;
	}

	public String getName(){
		return name;
	}

	public String getTagline(){
		return tagline;
	}

	public List<Integer> getEpisodeRunTime(){
		return episodeRunTime;
	}

	public Object getNextEpisodeToAir(){
		return nextEpisodeToAir;
	}

	public boolean isInProduction(){
		return inProduction;
	}

	public String getLastAirDate(){
		return lastAirDate;
	}

	public String getHomepage(){
		return homepage;
	}

	public String getStatus(){
		return status;
	}
}