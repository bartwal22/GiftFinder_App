package com.example.giftfinder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

public class GiftRemainderAdapter extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> eventList;

    public GiftRemainderAdapter(Context context, List<Event> eventList) {
        super(context, 0, eventList);
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the view if it is null
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        }

        // Get the event at the current position
        Event event = getItem(position);

        // Get references to the TextViews
        TextView eventNameTextView = convertView.findViewById(R.id.eventNameTextView);
        TextView eventDateTextView = convertView.findViewById(R.id.eventDateTextView);
        TextView giftIdeaTextView = convertView.findViewById(R.id.giftIdeaTextView);

        // Set the text for each TextView
        eventNameTextView.setText(event.getEventName());
        eventDateTextView.setText(new SimpleDateFormat("yyyy-MM-dd").format(event.getEventDate())); // Format date to "yyyy-MM-dd"
        giftIdeaTextView.setText(event.getGiftIdea());

        return convertView;
    }
}
