package com.example.giftfinder;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class TrendingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Create a list of gifts
        List<Gift> trendingGifts = new ArrayList<>();
        trendingGifts.add(new Gift("Gift 1", "A cool gift for friends.", "Birthday", 100, ""));
        trendingGifts.add(new Gift("Gift 2", "Perfect gift for family.", "Anniversary", 150, ""));
        trendingGifts.add(new Gift("Gift 3", "A lovely gift for special occasions.", "Wedding", 200, ""));
        // Add more gifts...

        // Set the adapter to the RecyclerView
        TrendingGiftsAdapter adapter = new TrendingGiftsAdapter(trendingGifts);
        recyclerView.setAdapter(adapter);
    }
}
