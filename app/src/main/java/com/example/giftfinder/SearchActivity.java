package com.example.giftfinder;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton, filterButton;
    private Spinner priceSpinner, categorySpinner, occasionSpinner;
    private RecyclerView resultsRecyclerView;
    private GiftAdapter giftAdapter;
    private List<Gift> allGifts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initialize UI elements
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);
        filterButton = findViewById(R.id.filterButton);
        priceSpinner = findViewById(R.id.priceSpinner);
        categorySpinner = findViewById(R.id.categorySpinner);
        occasionSpinner = findViewById(R.id.occasionSpinner);
        resultsRecyclerView = findViewById(R.id.resultsRecyclerView);

        // Initialize the RecyclerView with an empty adapter
        resultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        giftAdapter = new GiftAdapter(new ArrayList<>(),this);
        resultsRecyclerView.setAdapter(giftAdapter);

        // Set up spinners
        setUpSpinners();

        // Create a sample list of all gifts
        allGifts = getAllGifts();

        // Search button functionality
        searchButton.setOnClickListener(v -> {
            String searchQuery = searchEditText.getText().toString().trim();
            if (!searchQuery.isEmpty()) {
                performSearch(searchQuery);
            } else {
                Toast.makeText(SearchActivity.this, "Please enter a search term", Toast.LENGTH_SHORT).show();
            }
        });

        // Filter button functionality
        filterButton.setOnClickListener(v -> {
            String selectedPrice = priceSpinner.getSelectedItem().toString();
            String selectedCategory = categorySpinner.getSelectedItem().toString();
            String selectedOccasion = occasionSpinner.getSelectedItem().toString();
            applyFilters(selectedPrice, selectedCategory, selectedOccasion);
        });
    }

    private void setUpSpinners() {
        String[] priceOptions = {"Select Price", "Under ₹1000", "₹1000 - ₹5000", "₹5000 - ₹10000", "Above ₹10000"};
        String[] categoryOptions = {"Select Category", "Electronics", "Books", "Clothes", "Home Decor", "Toys", "Jewelry", "Sports Equipment"};
        String[] occasionOptions = {"Select Occasion", "Birthday", "Christmas", "Anniversary", "Wedding", "New Year", "Valentine's Day", "Graduation"};

        ArrayAdapter<String> priceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, priceOptions);
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priceSpinner.setAdapter(priceAdapter);

        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoryOptions);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<String> occasionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, occasionOptions);
        occasionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        occasionSpinner.setAdapter(occasionAdapter);
    }

    private List<Gift> getAllGifts() {
        // Add some sample gifts here
        List<Gift> gifts = new ArrayList<>();
        gifts.add(new Gift("Samsung Galaxy S21", "Electronics", "Birthday",  30499,"https://www.amazon.in/samsung-s21/s?k=samsung+s21"));
        gifts.add(new Gift("Redmi Note 10 Pro", "Electronics", "Birthday", 20000,"https://www.amazon.in/s?k=redmi+note+10+pro&adgrpid=75373122187&ext_vrnc=hi&hvadid=590702305616&hvdev=c&hvlocphy=9151791&hvnetw=g&hvqmt=e&hvrand=9601236487767133129&hvtargid=kwd-628863978420&hydadcr=13657_2280191&tag=googinhydr1-21&ref=pd_sl_4d5n981uto_e"));
        gifts.add(new Gift("Apple iPhone 13", "Electronics", "Birthday", 50000,"https://www.amazon.in/s?k=iphone+13&crid=VQVHPU3237IC&sprefix=iphone+13%2Caps%2C495&ref=nb_sb_noss_2"));
        gifts.add(new Gift("Twisted Lies", "Books", "Birthday", 200,"https://www.amazon.in/TWISTED-LIES-TikTok-addictive-romance/dp/034943428X"));
        gifts.add(new Gift("Harry Potter", "Books", "Birthday", 383,"https://www.amazon.in/s?k=%27harry+potter+book&crid=2PZZIX3JHQ5P0&sprefix=harry+potter+book%2Caps%2C358&ref=nb_sb_noss_2"));
        gifts.add(new Gift("Twisted Love", "Books", "Christmas", 200,"https://www.amazon.in/s?k=twisted+love&crid=1ML7JKNRXDP7M&sprefix=twisted+lov%2Caps%2C317&ref=nb_sb_noss_2"));
        gifts.add(new Gift("T-Shirt", "Clothes", "Anniversary", 500,"https://www.amazon.in/s?k=tshirt&crid=2L843PAZKSQFM&sprefix=tshi%2Caps%2C394&ref=nb_sb_noss_2"));
        gifts.add(new Gift("Hoddie", "Clothes", "Anniversary", 800,"https://www.amazon.in/s?k=hoodies+for+men+stylish&crid=2ICCJ5C47ICJT&sprefix=ho%2Caps%2C732&ref=nb_sb_ss_ts-doa-p_2_2"));
        gifts.add(new Gift("Shirt", "Clothes", "Anniversary", 1000,"https://www.amazon.in/s?k=shirtfor+men+stylish&crid=23EDQNP01U6XQ&sprefix=shirtfor+men+stylish%2Caps%2C381&ref=nb_sb_noss_1"));
        gifts.add(new Gift("Coffee Mug", "Home Decor", "Wedding", 300,"https://www.amazon.in/s?k=coffee+mug&adgrpid=59254243656&ext_vrnc=hi&hvadid=381515811048&hvdev=c&hvlocphy=9151791&hvnetw=g&hvqmt=e&hvrand=925213448870063446&hvtargid=kwd-25001970&hydadcr=10532_1908276&tag=googinhydr1-21&ref=pd_sl_5dzczekufr_e"));
        gifts.add(new Gift("Photo-Frame", "Home Decor", "Wedding", 900,"https://www.amazon.in/s?k=photo+frame&adgrpid=60492174898&ext_vrnc=hi&hvadid=381512874195&hvdev=c&hvlocphy=9151791&hvnetw=g&hvqmt=e&hvrand=12809866520934314737&hvtargid=kwd-15941696&hydadcr=1447_1954848&tag=googinhydr1-21&ref=pd_sl_70d02yph41_e"));
        gifts.add(new Gift("Buddha", "Home Decor", "Wedding", 1200,"https://www.amazon.in/s?k=buddha+home+decor&crid=1UNUIJM6SKQ8G&sprefix=buddha+home+deco%2Caps%2C357&ref=nb_sb_noss_2"));

        // Add more gifts as needed
        return gifts;
    }

    private void performSearch(String query) {
        // Convert query to lowercase for case-insensitive search
        String queryLower = query.toLowerCase();

        // Filter gifts based on search query
        List<Gift> filteredList = new ArrayList<>();
        for (Gift gift : allGifts) {
            // Check if the gift name or category matches the search query (case insensitive)
            if (gift.getName().toLowerCase().contains(queryLower) || gift.getCategory().toLowerCase().contains(queryLower)) {
                filteredList.add(gift);
            }
        }

        // Update the RecyclerView with the filtered list
        giftAdapter.updateGiftList(filteredList);
    }


    private void applyFilters(String selectedPrice, String selectedCategory, String selectedOccasion) {
        // Filter gifts based on selected filters
        List<Gift> filteredList = new ArrayList<>();

        for (Gift gift : allGifts) {
            // Apply price filter
            boolean priceMatch = false;
            if (selectedPrice.equals("Select Price")) {
                priceMatch = true;
            } else if (selectedPrice.equals("Under ₹1000") && gift.getPrice() < 1000) {
                priceMatch = true;
            } else if (selectedPrice.equals("₹1000 - ₹5000") && gift.getPrice() >= 1000 && gift.getPrice() <= 5000) {
                priceMatch = true;
            } else if (selectedPrice.equals("₹5000 - ₹10000") && gift.getPrice() > 5000 && gift.getPrice() <= 10000) {
                priceMatch = true;
            } else if (selectedPrice.equals("Above ₹10000") && gift.getPrice() > 10000) {
                priceMatch = true;
            }

            // Apply category filter
            boolean categoryMatch = selectedCategory.equals("Select Category") || gift.getCategory().equals(selectedCategory);

            // Apply occasion filter
            boolean occasionMatch = selectedOccasion.equals("Select Occasion") || gift.getOccasion().equals(selectedOccasion);

            // If all filters match, add the gift to the filtered list
            if (priceMatch && categoryMatch && occasionMatch) {
                filteredList.add(gift);
            }
        }

        // Update the RecyclerView with the filtered list
        giftAdapter.updateGiftList(filteredList);
    }
}
