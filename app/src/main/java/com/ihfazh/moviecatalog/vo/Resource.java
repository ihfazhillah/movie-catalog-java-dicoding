package com.ihfazh.moviecatalog.vo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {
    @NonNull private Status status;
    @Nullable private String message;
    @Nullable private T body;

    public Resource(@NonNull Status status, @Nullable String message, @Nullable T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public static <T> Resource<T> success(@Nullable T body){
        return new Resource<>(Status.SUCCESS, null, body);
    }

    public static <T> Resource<T> error(@Nullable String message){
        return new Resource<>(Status.ERROR, message, null);
    }

    public static <T> Resource<T> loading(){
        return new Resource<>(Status.LOADING, null, null);
    }
}
