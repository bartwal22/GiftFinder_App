package com.example.giftfinder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.example.giftfinder.PreferencesActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all card views
        CardView searchCard = findViewById(R.id.card_search);
        CardView preferencesCard = findViewById(R.id.card_preferences);
        CardView giftRemainderCard = findViewById(R.id.card_gift_remainder);
        CardView trendingCard = findViewById(R.id.card_trending);

        // Set onClickListeners for each card
        searchCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Search Activity
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        preferencesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Preferences Activity
                Intent intent = new Intent(MainActivity.this, PreferencesActivity.class);
                startActivity(intent);
            }
        });

        giftRemainderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Gift Remainder Activity (You may need to create this activity)
                Intent intent = new Intent(MainActivity.this, GiftRemainderActivity.class);  // Add your new activity
                startActivity(intent);
            }
        });

        trendingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Trending Gifts Activity
                Intent intent = new Intent(MainActivity.this, TrendingActivity.class);
                startActivity(intent);
            }
        });
    }
}
