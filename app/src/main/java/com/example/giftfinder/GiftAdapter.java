package com.example.giftfinder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.GiftViewHolder> {

    private List<Gift> giftList;
    private Context context;

    public GiftAdapter(List<Gift> giftList, Context context) {
        this.giftList = giftList;
        this.context = context;
    }

    @NonNull
    @Override
    public GiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gift, parent, false);
        return new GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiftViewHolder holder, int position) {
        Gift gift = giftList.get(position);

        // Bind data to TextViews
        holder.nameTextView.setText(gift.getName());
        holder.categoryTextView.setText(gift.getCategory());
        holder.occasionTextView.setText(gift.getOccasion());
        holder.priceTextView.setText("₹" + gift.getPrice());

        // Handle "Buy Now" button click
        holder.buyNowButton.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(gift.getUrl()));
            context.startActivity(browserIntent);
        });
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    public void updateGiftList(List<Gift> newGiftList) {
        this.giftList = newGiftList;
        notifyDataSetChanged();
    }

    public static class GiftViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, categoryTextView, occasionTextView, priceTextView;
        Button buyNowButton;

        public GiftViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.giftName);
            categoryTextView = itemView.findViewById(R.id.giftCategory);
            occasionTextView = itemView.findViewById(R.id.giftOccasion);
            priceTextView = itemView.findViewById(R.id.giftPrice);
            buyNowButton = itemView.findViewById(R.id.buyNowButton); // Button initialization
        }
    }
}
