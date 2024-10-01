import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *  In this program Cookie Monster finds the optimal path from 
 *  the upper left corner (0,0) to the lower right corner
 *  (SIZE-1,SIZE-1) in a cookie array.  The elements of
 *  the array contain cookies (a non-negative number) or barrels
 *  (-1).  On each step Cookie Monster can only go down or
 *  to the right.  He is not allowed to step on barrels. 
 *  The optimal path contains the largest number of cookies.
 *
 *  The program prompts the user for a file name,
 *  reads the cookie array from the file, and reports the
 *  number of cookies on the optimal path. Assumed size of the
 *  grid of values i 12 x 12, stored in row-major order.
 *
 *  Bonus:  Adapt the program to read 2 ints from the file first
 *  representing numRows and numCols, and then read all values into
 *  a 2-d array.  (Consult FloodFill project for an example.)
 *
 *  Bonus#2: Write a recursive solution that finds the optimal cookie
 *  total for the problem.   (For mega bonus, write a recursive solution
 *  that reports the Optimal Path formed with that total.)
 *
 *  The program also reports the actual optimal path, location
 *  by location in effective formatting.
 *
 *  Finally, the program will output every successful path found,
 *  along with the total cookies along that path.
 */

public class CookieMonsterStarter
{
  private int SIZE; //Can be altered for different files.
  private int[][] cookies;
  private int[][] path;
  private int[][] map;

  private ArrayList<Queue<Location>> paths = new ArrayList<>();

  /**
   *  Reads cookies from file
   */

  private void loadCookies()
  {
    String fileName;
    Scanner kboard = new Scanner(System.in);
    System.out.print("Enter the cookies file name: ");
    fileName = kboard.nextLine();
    File file = new File(fileName);
    Scanner input = null;
    try
    {
      input = new Scanner(file);
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Cannot open " + fileName);
      System.exit(1);
    }
    String firstLine = input.nextLine();
    String[] temp = firstLine.split(" ");
    ArrayList<String> parts = new ArrayList<>();
    for (String s : temp) {
      if (!s.isEmpty()) {
        parts.add(s);
      }
    }
    SIZE = parts.size();
    cookies = new int[SIZE][SIZE];
    System.out.println("Rows: " + parts.size());
    for (String s : parts) {
      cookies[0][parts.indexOf(s)] = Integer.parseInt(s);
    }

    for (int row = 1;   row < SIZE;   row++)
      for (int col = 0;   col < SIZE;   col++)
        cookies[row][col] = input.nextInt();

    path = new int[SIZE][SIZE];
    map = new int[SIZE][SIZE];
  }

  private void makeRandomCookies()
  {
    SIZE = ((int)(Math.random()*25) + 1);
    cookies = MatrixTools.Integer.Generation.randomMatrix(SIZE, SIZE, -1, 9);
    path = new int[SIZE][SIZE];
    map = new int[SIZE][SIZE];
  }

  /**
   *  Returns true if (row, col) is within the array and that position is
   *  not a barrel (-1); false otherwise.  Notice short-circuit evaluation
   *  to protect out-of-bounds errors from occuring.
   */
  private boolean goodPoint(int row, int col)
  {
    return row >= 0 && row<SIZE && col>= 0 && col<SIZE && cookies[row][col]>=0;
  }

  /**
   *  Returns the largest number of cookies collected by Monster
   *  on a path from (0,0) to (SIZE-1, SIZE-1)
   */
  private int optimalPathDynamic()
  {
    map = makeHeatMap();
    int currentRow = SIZE-1;
    int currentCol = SIZE-1;
    path[SIZE-1][SIZE-1] = map[SIZE-1][SIZE-1];
    while(currentCol != 0 || currentRow != 0){
      if (goodPoint(currentRow - 1, currentCol) && goodPoint(currentRow, currentCol - 1)){
        if (map[currentRow - 1][currentCol] > map[currentRow][currentCol - 1]){
          currentRow--;
        } else {
          currentCol--;
        }
      } else if (goodPoint(currentRow - 1, currentCol)){
        currentRow--;
      } else {
        currentCol--;
      }
      path[currentRow][currentCol] = map[currentRow][currentCol];
    }
    return map[SIZE-1][SIZE-1];
  }



  /**  The following is something we coded together in Ch20 work:
   *		E  is an Element Type
   * 	It is a Static method:  to activate it...
   *      in another class:  someotherQ= CoookieMonster.copy(someq);
   *      in this class:   		 newQ = copy(q);
   *      */
  public static <E>  Queue<E>   copy(Queue<E> q){

    Queue<E> q2 = new LinkedList<E>();

    if (!q.isEmpty()){

      E obj = q.remove();
      E first = obj;
      q2.add(obj);
      q.add(obj);

      while (q.peek() != first) {
        obj = q.remove();
        q.add(obj);
        q2.add(obj);
      }
    }

    return q2;
  }

  /**BONUS #2
   * Recursive solution for obtaining optimal number of cookies. Additional
   * parameters might need to be included to track paths, or it may be fruitful
   * to create some private fields that are used within this method as required.
   * For example, we could maintain fields for bestCookieCount and bestPathFound.
   * @param row  - the destination row	  (Start is always (0,0).)
   * @param col  - the destination column
   * @return number of cookies on optimal path to (row,col) from (0,0).
   */
  private int recOptimalPath(int row, int col)
  {
    int count = 0;

    //Code here.

    return count;
  }


  public static void main(String args[]) throws InterruptedException {  // Adapt this as you see fit.
    CookieMonsterStarter monster = new CookieMonsterStarter();
    monster.loadCookies();
    MatrixTools.Integer.Display.displayMatrixJFrame(monster.cookies);

    System.out.println("Optimal path has " +
            monster.findOptimalPath() + " cookies.\n");

    MatrixTools.Integer.Display.displayMatrixJFrame(monster.path);
    System.out.println("Number of Possible Paths: " + monster.paths.size());
    for (Queue<Location> path : monster.paths){
      System.out.println("Path: " + path);
    }
  }

  private boolean checkMultipulePaths(int row, int col) {
    return (goodPoint(row + 1, col) && goodPoint(row, col + 1));
  }
  private boolean checkMultipulePaths(Location location){ return(goodPoint(location.row + 1, location.col) && goodPoint(location.row, location.col + 1));}

  public int[][] makeHeatMap(){
    int[][] maxCookies = new int[SIZE][SIZE];
    System.out.println("SIZE: " + SIZE);
    maxCookies[0][0] = cookies[0][0];
    map[0][0] = cookies[0][0];
    for (int row = 0;   row < SIZE;   row++)
    {
      for (int col = 0;   col < SIZE;   col++)
      {
        if (goodPoint(row, col))
        {
          int max = 0;
          if (goodPoint(row - 1, col))
            max = Math.max(max, maxCookies[row - 1][col]);
          if (goodPoint(row, col - 1))
            max = Math.max(max, maxCookies[row][col - 1]);
          maxCookies[row][col] = max + cookies[row][col];
          map[row][col] = maxCookies[row][col];
        }
      }
    }


    return maxCookies;
  }

  public int findOptimalPath() throws InterruptedException {
    Queue<Location> currentPath = new LinkedList<>();
    Stack<Branch> branches = new Stack<>();
    int max = 0;
    int currentTotal = 0;
    int row = 0;
    int col = 0;
    Queue<Location> bestPath = new LinkedList<>();
    currentPath.add(new Location(0, 0, checkMultipulePaths(0, 0), cookies[0][0]));
    currentTotal += cookies[0][0];
    if (checkMultipulePaths(0, 0)) {
      branches.add(new Branch(0, 0, checkMultipulePaths(0, 0), cookies[0][0], currentPath, currentTotal));
    }
    int count = 0;
    while((!branches.isEmpty() || goodPoint(row, col + 1) || goodPoint(row + 1, col))) {
      if (goodPoint(row, col + 1)) {
        col++;
        currentTotal += cookies[row][col];
        currentPath.add(new Location(row, col, checkMultipulePaths(row, col), cookies[row][col]));
        if (checkMultipulePaths(row, col)) {
          branches.push(new Branch(row,col,checkMultipulePaths(row,col),cookies[row][col], copy(currentPath), currentTotal));
        }
      }
      else if (goodPoint(row + 1, col)) {
        row++;
        currentTotal += cookies[row][col];
        currentPath.add(new Location(row, col, checkMultipulePaths(row, col), cookies[row][col]));
        if (checkMultipulePaths(row, col)) {
          branches.push(new Branch(row,col,checkMultipulePaths(row,col),cookies[row][col], copy(currentPath), currentTotal));
        }
      }
      if (row == SIZE - 1 && col == SIZE - 1 || (!goodPoint(row, col + 1) && !goodPoint(row + 1, col))) {
        if(row == SIZE - 1 && col == SIZE - 1){
          paths.add(copy(currentPath));
        }
        if (currentTotal > max) {
          System.out.println("New Max: " + currentTotal);
          max = currentTotal;
          bestPath = copy(currentPath);
        }
        if (!branches.isEmpty()) {
          Branch branch = branches.pop();
          System.out.println("Branch Pop");
          System.out.println("Branchs: " + branches);
          currentPath = branch.getPreviousLocations();
          row = branch.row + 1;
          col = branch.col;
          currentTotal = branch.totalValue + cookies[row][col];
          currentPath.add(new Location(row, col, checkMultipulePaths(row, col), cookies[row][col]));
          if (checkMultipulePaths(row, col)) {
            branches.push(new Branch(row,col,checkMultipulePaths(row,col),cookies[row][col], copy(currentPath), currentTotal));
          }
          System.out.println("Row: " + row + " Col: " + col);
        }
      }
      count++;
    }
    System.out.println("Best Path: " + bestPath);
    for(Location location : bestPath){
      path[location.row][location.col] = location.value;
    }
    return max;
  }
}