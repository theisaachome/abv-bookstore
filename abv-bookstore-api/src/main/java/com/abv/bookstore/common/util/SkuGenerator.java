package com.abv.bookstore.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/*
BOOK-20250519-4837
BOOK-20250519-7291
BOOK-20250519-1304
BOOK-20250519-9865
BOOK-20250519-5620

 */
public class SkuGenerator {
    private static final String PREFIX = "BOOK";

    public static String generateSku() {
        // Get current date as YYYYMMDD
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());

        // Generate a random 4-digit number
        Random random = new Random();
        int randomNumber = 1000 + random.nextInt(9000); // between 1000 and 9999

        // Combine parts to form SKU
        return PREFIX + "-" + date + "-" + randomNumber;
    }

}
