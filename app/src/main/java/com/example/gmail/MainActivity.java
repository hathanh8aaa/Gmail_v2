package com.example.gmail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Gmail> gmails;
    List<Gmail> favourite_gmails;
    RecyclerView recyclerView;
    boolean isShowingFavourite = false;
    GmailAdapter adapter;
    EditText txtSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gmails = new ArrayList<>();
        favourite_gmails = new ArrayList<>();
        gmails.add(new Gmail("CS50's Introduction.","10:02 AM","Keep up the momentum!",
                "Many edX learners in CS50's Introduction to Artificial Intelligence with Python are completing more problems every week"));
        gmails.add(new Gmail("Team from Kaggle", "1:49PM","COVID-19 Competition","Hi @hathanh8aaa,\n" +
                "The primary goal of Kaggle’s COVID-19 effort is to find factors that impact the transmission of COVID-19" ));
        gmails.add(new Gmail("Villa", "2:05 AM", "Summer 2020","Hi, Can we have a conversation?"));
        gmails.add(new Gmail("Eden Hazard", "9:12 AM", "Summer 2020","I will send your ticket soon!"));
        gmails.add(new Gmail("FPT Telecom", "6:14 PM", "Please pay your bill","Your internet bill is out of date. Please pay you bill before 02/04/2020. Contact us for more information"));
        gmails.add(new Gmail("Dropbox", "1:00 AM", "Dropbox Paper","Create and edit in real time: Invite your team to collaborate in Paper. Edits appear instantly and changes are saved so you can always go back to previous versions."));
        gmails.add(new Gmail("Fahasa", "8:00 PM", "Hai So Phan","Your order is transporting. Please wait for about 2 - 3 days more"));
        gmails.add(new Gmail("Sherlock H", "Summer 2020","1:05 AM", "Never mind !"));
        gmails.add(new Gmail("Facebook", "12:19 PM", "Come back with us !","We miss you, G1!. Just come and see what are your friend talking about?"));

        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new GmailAdapter(gmails);
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btn_favourite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isShowingFavourite = !isShowingFavourite;
                if(isShowingFavourite){
                    adapter.showFavourite();
                }else{
                    adapter.showAll();
                }

            }
        });

        txtSearch = findViewById(R.id.text_search);
        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if(keyword.length() > 3){
                    adapter.search(keyword);
                }else adapter.showAll();
            }
        });



    }
}
