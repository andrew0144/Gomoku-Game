import javafx.scene.control.*;

/**
 * @author Andrew Gross
 * This class represents a button for the Gomoku class
 */
public class GomokuButton extends Button {
  
 // an int that stores the x position of the button in the array
 private int xPosition;
 
 // an int that stores the y position of the button in the array
 private int yPosition;
 
 /**
  * Gets the x position of the button in the array
  * @return the x position of the button
  */
 public int getXPosition(){
  return xPosition; 
 }
 
 /**
  * Sets the x position variable of the button in the array
  * @param  xPosition  the position of the button in columns 
  */
 public void setXPosition(int xPosition){
  this.xPosition = xPosition;
 }
 
 /**
  * Gets the y position of the button in the array
  * @return the y position of the button
  */
 public int getYPosition(){
  return yPosition; 
 }
 
  /**
  * Sets the y position variable of the button in the array
  * @param  yPosition  the position of the button in rows 
  */
 public void setYPosition(int yPosition){
  this.yPosition = yPosition;
 }
 
}
