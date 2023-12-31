import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (
                int i = 0;
                i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        List<Person> teens = persons.stream()
                .filter(x -> x.getAge() <= 17)
                .count();
        System.out.println(teens);


        Stream<Person> recruit = persons.stream()
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MAN)
                .map(x -> x.getFamily())
                .collect(Collectors.toList());
        System.out.println(recruit);

        Stream<Person> workers = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x -> x.getAge() >= 18)
                .filter(x -> x.getAge() <= 60 || x.getSex() == Sex.MAN && x.getAge() <= 65)
                .map(x -> x.getFamily())
                .sorted(Comparator.comparing(x -> x))
                .collect(Collectors.toList());
        System.out.println(workers);


    }
}