import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Minesweeper {

  public static final List<String> ALPHABET = new ArrayList<>(Arrays.asList(
          "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
  ));
  public static final int MINE = 9;


  public static void main(String[] args) {

    int mapWidth = 10;
    int mapHeight = 10;

    int[][] map = createMap(mapWidth, mapHeight);
    printMap(map);

  }

  private static void printMap(int[][] map) {
    printMapHeader(map);
    printMapBody(map);
  }

  private static void printMapBody(int[][] map) {
    for (int i = 0; i < map.length; i++) {
      System.out.print(ALPHABET.get(i) + "\t");
      for (int j = 0; j < map[i].length; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
  }

  private static void printMapHeader(int[][] map) {
    System.out.print("\t");
    for (int i = 1; i <= map[0].length; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  private static int[][] createMap(int mapWidth, int mapHeight) {
    int[][] map = new int[mapHeight][mapWidth];
    generateMines(map, 10);
    return map;
  }

  private static void generateMines(int[][] map, int mineCount) {
    Random random = new Random();
    int minesPlanted = 0;

    while (minesPlanted < mineCount) {
      int randomRowIndex = random.nextInt(map.length);
      int randomColumnIndex = random.nextInt(map[0].length);

      if (map[randomRowIndex][randomColumnIndex] != MINE) {
        map[randomRowIndex][randomColumnIndex] = MINE;
        minesPlanted++;
      }
    }
  }

}
