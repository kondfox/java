import java.util.*;

public class Minesweeper {

  public static final List<String> ALPHABET = new ArrayList<>(Arrays.asList(
          "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"
  ));
  public static final int MINE = 9;
  public static final int MAP_WIDTH = 10;
  public static final int MAP_HEIGHT = 10;
  public static final int MINE_COUNT = 10;

  private static boolean[][] appearedMap = new boolean[MAP_HEIGHT][MAP_WIDTH];
  private static boolean isGameOver = false;
  private static int appearedFieldsCount = 0;

  public static void main(String[] args) {

    int[][] map = createMap(MAP_WIDTH, MAP_HEIGHT);
    printMap(map);

    while (!isGameOver) {
      int[] userInput = getUserInput();
      appearField(userInput, appearedMap);
      printMap(map);
      checkGameState(map, userInput);
    }
    System.out.println("Game over");
    System.exit(-1);
  }

  private static void appearField(int[] userInput, boolean[][] appearedMap) {
    appearedMap[userInput[0]][userInput[1]] = true;
    appearedFieldsCount++;
  }

  private static void checkGameState(int[][] map, int[] userInput) {
    if (map[userInput[0]][userInput[1]] == MINE) {
      isGameOver = true;
    }
    if (appearedFieldsCount == MAP_WIDTH * MAP_HEIGHT - MINE_COUNT) {
      System.out.println("Congratulations, you Won!");
      System.exit(1);
    }
  }

  private static void printMap(int[][] map) {
    printMapHeader(map);
    printMapBody(map);
  }

  private static void printMapBody(int[][] map) {
    for (int i = 0; i < map.length; i++) {
      System.out.print(ALPHABET.get(i) + "\t");
      for (int j = 0; j < map[i].length; j++) {
        if (appearedMap[i][j]) {
          System.out.print(map[i][j] + " ");
        } else {
          System.out.print("# ");
        }

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
    generateMines(map, MINE_COUNT);
    calculateFieldValues(map);
    return map;
  }

  private static void calculateFieldValues(int[][] map) {
    for (int rowIndex = 0; rowIndex < map.length; rowIndex++) {
      for (int columnIndex = 0; columnIndex < map[0].length; columnIndex++) {
        if (map[rowIndex][columnIndex] == MINE) {
          continue;
        }
        List<Integer[]> neighbours = getNeighbours(map, rowIndex, columnIndex);
        int mineCount = countMines(map, neighbours);
        map[rowIndex][columnIndex] = mineCount;
      }

    }
  }

  private static int countMines(int[][] map, List<Integer[]> neighbours) {
    int mineCount = 0;
    for (int i = 0; i < neighbours.size(); i++) {
      int rowIndex = neighbours.get(i)[0];
      int columnIndex = neighbours.get(i)[1];
      if (map[rowIndex][columnIndex] == MINE) {
        mineCount++;
      }
    }
    return mineCount;
  }

  private static List<Integer[]> getNeighbours(int[][] map, int rowIndex, int columnIndex) {
    List<Integer[]> neighbours = new ArrayList<>();

    for (int i = -1; i <= 1; i++) {
      for (int j = -1; j <= 1; j++) {
        if (i == 0 && j == 0) {
          continue;
        }
        int neighbourRowIndex = rowIndex + i;
        int neighbourColumnIndex = columnIndex + j;
        if (neighbourRowIndex >= 0 && neighbourRowIndex < map.length &&
            neighbourColumnIndex >= 0 && neighbourColumnIndex < map[0].length) {
          neighbours.add(new Integer[]{ rowIndex + i, columnIndex + j });
        }
      }
    }
    return neighbours;
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

  public static int[] getUserInput() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Your coordinate (e.g. C3): ");
    String userInput = scanner.nextLine();

    int rowIndex = ALPHABET.indexOf(Character.toString(userInput.charAt(0)));
    int columnIndex = Integer.parseInt(userInput.substring(1)) - 1;
    return new int[]{ rowIndex, columnIndex };
  }
}
