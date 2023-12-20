package com.example.mywarehouse;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> cities = List.of("USE", "Netherlands", "UK", "India", "France");

        Comparator<String> c = (a, b) -> a.compareTo(b);
        Comparator<String> cr = c.reversed();
        String joined = cities.stream().sorted().collect(Collectors.joining(", "));

        System.out.println(joined);
    }



}
