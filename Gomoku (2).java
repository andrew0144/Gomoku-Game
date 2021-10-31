import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import java.util.List;

/**
 * @author Andrew Gross
 * This class represents a game of Gomoku
 */
public class Gomoku extends Application {
  
   // stores the stage of the application
   private Stage primaryStage;
  
   // stores the original green background fill of each button
   private BackgroundFill originalFill = new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, new Insets(1));
   
   // stores the circular background fill of a white piece
   private BackgroundFill whitePiece = new BackgroundFill(Color.WHITE, new CornerRadii(90), new Insets(3));
   
   // stores the circular background fill of a black piece
   private BackgroundFill blackPiece = new BackgroundFill(Color.BLACK, new CornerRadii(90), new Insets(3));
   
   // a background that has the original green fill
   private Background original = new Background(originalFill);
   
   // an int that stores the turn count
   private int turnCount = 1;
   
   // an array of ints that is the data representing the buttons
   private int[][] boardData;
   
   // an int storing the number needed in a row to win the game
   private int numToWin;
   
   // an int that stores the number in two rows that a player cannot create simultaneously with a move
   private int numBanned;
   
   // an int that stores the number in two open rows that a player cannot create simultaneously with a move 
   private int numBannedOpen;
   
   // an int storing the number of rows the game board has
   private int numOfRows;
   
   // an int storing the number of columns the game board has
   private int numOfColumns;
    
   
   /**
    * The start method for this application, starts the javafx app
    * @param  primaryStage  the stage the application is going to use
    */
  public void start(Stage primaryStage) {
    
    // a list storing the command line args
    List<String> args = getParameters().getRaw();
    
    // checks the size of the args and properly sets the values of variables needed to start the app
     if(args.size() > 0){
       if(args.size() == 1){
         numToWin = Integer.parseInt(args.get(0));
         numBanned = numToWin - 1;
         numBannedOpen = numToWin - 2;
         numOfRows = 19;
         numOfColumns = 19;
       }
       else if(args.size() == 3){
         numToWin = Integer.parseInt(args.get(0));
         numBanned = numToWin - 1;
         numBannedOpen = numToWin - 2;
         numOfRows = Integer.parseInt(args.get(1));
         numOfColumns = Integer.parseInt(args.get(2));
       }
       else{
        System.out.println("Please enter 0, 1, or 3 integer arguments. The first should be the number to win, and the latter two should be the dimensions of the board."); 
       }
     }     
     else{
       numToWin = 5;
       numBanned = 4;
       numBannedOpen = 3;
       numOfRows = 19;
       numOfColumns = 19;
     } 
    
    
    this.primaryStage = primaryStage;

    // a gridpane that will have the array of buttons in it
    GridPane gridPane = new GridPane();
    
    // an array of GomokuButtons that is the visual interface of the game
    GomokuButton[][] boardGUI = new GomokuButton[numOfRows][numOfColumns];
    
    // an int array that stores the data of the game 
    boardData = new int[numOfRows][numOfColumns];
    
    // fills the boardData array with zeros(indicating an empty board)
    for(int i = 0; i < boardData.length; i++){
      for(int i2 = 0; i2 < boardData[i].length; i2++){
        boardData[i][i2] = 0;
     }
    }
    
    // fills the visual board with the gomoku buttons and sets their x + y positions, size, background, and action event
   for(int i = 0; i < boardGUI.length; i++){
      for(int i2 = 0; i2 < boardGUI[i].length; i2++){
       boardGUI[i][i2] = new GomokuButton();
       boardGUI[i][i2].setYPosition(i);
       boardGUI[i][i2].setXPosition(i2);
       boardGUI[i][i2].setMinSize(30, 30);
       gridPane.add(boardGUI[i][i2], i2, i);
       boardGUI[i][i2].setBackground(original);
       boardGUI[i][i2].setOnAction(new GameMove());
      }
    }
        
   // a scene that has the grid pane inside of it
    Scene scene = new Scene(gridPane);

    primaryStage.setTitle("Gomoku");
    primaryStage.setScene(scene);            
    primaryStage.show();                  
  }
  
  /**
   * Creates an array of ints using the numberInLine method that stores the numberInLine in all 4 double directions
   * @param  board   the board data that will be used
   * @param  row     the row that the piece is placed at
   * @param  column  the column that the piece is placed at
   * @return         an int array that stores the number in each "double" direction (up + down, left + right, etc.)
   */
  public int[] checkAllDirections(int[][] board, int row, int column){
    
    // an int array that will store the numberInLine output in all 8 directions
    int[] line8 = new int[8];
    
    // an int array that will store the combined "double" directions from line8
    int[] line4 = new int[4];
    
    // fills the line8 array with the proper output of numberInLine
    for(int i = 1; i < line8.length + 1; i++){
      line8[i - 1] = numberInLine(board, row, column, i);
    }      
    
    line4[0] = line8[0] + line8[7] - 1;
    line4[1] = line8[1] + line8[6] - 1;
    line4[2] = line8[2] + line8[5] - 1;
    line4[3] = line8[3] + line8[4] - 1;
    
    return line4;
  }
  
  /**
   * Returns the number of the same color pieces in a row in a certain direction
   * @param  board      the board data that will be used
   * @param  row        the row that the piece is placed at
   * @param  column     the column that the piece is placed at
   * @param  direction  the direction being checked
   * @return            an int that stores the number in the given direction
   */
   public int numberInLine(int[][] board, int row, int column, int direction){
     
     // an int that stores the x position increment for the loop
     int incrementX;
     
     // an int that stores the y position increment for the loop
     int incrementY;
     
     // an int that stores the index of the last column
     int lastColumn = board[0].length - 1;
     
     // an int that stores the index of the last row
     int lastRow = board.length - 1;
     
     // an int that stores the number in a row of the same color pieces
     int count = 1;
     
     // a boolean that tells whether the loop should continue or not
     boolean hasNext;
     
     // an int that stores the number representing the color of the piece
     int pieceColor = board[row][column];
     
     // sets the x and y increments
     switch(direction){
       case 1:
         incrementX = -1;
         incrementY = 1;
         break;
       case 2:
         incrementX = 0;
         incrementY = 1;
         break;
       case 3:
         incrementX = 1;
         incrementY = 1;
         break;
       case 4:
         incrementX = -1;
         incrementY = 0;
         break;
       case 5:
         incrementX = 1;
         incrementY = 0;
         break;
       case 6:
         incrementX = -1;
         incrementY = -1;
         break;
       case 7:
         incrementX = 0;
         incrementY = -1;
         break;
       case 8: 
         incrementX = 1;
         incrementY = -1; 
         break;
       default:
         incrementX = 0;
         incrementY = 0;
         break;
     }

     // the next row in the given direction
     int nextRow = row + incrementY;
     
     // the next column in the given direction
     int nextColumn = column + incrementX;
     
     // sets the intial value of hasNext
     if (nextRow < 0 || nextColumn < 0 || nextRow > lastRow || nextColumn > lastColumn){
       hasNext = false;
     }
     else if (board[row + incrementY][column + incrementX] != 0){
       hasNext = true;
     }
     else
       hasNext = false;
     
     // increases count if the next piece is of the same color and exists
     while(hasNext == true){
       row += incrementY;
       column += incrementX;
       if (board[row][column] == pieceColor)
       count++;
     nextRow = row + incrementY;
     nextColumn = column + incrementX;
     
     // sets the value of hasNext so the loop knows whether to continue or not
     if (nextRow < 0 || nextColumn < 0 || nextRow > lastRow || nextColumn > lastColumn){
       hasNext = false;
     }
     else if (board[nextRow][nextColumn] != 0){
       hasNext = true;
     }
     else
       hasNext = false;
     }
    return count;
   }
   
   /**
   * Checks whether the "double direction" specified has an open line on both sides or not
   * @param  board       the board data that will be used
   * @param  row         the row that the piece is placed at
   * @param  column      the column that the piece is placed at
   * @param  line4Input  the line4 "double direction" that is being checked
   * @return             true if both sides of the line are open, false otherwise
   */
   public boolean doubleIsOpen(int[][] board, int row, int column, int line4Input){
     
     // an array of 2 booleans that stores the isOpen output for both sides
     boolean[] booleans = new boolean[2];
     
     // an int storing the first direction
     int direction1 = 1;
     
     // an int storing the second direction
     int direction2 = 1;
     
     // properly sets the directions depending on the line4Input
     switch(line4Input){
       case 0:
         direction1 = 1;
         direction2 = 8;
         break;
       case 1: 
         direction1 = 2;
         direction2 = 7;
         break;
       case 2:
         direction1 = 3;
         direction2 = 6;
         break;
       case 3:
         direction1 = 4;
         direction2 = 5;
         break;        
     }
     
     booleans[0] = isOpen(board, row, column, direction1);
     booleans[1] = isOpen(board, row, column, direction2);
     
     if (booleans[0] == true && booleans[1] == true)
       return true;
     else
       return false;
   }
   
   /**
   * Returns whether the line of the same color of pieces ends in an open space or not
   * @param  board      the board data that will be used
   * @param  row        the row that the piece is placed at
   * @param  column     the column that the piece is placed at
   * @param  direction  the direction being searched
   * @return            a boolean indicating whether the line is open or not
   */
   public boolean isOpen(int[][] board, int row, int column, int direction){
    
     // an int that stores the x position increment for the loop
     int incrementX;
     
     // an int that stores the y position increment for the loop
     int incrementY;
     
     // an int that stores the index of the last column
     int lastColumn = board[0].length - 1;
     
     // an int that stores the index of the last row
     int lastRow = board.length - 1;
     
     // a boolean that tells whether the loop should continue or not
     boolean hasNext;
     
     // an int storing the color of the piece placed
     int pieceColor = board[row][column];
     
     // properly sets the x and y increments depending on the direction
     switch(direction){
       case 1:
         incrementX = -1;
         incrementY = 1;
         break;
       case 2:
         incrementX = 0;
         incrementY = 1;
         break;
       case 3:
         incrementX = 1;
         incrementY = 1;
         break;
       case 4:
         incrementX = -1;
         incrementY = 0;
         break;
       case 5:
         incrementX = 1;
         incrementY = 0;
         break;
       case 6:
         incrementX = -1;
         incrementY = -1;
         break;
       case 7:
         incrementX = 0;
         incrementY = -1;
         break;
       case 8: 
         incrementX = 1;
         incrementY = -1; 
         break;
       default:
         incrementX = 0;
         incrementY = 0;
         break;
     }
     
     // the next row in the line
     int nextRow = row + incrementY;
     
     // the next column in the line
     int nextColumn = column + incrementX;
     
     // properly initializes the value of hasNext
     if (nextRow < 0 || nextColumn < 0 || nextRow > lastRow || nextColumn > lastColumn){
       return false;
     }
     else if (board[row + incrementY][column + incrementX] != 0){
       hasNext = true;
     }
     else
       hasNext = false;
     
     // loops through the line until it hits a different color, open space, or the edge and outputs the proper boolean
     while(hasNext == true){    
     nextRow = row + incrementY;
     nextColumn = column + incrementX;
     if (nextRow < 0 || nextColumn < 0 || nextRow > lastRow || nextColumn > lastColumn){
       return false;
     }
     else if (board[nextRow][nextColumn] == 0){
       return true;
     }
     else if (board[nextRow][nextColumn] != pieceColor){
       return false;
     }
     else
       hasNext = true;
     row = nextRow;
     column = nextColumn;
     }
     return true;
   }
   
   /**
    * This is the main method, which launches the application
    * @param  args  the array of strings that are used in the application
    */ 
   public static void main(String[] args) {
     Application.launch(args);
   }
   
   /**
    * This nested class represents a piece being place
    */
    private class GameMove implements EventHandler<ActionEvent> {
     
    /** Sets the button to place the piece and runs through the rules of the game
      * @param e  information about the button click event that occurred
      */
     public void handle(ActionEvent e) {
       
       // the button being clicked on
       GomokuButton b = (GomokuButton)e.getSource();
        
       // if the button has no piece, places the correct piece and updates the board data
       if (b.getBackground().getFills().contains(whitePiece) != true && b.getBackground().getFills().contains(blackPiece) != true){
       
         if (turnCount == 1 || turnCount % 2 == 1){
         b.setBackground(new Background(originalFill, blackPiece));
         boardData[b.getYPosition()][b.getXPosition()] = 1;
         turnCount++;
         }
         else{
           b.setBackground(new Background(originalFill, whitePiece));
           boardData[b.getYPosition()][b.getXPosition()] = 2;
           turnCount++;
         }
       }
       
       // an int array that stores the output of checkAllDirections
       int[] line4 = checkAllDirections(boardData, b.getYPosition(), b.getXPosition());
       
       // a boolean for checking if the game has been won or not
       boolean hasWon = false;
       
       // a counter for the numBanned variable
       int bannedMoveCount = 0;
       
       // a counter for the numBannedOpen variable
       int bannedMoveOpenCount = 0;
       
       // goes through the output of checkAllDirections and checks if the game has been won or the move is illegal
       for (int i = 0; i < line4.length; i++){
         if (line4[i] == numBanned){
           if (bannedMoveCount > 0){
             b.setBackground(new Background(originalFill));
             boardData[b.getYPosition()][b.getXPosition()] = 0;
             turnCount--;
             System.out.println("You cannot make a move that simultaneously creates two lines of " + numBanned + ".Please try another move.");
           }
           bannedMoveCount++;
         }
         
         if (line4[i] == numBannedOpen){
           if(doubleIsOpen(boardData, b.getYPosition(), b.getXPosition(), i) == true){
           if (bannedMoveOpenCount > 0){
             b.setBackground(new Background(originalFill));
             boardData[b.getYPosition()][b.getXPosition()] = 0;
             turnCount--;
             System.out.println("You cannot make a move that simultaneously creates two open lines of " + numBannedOpen + ".Please try another move.");           
           }
           
           bannedMoveOpenCount++;
           }
         }
         
        if (line4[i] == numToWin)
          hasWon = true;
       }
       if (hasWon == true){
         primaryStage.setScene(new Scene(new TextArea("You Win!!")));
       }
       
     }
       
   }
  
}