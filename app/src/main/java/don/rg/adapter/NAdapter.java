package don.rg.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import don.rg.R;
import don.rg.common.Constants;
import don.rg.model.Movie;

/**
 * Created by don on 7/16/16.
 */


public class NAdapter extends PagerAdapter {

    private List<Movie> movies;
    LayoutInflater inflater;
    private Context context;

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    public NAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        TextView movieTitle;
        TextView genre;
        TextView lang;
        ImageView pp;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.detail_item, container,
                false);

        movieTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        genre = (TextView) itemView.findViewById(R.id.tvDetail);
        lang = (TextView) itemView.findViewById(R.id.tvType);
        pp = (ImageView) itemView.findViewById(R.id.ivPP);


        String awal = movies.get(position).getOriginalLanguage();
        String l = null;
        if (awal.equals("en")) {
            l = awal.replace("en", "English").trim();
        }


//        Log.d("HASIL PERUBAHAN", l);

        movieTitle.setText(movies.get(position).getTitle());
//        genre.setText(movies.get(position).getGenreIds().toString());
        genre.setText("Action | Adventure | Fiction");
        lang.setText(l);

        Picasso.with(context).load(Constants.IMAGE_URL + movies.get(position).getPosterPath()).into(pp);
        Log.d("POSTER", movies.get(position).getPosterPath());


        ((ViewPager)container).addView(itemView);
//        return super.instantiateItem(container, position);
        return itemView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((LinearLayout) object);
    }

}

