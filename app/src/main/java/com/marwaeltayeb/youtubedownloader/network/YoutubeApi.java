package com.marwaeltayeb.youtubedownloader.network;

import com.marwaeltayeb.youtubedownloader.model.YoutubeApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeApi {

    @GET("search")
    Call<YoutubeApiResponse>getVideos(@Query("part") String part, @Query("maxResults") int resultNumbers, @Query("q") String keyword, @Query("key") String key);

}