package com.example.utils;

import com.example.models.Photographer;

import java.util.List;

public class PhotographerSorter {
    // Bubble sort by rating (descending order)
    public static List<Photographer> sortByRating(List<Photographer> photographers) {
        int n = photographers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (photographers.get(j).getRating() < photographers.get(j + 1).getRating()) {
                    // Swap the photographers
                    Photographer temp = photographers.get(j);
                    photographers.set(j, photographers.get(j + 1));
                    photographers.set(j + 1, temp);
                }
            }
        }
        return photographers;
    }
}