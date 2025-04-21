package com.example.giftfinder;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class PreferencesActivity extends AppCompatActivity {
    private CheckBox notificationCheckBox, darkModeCheckBox;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);

        // Initialize views
        notificationCheckBox = findViewById(R.id.checkboxNotification);
        darkModeCheckBox = findViewById(R.id.checkboxDarkMode);
        saveButton = findViewById(R.id.btnSavePreferences);

        // Load saved preferences if any
        loadPreferences();

        // Save preferences when the button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save preferences to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("notifications_enabled", notificationCheckBox.isChecked());
                editor.putBoolean("dark_mode_enabled", darkModeCheckBox.isChecked());
                editor.apply();

                // Apply dark mode if the checkbox is checked
                if (darkModeCheckBox.isChecked()) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

                // Show Toast message for Notification Checkbox
                if (notificationCheckBox.isChecked()) {
                    Toast.makeText(PreferencesActivity.this, "Notifications Enabled", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PreferencesActivity.this, "Notifications Disabled", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Method to load saved preferences
    private void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE);
        boolean notificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", false);
        boolean darkModeEnabled = sharedPreferences.getBoolean("dark_mode_enabled", false);

        // Set the loaded preferences to the views
        notificationCheckBox.setChecked(notificationsEnabled);
        darkModeCheckBox.setChecked(darkModeEnabled);

        // Apply dark mode if enabled
        if (darkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}
