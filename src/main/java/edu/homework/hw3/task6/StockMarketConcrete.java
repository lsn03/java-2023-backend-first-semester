package edu.homework.hw3.task6;

import java.util.PriorityQueue;

public final class StockMarketConcrete implements StockMarket {
    private final PriorityQueue<Stock> stockQueue;

    public StockMarketConcrete() {
        stockQueue = new PriorityQueue<>((stock1, stock2) -> Integer.compare(stock2.getPrice(), stock1.getPrice()));
    }

    @Override
    public void add(Stock stock) {
        stockQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stockQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return stockQueue.peek();
    }
}
