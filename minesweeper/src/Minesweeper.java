import java.util.Random;

public class Minesweeper {

//    1 2 3 4 5 6
//  A # # # # # #
//  B # # # # # #
//  C # # # # # #
//  D # # 1 # # #


  public static void main(String[] args) {

    int mapWidth = 10;
    int mapHeight = 10;

    int[][] map = createMap(mapWidth, mapHeight);
    printMap(map);

  }

  private static void printMap(int[][] map) {
    for (int i = 0; i < map.length; i++) {
      for (int j = 0; j < map[i].length; j++) {
        System.out.print(map[i][j] + " ");
      }
      System.out.println();
    }
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

      if (map[randomRowIndex][randomColumnIndex] != 9) {
        map[randomRowIndex][randomColumnIndex] = 9;
        minesPlanted++;
      }
    }

  }

}
