package com.java_8_training.problems.lambdas;


import com.java_8_training.problems.lambdas.model.Apple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class LambdasRefactor {
    public static void main(String[] args) {
        System.out.println(sortInventoryByDecreasingWeight());
        System.out.println(filterGreenApples());
    }

    public static List<Apple> sortInventoryByDecreasingWeight() {
        //TO: refactor to use lambda expression

        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
     /*   inventory.sort(new Comparator<Apple>() {
            @Override
            public int compare(Apple apple1, Apple apple2) {
                return apple2.getWeight().compareTo(apple1.getWeight());
            }
        });
        */
        return inventory;

    }

    public static List<Apple> filterGreenApples() {

        // TOD: refactor to use lambda expression
        // TO: refactor to use standard functional interface

        List<Apple> inventory = asList(new Apple(80, "green"), new Apple(155, "green"), new Apple(120, "red"));



        List<Apple> greenApples = filterApples(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return "green".equals(apple.getColor());
            }
        });

        //aici e cu lambda
        List<Apple> filteredList = filterApples(inventory,(apple)->"green".equals(apple.getColor()));
        return filteredList;
    }

    private static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    interface ApplePredicate {
        boolean test(Apple apple);
    }
}
