package com.alperen.finalproject.utils.network;

import com.alperen.finalproject.models.GenreModel;
import com.alperen.finalproject.models.PaginationModel;
import com.alperen.finalproject.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Alperen on 27.08.2022.
 */
public class AppRepository {
    private static final MovieService movieService = MovieRepository.getInstance().create(MovieService.class);

    public static void getMovies(int page, IQueryStatus status) {
        status.processing();
        movieService.getGenres(Constants.API_KEY).enqueue(new Callback<GenreModel>() {
            @Override
            public void onResponse(Call<GenreModel> call, Response<GenreModel> response) {
                List<GenreModel.Genre> genreList = response.body().getGenres();

                movieService.getMoviePage(Constants.API_KEY, page).enqueue(new Callback<PaginationModel>() {
                    @Override
                    public void onResponse(Call<PaginationModel> call, Response<PaginationModel> response) {
                        if (response.isSuccessful()) {
                            List<Object> resultList = new ArrayList<>();
                            resultList.add(response.body().getResults());
                            resultList.add(genreList);

                            status.success(resultList);
                        } else {
                            status.fail("Failed", response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<PaginationModel> call, Throwable t) {
                        status.fail("Error", t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<GenreModel> call, Throwable t) {
                status.fail("Error", t.getMessage());
            }
        });

    }
}
