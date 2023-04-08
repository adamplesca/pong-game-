// Java Pong game (class)
// Adam Plesca
// 8/4/23

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Paddle extends Rectangle{

	int id; //for paddle1 or paddle 2
	int yVelocity; //speed at which paddle goes in game
	int speed = 30;
	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){ // passing values into constructor to be able to create multiple paddle objects (2)
		super(x,y,PADDLE_WIDTH, PADDLE_HEIGHT);
		this.id = id;
	}
	public void keyPressed(KeyEvent e){ //checks for user input of the keys being pressed
		switch(id){
			case 1: //case 1 = paddle 1
			 	if(e.getKeyCode()==KeyEvent.VK_W) { //this line of code makes it so when the user 1 presses W, paddle one will move
					setYDireaction(- speed); //will move up on the y -axis by 10 pixels
					move();
				}
				if(e.getKeyCode()==KeyEvent.VK_S) { //this line of code makes it so when the user 1 presses S, paddle one will move (VK = Virtual key)
					setYDireaction(speed); //will move up on the y -axis by 10 pixels
					move();
				}
				break;
			case 2: //case 2 = paddle 2
				if(e.getKeyCode()==KeyEvent.VK_P) { //this line of code makes it so when the user 2 presses P, paddle two will move
					setYDireaction(- speed); //will move up on the y -axis by 10 pixels
					move();
				}
				if(e.getKeyCode()==KeyEvent.VK_L) { //this line of code makes it so when the user 2 presses L, paddle two will move (VK = Virtual key)
					setYDireaction(speed); //will move up on the y -axis by 10 pixels
					move();
				}
				break;
			}
	}
	public void keyReleased(KeyEvent e){ //checks for user input of the keys being released (VK = Virtual key)
			switch(id){
				case 1: //case 1 = paddle 1
					 if(e.getKeyCode()==KeyEvent.VK_W) { //this line of code makes it so when the user 1 presses W, paddle one will move
						setYDireaction(0); //will stop moving when key is released
						move();
					}
					if(e.getKeyCode()==KeyEvent.VK_S) { //this line of code makes it so when the user 1 presses S, paddle one will move
						setYDireaction(0); //will stop moving when key is released
						move();
					}
					break;
				case 2: //case 2 = paddle 2
					if(e.getKeyCode()==KeyEvent.VK_P) { //this line of code makes it so when the user 2 presses P, paddle two will move
						setYDireaction(0);//will stop moving when key is released);
						move();
					}
					if(e.getKeyCode()==KeyEvent.VK_L) { //this line of code makes it so when the user 2 presses L, paddle two will move
						setYDireaction(0); //will stop moving when key is released
						move();
					}
					break;
			}
	}
	public void setYDireaction(int yDireaction){ //paddles only move up and down so don't need to set a x direaction for it
		yVelocity = yDireaction;
	}
	public void move(){
		y = y + yVelocity;
	}
	public void draw(Graphics g){
		//if statment to set the colors of the paddles
		if(id == 1)
			g.setColor(Color.blue);
		else
			g.setColor(Color.red); //no need for another else if/ if statment since else will determine the second paddle as paddle2
			g.fillRect(x, y, width, height);
	}

}