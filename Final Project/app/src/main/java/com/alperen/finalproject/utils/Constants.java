package com.alperen.finalproject.utils;

import com.alperen.finalproject.utils.network.MovieRepository;
import com.alperen.finalproject.utils.network.MovieService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alperen on 26.08.2022.
 */
public class Constants {
    public static final String API_KEY = "09ac03874512e037c532419ec2ed92e7";
    public static final String POSTER_PATH = "https://image.tmdb.org/t/p/original/";
    public static final String BASE_URL = "https://api.themoviedb.org/3/";
}
