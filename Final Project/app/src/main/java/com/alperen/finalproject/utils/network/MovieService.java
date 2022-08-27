package com.alperen.finalproject.utils.network;

import com.alperen.finalproject.models.GenreModel;
import com.alperen.finalproject.models.PaginationModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Alperen on 26.08.2022.
 */
public interface MovieService {
    @GET("movie/popular")
    Call<PaginationModel> getMoviePage(@Query("api_key") String apiKey, @Query("page") int pagination);

    @GET("genre/movie/list")
    Call<GenreModel> getGenres(@Query("api_key") String apiKey);

}
