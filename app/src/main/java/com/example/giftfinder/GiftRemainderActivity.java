package com.example.giftfinder;

import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GiftRemainderActivity extends AppCompatActivity {
    private EditText eventNameEditText, giftIdeaEditText;
    private Button selectDateButton, addEventButton;
    private ListView eventListView;
    private Calendar eventCalendar;
    private String selectedDate;

    private List<Event> eventList = new ArrayList<>(); // Initialize the list as an empty list
    private GiftRemainderAdapter adapter; // Adapter for ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_event);

        // Load saved events from SharedPreferences
        loadEventListFromSharedPreferences();

        // Initialize views
        eventNameEditText = findViewById(R.id.eventNameEditText);
        giftIdeaEditText = findViewById(R.id.giftIdeaEditText);
        selectDateButton = findViewById(R.id.selectDateButton);
        addEventButton = findViewById(R.id.addEventButton);
        eventListView = findViewById(R.id.eventListView); // Initialize ListView

        // Initialize calendar for date picking
        eventCalendar = Calendar.getInstance();

        // Setup adapter for ListView
        adapter = new GiftRemainderAdapter(this, eventList);
        eventListView.setAdapter(adapter); // Set adapter to ListView

        selectDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        GiftRemainderActivity.this,
                        (view, year, month, dayOfMonth) -> {
                            eventCalendar.set(Calendar.YEAR, year);
                            eventCalendar.set(Calendar.MONTH, month);
                            eventCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                            selectDateButton.setText(selectedDate);
                        },
                        eventCalendar.get(Calendar.YEAR),
                        eventCalendar.get(Calendar.MONTH),
                        eventCalendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        addEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventName = eventNameEditText.getText().toString().trim();
                String giftIdea = giftIdeaEditText.getText().toString().trim();

                if (!eventName.isEmpty() && selectedDate != null && !giftIdea.isEmpty()) {
                    // Create Event object
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date eventDate = dateFormat.parse(selectedDate);

                        Event event = new Event(eventName, eventDate, giftIdea);

                        // Add event to the list and save it
                        addEventToReminderList(event);
                        Toast.makeText(GiftRemainderActivity.this, "Event added successfully!", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(GiftRemainderActivity.this, "Error in date format", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(GiftRemainderActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveEventListToSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("GiftFinderPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String eventListJson = gson.toJson(eventList); // Convert list to JSON
        editor.putString("eventList", eventListJson);
        editor.apply(); // Save the JSON string
    }

    private void loadEventListFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("GiftFinderPrefs", MODE_PRIVATE);
        String eventListJson = sharedPreferences.getString("eventList", null);

        if (eventListJson != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Event>>() {}.getType();
            eventList = gson.fromJson(eventListJson, type); // Convert JSON back to list
        } else {
            eventList = new ArrayList<>(); // Initialize empty list if no data
        }
    }

    private void addEventToReminderList(Event event) {
        eventList.add(event); // Add new event to the list
        saveEventListToSharedPreferences(); // Save updated list
        adapter.notifyDataSetChanged(); // Refresh ListView to display the new event
        Log.d("GiftReminder", "Event added: " + event.getEventName() + " on " + event.getEventDate());
    }
}
