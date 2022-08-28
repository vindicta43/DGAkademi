package com.alperen.finalproject.utils.network;

import androidx.lifecycle.LiveData;

import com.alperen.finalproject.models.CastModel;
import com.alperen.finalproject.models.GenresModel;
import com.alperen.finalproject.models.MovieDetailModel;
import com.alperen.finalproject.models.PaginationModel;
import com.alperen.finalproject.utils.Constants;
import com.google.firebase.auth.FirebaseAuth;

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
        movieService.getGenres(Constants.API_KEY).enqueue(new Callback<GenresModel>() {
            @Override
            public void onResponse(Call<GenresModel> call, Response<GenresModel> response) {
                List<GenresModel.Genre> genreList = response.body().getGenres();

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
            public void onFailure(Call<GenresModel> call, Throwable t) {
                status.fail("Error", t.getMessage());
            }
        });
    }

    public static void getMovieDetails(int id, IQueryStatus status) {
        status.processing();
        movieService.getMovieCast(id, Constants.API_KEY).enqueue(new Callback<CastModel>() {
            @Override
            public void onResponse(Call<CastModel> call, Response<CastModel> response) {
                List<CastModel.Cast> castList = response.body().getCast();

                movieService.getMovieDetails(id, Constants.API_KEY).enqueue(new Callback<MovieDetailModel>() {
                    @Override
                    public void onResponse(Call<MovieDetailModel> call, Response<MovieDetailModel> response) {
                        if (response.isSuccessful()) {
                            List<Object> resultList = new ArrayList<>();

                            List<MovieDetailModel> movieDetail = new ArrayList<>();
                            movieDetail.add(response.body());

                            resultList.add(movieDetail);
                            resultList.add(castList);

                            status.success(resultList);
                        } else {
                            status.fail("Failed", response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<MovieDetailModel> call, Throwable t) {
                        status.fail("Failed", t.getMessage());
                    }
                });
            }

            @Override
            public void onFailure(Call<CastModel> call, Throwable t) {
                status.fail("Failed", t.getMessage());
            }
        });
    }

    public static LiveData<Integer> getUserBookmarks() {
        // TODO: yapılacak
        return null;
    }

    public static void signIn(String email, String password, IAuthStatus status) {
        status.processing();
        FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    status.success("signIn", "");
                })
                .addOnFailureListener(e -> {
                    status.fail("Error", e.getMessage());
                });
    }

    public static void signUp(String email, String password, IAuthStatus status) {
        status.processing();
        FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(runnable -> {
                    status.success("signUp", "Signed up successfully");
                })
                .addOnFailureListener(e -> {
                    status.fail("Error", e.getMessage());
                });
    }

    public static void sendResetEmail(String email, IAuthStatus status) {
        status.processing();
        FirebaseAuth.getInstance()
                .sendPasswordResetEmail(email)
                .addOnSuccessListener(runnable -> {
                    status.success("resetEmail", "A reset email has been sent");
                })
                .addOnFailureListener(e -> {
                    status.fail("Error", e.getMessage());
                });
    }
}
