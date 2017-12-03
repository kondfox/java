import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Lambdas {

  public static void main(String[] args) {

    System.out.println(Arrays.toString(getBiggerThan80s(new Integer[] { 97, 92, 81, 60, 69, 12, 95, 15 })));
    System.out.println(Arrays.toString(getEvens(new Integer[] { 1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14 })));
    System.out.println(getAverageOfOdds(new Integer[] { 1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14 }));
    System.out.println(Arrays.toString(getSquaredPositives(new Integer[] { 1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14 })));
    System.out.println(Arrays.toString(getBiggerSquaredThan20(new Integer[] { 3, 9, 2, 8, 6, 5 })));
    getFrequencyOfNumbers(new Integer[] { 5, 9, 1, 2, 3, 7, 5, 6, 7, 3, 7, 6, 8, 5, 4, 9, 6, 2 })
            .forEach((k, v) -> System.out.println(k + ": " + v));
    getFrequencyOfCharacters("Hello world")
            .forEach((k, v) -> System.out.println(k + ": " + v));
    System.out.println(Arrays.toString(getStringsStartsWithAAndEndsWithI(
            new String[] { "ROME","LONDON","NAIROBI","CALIFORNIA","ZURICH","NEW DELHI","AMSTERDAM","ABU DHABI", "PARIS" })));
    System.out.println(Arrays.toString(getUpperCaseCharacters("Hello World")));
    System.out.println(charArrayToString(new Character[] { 'H', 'e', 'l', 'l', 'o' }));

    List<Fox> foxes = new ArrayList<>(Arrays.asList(
            new Fox("Alpaga", "Pallida", "green"),
            new Fox("Oracle", "Pallida", "blue"),
            new Fox("Please", "Corsac", "green"),
            new Fox("Asbest", "Corsac", "blue"),
            new Fox("Static", "Pallida", "red")
    ));
    System.out.println(getFoxes(foxes, f -> f.getColor().equals("green")));
    System.out.println(getFoxes(foxes, f -> f.getColor().equals("green") && f.getType().equals("Pallida")));
  }

  private static List<Fox> getFoxes(List<Fox> foxes, Predicate<Fox> predicate) {
    List<Fox> greenFoxes = foxes.stream()
            .filter(predicate)
            .collect(Collectors.toList());
    return greenFoxes;
  }

  private static String charArrayToString(Character[] chars) {
    String stringFromCharArray = Arrays.stream(chars)
            .map(Object::toString)
            .collect(Collectors.joining());
    return stringFromCharArray;
  }

  private static Character[] getUpperCaseCharacters(String string) {
    Character[] upperCaseCharacters = string.chars()
            .filter(c -> c >= Character.valueOf('A') && c <= Character.valueOf('Z'))
            .mapToObj(c -> (char) c)
            .toArray(Character[]::new);
    return upperCaseCharacters;
  }

  private static String[] getStringsStartsWithAAndEndsWithI(String[] strings) {
    String[] stringsStartsWithAAndEndsWithWithI = Arrays.stream(strings)
            .filter(s -> s.toLowerCase().startsWith("a") && s.toLowerCase().endsWith("i"))
            .toArray(String[]::new);
    return stringsStartsWithAAndEndsWithWithI;
  }

  private static Map<Character, Long> getFrequencyOfCharacters(String text) {
    Map<Character, Long> frequencyOfCharacters = text.chars()
            .mapToObj(c -> Character.toLowerCase((char) c))
            .collect(groupingBy(Function.identity(), counting()));
    return frequencyOfCharacters;
  }

  private static Map<Integer, Long> getFrequencyOfNumbers(Integer[] numbers) {
    Map<Integer, Long> frequencyOfNumbers = Arrays.stream(numbers)
            .collect(groupingBy(Function.identity(), counting()));
    return frequencyOfNumbers;
  }

  private static Integer[] getBiggerSquaredThan20(Integer[] numbers) {
    Integer[] biggerSquaredThan20 = Arrays.stream(numbers)
            .filter(n -> Math.pow(n, 2) > 20)
            .toArray(Integer[]::new);
    return biggerSquaredThan20;
  }

  private static Integer[] getSquaredPositives(Integer[] numbers) {
    Integer[] squaredPositives = Arrays.stream(numbers)
            .filter(n -> n > 0)
            .map(n -> (int) Math.pow(n, 2))
            .toArray(Integer[]::new);
    return squaredPositives;
  }

  private static double getAverageOfOdds(Integer[] numbers) {
    double averageOfOdds = Arrays.stream(numbers)
            .filter(n -> (n & 1) == 1)
            .mapToDouble(Integer::doubleValue)
            .average()
            .getAsDouble();
    return averageOfOdds;
  }

  private static Integer[] getEvens(Integer[] numbers) {
    Integer[] evenNumbers = Arrays.stream(numbers)
            .filter(n -> (n & 1) == 0)
            .toArray(Integer[]::new);
    return evenNumbers;
  }

  public static Integer[] getBiggerThan80s(Integer[] scores) {
    Integer[] biggerThan80 = Arrays.stream(scores)
            .filter(n -> n > 80)
            .toArray(Integer[]::new);
    return biggerThan80;
  }
}
