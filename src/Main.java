import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );

        }

        long count = persons.stream()
                .filter(person -> person.getAge() <= 18)
                .count();
        System.out.println("Несовершеннолетних: " + count);

        System.out.println("Призывники:");
        persons.stream()
                .filter(person -> person.getAge() >= 18 && person.getAge() <= 27)
                .filter(person -> person.getSex().name().equals("MAN"))
                .map(person -> person.getFamily())
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Потенциально работающие:");
        persons.stream()
                .filter(person -> person.getAge() >= 18
                        && person.getAge() <= (Sex.MAN == person.getSex() ? 65 : 60))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
