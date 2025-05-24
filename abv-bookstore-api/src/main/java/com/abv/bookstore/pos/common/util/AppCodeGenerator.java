package com.abv.bookstore.pos.common.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;

/*
BOOK-20250519-4837
BOOK-20250519-7291
BOOK-20250519-1304
BOOK-20250519-9865
BOOK-20250519-5620
 */
public class AppCodeGenerator {
    private static final String PREFIX = "BOOK";
    private static final String ORDER_PREFIX = "ORD";

    public static String generateSku() {
        // Get current date as YYYYMMDD
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        // Combine parts to form SKU
        return PREFIX + "-" + date + "-" + getNextSequenceNumber();
    }


    public static String generateOrderNumber() {
        // Generate unique order number (e.g., ORD-20241124-001)
        String dateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String sequence = String.format("%03d", getNextSequenceNumber());
        return "ORD-" + dateStr + "-" + sequence;
    }

    private static int getNextSequenceNumber() {
        // In a real application, you'd get this from a database or service
        // Generate a random 4-digit number
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000); // between 1000 and 9999
        return randomNumber;
    }

    public static void main(String[] args) {
        System.out.println( new AppCodeGenerator().generateOrderNumber());
    }

}
