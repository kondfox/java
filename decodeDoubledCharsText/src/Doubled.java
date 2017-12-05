import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Doubled {
  public static void main(String[] args) {
    List<String> linesToDecode = getFileContent("duplicated-chars.txt");
    List<String> decodedLines = linesToDecode.stream()
            .map(l -> decode(l))
            .collect(Collectors.toList());
    decodedLines.forEach(System.out::println);
  }

  private static String decode(String lineToDecode) {
    List<Character> charactersOfLine = lineToDecode.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.toList());
    int nth = 2;
    int size = charactersOfLine.size();
    int limit = size / nth + Math.min(size % nth, 1);
    String decodedLine = Stream.iterate(0, i -> i + nth)
            .limit(limit)
            .map(charactersOfLine::get)
            .map(c -> c.toString())
            .collect(Collectors.joining());
    return decodedLine;
  }

  private static List<String> getFileContent(String pathString) {
    List<String> lines = new ArrayList<>();
    try {
      lines = Files.readAllLines(Paths.get(pathString));
    } catch (Exception e) {
      System.out.println("Meh :(");
    }
    return lines;
  }
}
