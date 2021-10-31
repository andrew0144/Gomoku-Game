                                                                                                                                                   import org.junit.*;
import static org.junit.Assert.*;
import junit.framework.TestCase;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;

/**
 * This class tests the Gomoku class
 * @author Andrew Gross
 */

public class GomokuTester extends TestCase {
  
  // tests numberInLine with direction 1(diagonally down to the left)
  public void testNumberInLine1(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 2, 2, 1));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 0, 2, 1));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 5, 0, 1));
    
    // tests many pieces after the current piece
    assertEquals(3, test.numberInLine(testBoard, 0, 5, 1));
    
  }
  
  // tests numberInLine with direction 2(straight down)
  public void testNumberInLine2(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 2, 2, 2));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 1, 2, 2));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 5, 0, 2));
    
    // tests many pieces after the current piece
    assertEquals(3, test.numberInLine(testBoard, 0, 2, 2));
    
  }
  
  // tests numberInLine with direction 3(down + right)
  public void testNumberInLine3(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 4, 0, 3));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 3, 3, 3));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 5, 0, 3));
    
    // tests many pieces after the current piece
    assertEquals(4, test.numberInLine(testBoard, 1, 1, 3));
  }

  // tests numberInLine with direction 4(straight left)
  public void testNumberInLine4(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 2, 2, 4));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 1, 2, 4));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 5, 0, 4));
    
    // tests many pieces after the current piece
    assertEquals(3, test.numberInLine(testBoard, 3, 5, 4));
    
  }

  // tests numberInLine with direction 5(straight right)
  public void testNumberInLine5(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 1, 4, 5));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 3, 4, 5));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 3, 5, 5));
    
    // tests many pieces after the current piece
    assertEquals(3, test.numberInLine(testBoard, 3, 3, 5));
    
  }
  
  // tests numberInLine with direction 6(up and left)
  public void testNumberInLine6(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 3, 5, 6));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 2, 2, 6));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 0, 2, 6));
    
    // tests many pieces after the current piece
    assertEquals(4, test.numberInLine(testBoard, 4, 4, 6));
    
  }
  
  // tests numberInLine with direction 7(straight up)
  public void testNumberInLine7(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 1, 0, 7));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 4, 4, 7));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 0, 2, 7));
    
    // tests many pieces after the current piece
    assertEquals(3, test.numberInLine(testBoard, 2, 2, 7));
    
  }
  
  // tests numberInLine with direction 8(up and right)
  public void testNumberInLine8(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(1, test.numberInLine(testBoard, 1, 2, 8));
    
    // tests one piece of the same color after the current piece
    assertEquals(2, test.numberInLine(testBoard, 1, 1, 8));
    
    // tests hitting the edge of the board
    assertEquals(1, test.numberInLine(testBoard, 0, 5, 8));
    
    // tests many pieces after the current piece
    assertEquals(3, test.numberInLine(testBoard, 2, 3, 8));
  }
  
  // tests isOpen with direction 1(down and left)
  public void testIsOpen1(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    // an alternate testing board 
    int[][] testBoard2 = {{0,2,2,2,2,0},{0,2,1,1,2,2},{0,2,1,1,2,0},{0,1,2,2,2,0},{2,0,1,2,0,0},{1,0,2,0,1,2}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(true, test.isOpen(testBoard, 2, 2, 1));
    
    // tests one piece of the same color after the current piece before being open
    assertEquals(true, test.isOpen(testBoard, 0, 2, 1));
    
    // tests many pieces after the current piece before being open
    assertEquals(true, test.isOpen(testBoard, 0, 5, 1));
        
    // tests hitting the edge of the board
    assertEquals(false, test.isOpen(testBoard, 5, 0, 1));
    
    // tests false with an opposite color piece immediately after
    assertEquals(false, test.isOpen(testBoard2, 1, 2, 1));
    
    // tests false with an opposite color piece after 1 piece of the same color
    assertEquals(false, test.isOpen(testBoard2, 2, 4, 1));
    
    // tests false with an opposite color piece after many pieces of the same color
    assertEquals(false, test.isOpen(testBoard2, 1, 5, 1));
    }
  
  // tests isOpen with direction 2(straight down)
  public void testIsOpen2(){
   
    // an instance of the Gomoku class
    Gomoku test = new Gomoku();
    // the board to test
    int[][] testBoard = {{0,0,2,0,0,1},{1,2,2,0,1,0},{0,0,2,1,0,0},{0,0,0,2,2,2},{2,0,0,0,2,0},{2,0,0,0,0,0}};
    // an alternate testing board 
    int[][] testBoard2 = {{0,2,2,2,2,0},{0,2,1,1,2,2},{0,2,1,1,2,0},{0,1,2,2,2,0},{2,0,1,2,0,0},{1,0,2,0,1,2}};
    
    // tests an immediate empty space after the current piece 
    assertEquals(true, test.isOpen(testBoard, 2, 2, 2));
    
    // tests one piece of the same color after the current piece before being open
    assertEquals(true, test.isOpen(testBoard, 0, 2, 2));
    
    // tests many pieces after the current piece before being open
    assertEquals(true, test.isOpen(testBoard, 0, 5, 2));
        
    // tests hitting the edge of the board
    assertEquals(false, test.isOpen(testBoard, 5, 0, 2));
    
    // tests false with an opposite color piece immediately after
    assertEquals(false, test.isOpen(testBoard2, 1, 2, 2));
    
    // tests false with an opposite color piece after 1 piece of the same color
    assertEquals(false, test.isOpen(testBoard2, 1, 2, 2));
    
    // tests false with an opposite color piece after many pieces of the same color
    assertEquals(false, test.isOpen(testBoard2, 0, 1, 2));
    }
  
  // tests isOpen with direction 3(down and right)
  public void testIsOpen3(){
   
  }
}
