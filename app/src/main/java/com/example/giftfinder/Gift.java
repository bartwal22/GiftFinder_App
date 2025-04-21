package com.example.giftfinder;

public class Gift {
    private String name;
    private String category;
    private String occasion;
    private int price;
    private String url; // New field for the shopping website link

    // Constructor
    public Gift(String name, String category, String occasion, int price, String url) {
        this.name = name;
        this.category = category;
        this.occasion = occasion;
        this.price = price;
        this.url = url;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getOccasion() {
        return occasion;
    }

    public int getPrice() {
        return price;
    }

    public String getUrl() {
        return url;
    }
}
