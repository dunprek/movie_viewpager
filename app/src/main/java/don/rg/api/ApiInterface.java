package don.rg.api;

/**
 * Created by don on 7/16/16.
 */

import don.rg.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiInterface {
    @GET("movie/now_playing")
    Call<MoviesResponse> getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<MoviesResponse> getUpcomingMovie(@Query("api_key") String apiKey);
}