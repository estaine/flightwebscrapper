package com.estaine.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MMM.yyyy HH:mm:ss");

    private String from;
    private String to;
    private String outwardDate;
    private String returnDate;
    private Integer price;

    public Flight(String from, String to, String outwardDate, String returnDate, Integer price) {
        this.from = from;
        this.to = to;
        this.outwardDate = outwardDate;
        this.returnDate = returnDate;
        this.price = price;
        System.out.println(this.toString());
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getOutwardDate() {
        return outwardDate;
    }

    public void setOutwardDate(String outwardDate) {
        this.outwardDate = outwardDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "[" + FORMATTER.format(LocalDateTime.now()) + "]" +
                "\t" + from + " - " + to + "\t" + outwardDate + " - " + returnDate + " = " + price.toString();
    }
}
