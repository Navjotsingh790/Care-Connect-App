package com.mastercoding.healthcareapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mastercoding.healthcareapp.Models.NewsApiResponse;
import com.mastercoding.healthcareapp.Models.NewsHeadlines;

import java.util.List;

public class HomePage extends AppCompatActivity implements SelectListener, View.OnClickListener{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    Button b1,b2,b3,b4,b5,b6,b7;

    SearchView searchView;

//    ProgressBar dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
//        dialog = new ProgressBar(this);
//        dialog.setText("Fetching news articles..");
//        dialog.show();

        b1 = findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2 = findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3 = findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4 = findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5 = findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6 = findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7 = findViewById(R.id.btn_7);
        b7.setOnClickListener(this);

        searchView = findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                RequestManager manager = new RequestManager(HomePage.this);
                manager.getNewsHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general", null);
    }

    private  final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            showNews(list);
          //dialog.dismiss();
        }

        @Override
        public void onError(String message) {
            Toast.makeText(getApplicationContext(),"An Error Occured",Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(HomePage.this, DetailsActivity.class)
                .putExtra("data",headlines));

    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, category, null);
    }
}