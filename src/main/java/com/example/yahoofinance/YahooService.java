package com.example.yahoofinance;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stock")
public class YahooService {
    List<String> stockCodes = new ArrayList<>();

    @GetMapping("/{stockCode}")
    public void addStock(@PathVariable String stockCode) throws IOException {
        Stock stock = YahooFinance.get(stockCode);

        if (stock != null)
            stockCodes.add(stockCode);
    }

    @DeleteMapping("/{stockCode}")
    public void removeStock(@PathVariable String stockCode) throws IOException {

        int index = stockCodes.indexOf(stockCode);
        if (index != -1) {
            stockCodes.remove(index);
        }
    }

    @GetMapping
    public Map<String, Stock> getRealtimePrices() throws IOException {

        String[] stocks = new String[stockCodes.size()];
        int i = 0;
        for (String code : stockCodes) {
            stocks[i++] = code;
        }
        return YahooFinance.get((stocks));
    }
}
