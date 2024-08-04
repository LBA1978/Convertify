package com.example.convertify.model;

public class ExchangeRate {
    private String base;
    private String date;
    private Rates rates;

    public String getBase() {
        return base;
    }

    public String getDate() {
        return date;
    }

    public Rates getRates() {
        return rates;
    }

    public double getRate() {
        return rates.getRate();
    }

    public class Rates {
        private double rate;

        public double getRate() {
            return rate;
        }
    }
}
