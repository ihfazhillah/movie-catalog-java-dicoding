package com.ihfazh.moviecatalog.utils;

public class TMDBUtils {
    public static String BASE_URL = "https://api.themoviedb.org/3/";
    public static boolean memoryDb = false;

    public static String getFullImagePath(String path){
        return "https://image.tmdb.org/t/p/w500/" + path;
    }
}
