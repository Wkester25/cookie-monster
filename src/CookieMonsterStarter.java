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

  private void makeRandomCookies(int size)
  {
    SIZE = (size);
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
    if (goodPoint(row, col))
    {
      int goRight = recOptimalPath(row, col + 1);
      int goDown = recOptimalPath(row + 1, col);
      count = cookies[row][col] + Math.max(goRight, goDown);
      map[row][col] = count;
    }
    return count;
  }


  public static void main(String args[]) throws InterruptedException {  // Adapt this as you see fit.
    CookieMonsterStarter monster = new CookieMonsterStarter();
    Scanner kboard = new Scanner(System.in);
    System.out.print("Enter 1 to read from file, 2 to generate random cookies: ");
    int choice = kboard.nextInt();
    if (choice == 1)
      monster.loadCookies();
    else {
        System.out.print("Please enter the size of the cookie array or -1 for random size: ");
        int size = kboard.nextInt();
        if (size == -1){
          monster.makeRandomCookies();
        } else {
          monster.makeRandomCookies(size);
        }
    }
    MatrixTools.Integer.Display.displayMatrixJFrame(monster.cookies);
    System.out.println("Please select the method you would like to use to find the optimal path.");
    System.out.println("1. Dynamic Programming");
    System.out.println("2. Recursive");
    System.out.println("3. Stacks & Queues Fun");
    System.out.print("Method: ");
    int method = kboard.nextInt();
    if (method == 1){
      System.out.println("The Dynamic Programming method has been selected. This method generates a heatmap of the maximum possible value of cookies at each location. The optimal path is then found by backtracking from the end to the beginning. The heatmap and best path will now be displayed in new windows.");
      System.out.println("Optimal path has " + monster.optimalPathDynamic() + " cookies.\n");
        MatrixTools.Integer.Display.displayMatrixJFrame(monster.map);
        MatrixTools.Integer.Display.displayMatrixJFrame(monster.path);
    } else if (method == 2){
      System.out.println("The Recursive method has been selected. This method recursively finds the optimal path by checking the maximum value of cookies at each location. The optimal path is then found by backtracking from the end to the beginning. The heatmap will now be displayed in a new window.");
      System.out.println("Optimal path has " + monster.recOptimalPath(0,0) + " cookies.\n");
        MatrixTools.Integer.Display.displayMatrixJFrame(monster.map);
        MatrixTools.Integer.Display.displayMatrixJFrame(monster.path);
    } else {
        System.out.println("The Stacks & Queues Fun method has been selected. This method uses a queue to keep track of the current path and a stack to keep track of all possible paths. Only the best solution will now displayed in a new window.");
        monster.findOptimalPath();
        MatrixTools.Integer.Display.displayMatrixJFrame(monster.path);
    }
    if(method == 3){
      System.out.println("Would you like to see all paths? (Y/N) [Its usually a lot of paths!]");
        String answer = kboard.next();
        if(answer.equalsIgnoreCase("Y")){
          System.out.println("Number of Possible Paths: " + monster.paths.size());
          for(Queue<Location> path : monster.paths){
            System.out.println("Path: " + path);
          }
        } else {
          System.out.println("Goodbye!");
        }
    }
    else {
      System.out.println("Goodbye!");
    }
  }

  private boolean checkMultipulePaths(int row, int col) {
    return (goodPoint(row + 1, col) && goodPoint(row, col + 1));
  }
  private boolean checkMultipulePaths(Location location){ return(goodPoint(location.row + 1, location.col) && goodPoint(location.row, location.col + 1));}

  public int[][] makeHeatMap(){
    int[][] maxCookies = new int[SIZE][SIZE];
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

  public int findOptimalPath(){
    Queue<Location> currentPath = new LinkedList<>();
    Stack<Branch> branches = new Stack<>();
    int max = 0;
    int currentTotal = 0;
    int row = 0;
    int col = 0;
    Queue<Location> bestPath = new LinkedList<>();
    currentPath.add(new Location(0, 0, checkMultipulePaths(0, 0), cookies[0][0]));                                //Add the first location to the current path
    currentTotal += cookies[0][0];                                                                                                  //Add the value of the first location to the current total
    if (checkMultipulePaths(0, 0)) {                                                                                       //If there are multiple paths from the start
      branches.add(new Branch(0, 0, checkMultipulePaths(0, 0), cookies[0][0], copy(currentPath), currentTotal));  //Add the path to the stack
    }
    int count = 0;
    while((!branches.isEmpty() || goodPoint(row, col + 1) || goodPoint(row + 1, col))) {                                   //While there are still paths to check or the current path is not at the end
      if (goodPoint(row, col + 1)) {                                                                                            //If there is a path to the right
        System.out.println("Right");
        col++; //Move to the right
        currentTotal += cookies[row][col];                                                                                          //Add the value of the current location to the total
        currentPath.add(new Location(row, col, checkMultipulePaths(row, col), cookies[row][col]));                                  //Add the current location to the current path
        if (checkMultipulePaths(row, col)) {                                                                                        //If there are multiple paths from the current location
          branches.push(new Branch(row,col,checkMultipulePaths(row,col),cookies[row][col], copy(currentPath), currentTotal));       //Add the path to the stack
        }
      }
      else if (goodPoint(row + 1, col)) {                                                                                      //If there is a path down
        System.out.println("Down");
        row++; //Move down
        currentTotal += cookies[row][col];                                                                                          //Add the value of the current location to the total
        currentPath.add(new Location(row, col, checkMultipulePaths(row, col), cookies[row][col]));                                  //Add the current location to the current path
        if (checkMultipulePaths(row, col)) {                                                                                        //If there are multiple paths from the current location
          branches.push(new Branch(row,col,checkMultipulePaths(row,col),cookies[row][col], copy(currentPath), currentTotal));       //Add the path to the stack
        }
      }
      if ((row == SIZE - 1 && col == SIZE - 1) || (!goodPoint(row, col + 1) && !goodPoint(row + 1, col))) {                //If the current path is at the end or there are no more paths to check
        System.out.println("End");
        if(row == SIZE - 1 && col == SIZE - 1){                                                                                     //If the current path is at the end
          paths.add(copy(currentPath));                                                                                             //Add the current path to the list of possible paths
        }
        if (currentTotal > max) {                                                                                                   //If the current path has more cookies than the previous best path
          System.out.println("New Max: " + currentTotal);
          max = currentTotal;                                                                                                       //Set the new best path to the current path
          bestPath = copy(currentPath);                                                                                             //Set the new best path to the current path
        }
        if (!branches.isEmpty()) {                                                                                                  //If there are still paths to check
          Branch branch = branches.pop();                                                                                           //Get the next path to check
          System.out.println("Branch Pop");
          System.out.println("Branchs: " + branches);
          currentPath = branch.getPreviousLocations();                                                                              //Set the current path to the path that was popped
          row = branch.row + 1;                                                                                                     //Set the current row and column to the next location in the path
          col = branch.col;                                                                                                         //Set the current row and column to the next location in the path
          currentTotal = branch.totalValue + cookies[row][col];                                                                     //Set the current total to the total value of the path that was popped
          currentPath.add(new Location(row, col, checkMultipulePaths(row, col), cookies[row][col]));                                //Add the next location to the current path
          if (checkMultipulePaths(row, col) && row != SIZE - 1 && col != SIZE - 1) {                                                //If there are multiple paths from the current location
            branches.push(new Branch(row,col,checkMultipulePaths(row,col),cookies[row][col], copy(currentPath), currentTotal));     //Add the path to the stack
          }
          System.out.println(currentPath);
        }

      }
    }
    System.out.println("Best Path: " + bestPath);
    for(Location location : bestPath){
      path[location.row][location.col] = location.value;
    }
    return max;
  }
}