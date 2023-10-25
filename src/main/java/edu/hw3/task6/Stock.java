package edu.hw3.task6;

public class Stock {
    private final int price;
    private final String company;

    public Stock(int price, String company) {
        this.price = price;
        this.company = company;
    }

    public int getPrice() {
        return price;
    }

    public String getCompany() {
        return company;
    }
}
