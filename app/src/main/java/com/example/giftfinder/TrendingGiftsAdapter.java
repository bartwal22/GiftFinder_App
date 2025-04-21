package com.example.giftfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TrendingGiftsAdapter extends RecyclerView.Adapter<TrendingGiftsAdapter.GiftViewHolder> {

    private List<Gift> giftList;

    // Constructor to initialize the adapter with the gift list
    public TrendingGiftsAdapter(List<Gift> giftList) {
        this.giftList = giftList;
    }

    // Create ViewHolder
    @Override
    public GiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift, parent, false);
        return new GiftViewHolder(view);
    }

    // Bind data to each item
    @Override
    public void onBindViewHolder(GiftViewHolder holder, int position) {
        Gift gift = giftList.get(position);

        // Set data to TextViews
        holder.giftName.setText(gift.getName());
        holder.giftCategory.setText(gift.getCategory());
        holder.giftOccasion.setText(gift.getOccasion());
        holder.giftPrice.setText("₹" + gift.getPrice());
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    // ViewHolder to hold individual gift item views
    public static class GiftViewHolder extends RecyclerView.ViewHolder {
        TextView giftName;
        TextView giftCategory;
        TextView giftOccasion;
        TextView giftPrice;

        public GiftViewHolder(View itemView) {
            super(itemView);

            // Initialize TextViews
            giftName = itemView.findViewById(R.id.giftName);
            giftCategory = itemView.findViewById(R.id.giftCategory);
            giftOccasion = itemView.findViewById(R.id.giftOccasion);
            giftPrice = itemView.findViewById(R.id.giftPrice);
        }
    }
}
