package com.example.android_sprint1_challenge;

import android.content.Context;
import android.content.Intent;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class AddNewMovie extends AppCompatActivity implements Serializable {
    private EditText movieInput;
    private Button saveButton, deleteButton;
    private Switch viewedSwitch;
    //private Boolean switchViewed;
    Context context;
    private MovieEntry entry;
    private String entryString;
    private ArrayList<MovieEntry> entryList ;
    Boolean switchViewed;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_movie);
        viewedSwitch = findViewById(R.id.watched_switch);
        movieInput = findViewById(R.id.movie_edit_text);
        saveButton = findViewById(R.id.button_save);
        deleteButton = findViewById(R.id.button_delete);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final Intent passList = getIntent();
        context = this;

        if (passList.getStringExtra("movieName") != null) {
            switchViewed = passList.getBooleanExtra("movieBoolean",false);
            viewedSwitch.setChecked(switchViewed);
            entryString = passList.getStringExtra("movieName");
            id = passList.getIntExtra("movieId",1213);
            movieInput.setText(entryString);


        }else {
            id = 1213;

            movieInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    entryString = s.toString();

                }
            });

        }


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                passList.putExtra("movieid", id);
                passList.putExtra("moviename", entryString);
                passList.putExtra("movieboolean", viewedSwitch.isChecked());
                setResult(MovieListActivity.RESULT_OK, passList);
                finish();
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = id+5000;
                passList.putExtra("movieid", id);

                setResult(MovieListActivity.RESULT_OK, passList);
                finish();
            }
        });



    }
}
