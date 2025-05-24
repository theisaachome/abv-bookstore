package com.abv.bookstore.pos.common.util;

public class SlugGenerator {
    public static String toSlug(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9\\s]", "")
                .replaceAll("\\s+", "-")
                .replaceAll("^-+|-+$", "");
    }

    public static void main(String[] args) {
        String title = "Java in Action!";
        String slug = toSlug(title);
        System.out.println("Slug: " + slug);  // Expected: Output: java-in-action
    }
}
