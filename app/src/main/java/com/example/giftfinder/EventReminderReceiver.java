package com.example.giftfinder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class EventReminderReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String eventName = intent.getStringExtra("eventName");
        String giftIdea = intent.getStringExtra("giftIdea");

        // Notify the user with a Toast message or a Notification
        Toast.makeText(context, "Reminder: " + eventName + " - " + giftIdea, Toast.LENGTH_LONG).show();
    }
}
