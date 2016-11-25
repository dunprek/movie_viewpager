package don.rg.activity.fragment;

/**
 * Created by don on 7/14/16.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

import don.rg.R;
import don.rg.adapter.CAdapter;
import don.rg.api.ApiClient;
import don.rg.api.ApiInterface;
import don.rg.common.Constants;
import don.rg.model.Movie;
import don.rg.model.MoviesResponse;
import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Coming extends Fragment {


    private static final String TAG = Coming.class.getSimpleName();

    private ViewPager viewPager;
    private CAdapter Myadapter;
    private CircleIndicator indicator;

    public Coming() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_now, container, false);


        viewPager = (ViewPager) rootView.findViewById(R.id.view_pager);
        indicator = (CircleIndicator) rootView.findViewById(R.id.indicator);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getUpcomingMovie(Constants.API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                int statusCode = response.code();
                List<Movie> movies = response.body().getResults();
//                Myadapter = new NAdapter(movies, R.layout.detail_item, getActivity());
                Myadapter = new CAdapter(getActivity(), movies);
                viewPager.setAdapter(Myadapter);
                indicator.setViewPager(viewPager);

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }


        });


        return rootView;
    }

}