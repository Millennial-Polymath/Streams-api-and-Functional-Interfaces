package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        List<String> listOfStrings = Arrays.asList("James", "Joseph", "Jonathan", "Joram"); // used arrays as list due to mutability

        testConsumer();
        System.out.println();
        testConsumerWithStreams();
        System.out.println();
        testPredicate();
        System.out.println();
        testPredicateWithStreams();
        System.out.println();
        testPredicateWithStrings();
        System.out.println();
        testLambda(listOfStrings);

        List<String> filtered =
                    listOfStrings
                            .stream()
                            .filter(a -> a.contains("A"))
                            .collect(Collectors.toList());

        System.out.println(filtered);

        Predicate<String> containsA = str2 -> str2.contains("A");

       Runnable run =  () -> {
            System.out.println(
             listOfStrings
                     .stream()
                     .filter(containsA.and(s -> s.length() > 5))
                     .collect(Collectors.toList()));
        };

       Thread thread = new Thread(run);
       thread.start();





    }


    public static void testConsumer() {
        Consumer<String> printStrings = s -> System.out.println(s);

        Stream streamOfStr = Stream.of("Bernard", "Muchami", "Ngotho");

        streamOfStr.forEach(printStrings);
    }

    public static void testConsumerWithStreams() {
        List<String> listOfNames = Arrays.asList("John", "Joseph", "Jonathan", "Joram"); // used arrays as list due to mutability

        //create a consumer that converts each string to uppercase

        Consumer<List<String>> convertToUpper = list -> {
            for (int i = 0; i < list.size(); i++) {
                list.set(i, list.get(i).toUpperCase());
            }
        };

        Consumer<List<String>> convertForEach = list -> {
            for (String str : list) {
                str.toUpperCase();
            }
        };
        Consumer<List<String>> printConvertedStrings = list -> {
            list.stream().forEach(System.out::println);
        };

        convertToUpper.andThen(printConvertedStrings).accept(listOfNames);
        System.out.println();
        convertForEach.andThen(printConvertedStrings).accept(listOfNames);
    }

    public static void testPredicate() {

        Predicate<Integer> oddOrEven = number -> number % 2 == 0;

        for (int i = 1; i <= 15; i++) {
            if (oddOrEven.test(i)) {
                System.out.println(i + " is even");
            } else {
                System.out.println(i + " is odd");
            }
        }
    }

    public static void testPredicateWithStreams() {
        Predicate<Integer> testDivisibleByThree = number -> number % 3 == 0;

        IntStream.range(1, 15)
                .mapToObj(i -> {
                    if (testDivisibleByThree.test(i)) {
                        return i + " is divisible by 3";
                    } else {
                        return i + " is not divisible by 3";
                    }
                })
//                .filter(null)
                .forEach(System.out::println);
    }

    static void testPredicateWithStrings() {

        List<String> names = Arrays.asList("John", "Ben", "Anne", "James", "Hellen");

        Predicate<String> namesContainingN = str -> str.contains("n");

        names.stream().filter(namesContainingN).forEach(System.out::println);
    }

    public static void testLambda(List<String> listOfStrings) {

        Consumer<List<String>> convertToUpper = new Consumer<List<String>>() {
            @Override
            public void accept(List<String> strings) {
                for (int i = 0; i < listOfStrings.size(); i++) {
                    listOfStrings.set(i, listOfStrings.get(i).toUpperCase());
                }
            }
        };
        convertToUpper.accept(listOfStrings);

        for (String str : listOfStrings) {
            System.out.println(str);
        }
    }
}

