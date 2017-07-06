
package com.java_8_training.problems.collectors;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Comparator;
import java.util.IntSummaryStatistics;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.summarizingInt;
import static junit.framework.Assert.assertEquals;

@Ignore
public class ArithmeticAndReducingCollectorsTest {

    // See: Dish.menu.stream()

    @Test
    public void leastCaloricDishMEAT() {
        //TOD #C5
        Dish leastCaloricMEAT = new Dish();
        leastCaloricMEAT= Dish.menu.stream().filter(a->a.getType()==Dish.Type.MEAT).collect(minBy(Comparator.comparingInt(Dish::getCalories))).get();
        assertEquals("chicken", leastCaloricMEAT.getName());
    }

    @Test
    public void statisticsForVegetarianDishes() {
        //TOD #C5
        IntSummaryStatistics vegetarianStats = new IntSummaryStatistics();
        vegetarianStats = Dish.menu.stream().filter(Dish::isVegetarian).collect(summarizingInt(Dish::getCalories));
        assertEquals(4, vegetarianStats.getCount());
        assertEquals(1550, vegetarianStats.getSum());
        assertEquals(120, vegetarianStats.getMin());
        assertEquals(387.5, vegetarianStats.getAverage());
        assertEquals(550, vegetarianStats.getMax());

    }
}
