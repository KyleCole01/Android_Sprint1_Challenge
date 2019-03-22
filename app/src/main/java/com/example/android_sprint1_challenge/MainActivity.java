package com.example.android_sprint1_challenge;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {
    LinearLayout layoutLinear ;
    Intent fullIntent;
    Button addButton;
    Context context;
    ArrayList movieArray = new ArrayList<String>();
    MovieObject newMovie = new MovieObject();
    public int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       layoutLinear = findViewById(R.id.scroll_view);
        addButton = findViewById(R.id.button_add_movie);
        context = this;

            Intent saveMovie = getIntent();
                newMovie = (MovieObject) saveMovie.getSerializableExtra("newmovie");

                layoutLinear.removeAllViews();


            if(newMovie!=null) {
                movieArray.add(newMovie);
                for (i = 0 ; i < movieArray.size(); ++i) {
                    MovieObject listedMovie = (MovieObject) movieArray.get(i);
                    String listedMovieName = listedMovie.getMovieName();
                    Boolean listedMovieWatched = listedMovie.isViewed();
                    createTextView(listedMovieName, i, listedMovieWatched);
                }
            }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addMovie = new Intent(context, MovieDetails.class);
                int indexValue = i;
                addMovie.putExtra("index", indexValue);
                startActivity(addMovie);




            }
        });



    }
    public TextView createTextView(final String imageText, final int listIndex, final Boolean isViewed){
        fullIntent = new Intent(MainActivity.this, MovieDetails.class);
        TextView        textView        = new TextView (getApplicationContext ());
        layoutLinear.addView ( textView);
        textView.setId ( listIndex );
        textView.setText ( imageText );
        textView.setTextSize(15);
        textView.setPadding ( 10,10,10,10 );
        textView.setWidth ( 200);
        textView.setHeight ( 100 );
        if(isViewed){
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else {textView.setTextColor(getResources().getColor(R.color.colorAccent));}



        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fullIntent.putExtra("index", listIndex);
                fullIntent.putExtra("name", imageText );
                fullIntent.putExtra("isviewed", isViewed);
                startActivity(fullIntent);


            }
        });

        return textView;
    }

}