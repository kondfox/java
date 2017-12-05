import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EncodedLines {

  public static void main(String[] args) {
    List<String> encodedLines = getFileContent("encoded-lines.txt");
    List<String> decodedLines = decode(encodedLines);
    decodedLines.forEach(System.out::println);
  }

  private static List<String> decode(List<String> encodedLines) {
    return encodedLines.stream()
            .map(l -> l.chars()
                    .mapToObj(c -> Character.toString((char) (c - 1)))
                    .collect(Collectors.joining()))
            .collect(Collectors.toList());
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
