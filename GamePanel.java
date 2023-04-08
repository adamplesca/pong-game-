// Java Pong game (class)
// Adam Plesca
// 8/4/23

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

	static final int GAME_WIDTH = 1000; // static to share one variable, final keyword used to prevent accidentally tweeking it
	static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555)); // good coding practise to use all caps for final values + game hieght will adjust accodingly to the game width based off a standard pingpong table
	static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
	static final int BALL_DIAMETER = 20;
	static final int PADDLE_WIDTH = 25;
	static final int PADDLE_HEIGHT = 100;
	//declaring a few instances
	Thread gameThread;
	Image image;
	Graphics graphics;
	Random random;
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	Score score;
	//constructor
	GamePanel(){
		newPaddles(); //creates paddle(s)
		newBall(); //creates ball
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		this.setFocusable(true); //reads key presses
		this.addKeyListener(new AL()); //acton listener, will respond to key strokes
		this.setPreferredSize(SCREEN_SIZE);

		gameThread = new Thread(this); //this keyword implements the Runnable interface
		gameThread.start(); // then you start the thread
	}
	public void newBall(){
		random = new Random();
		ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
	}
	public void newPaddles(){
		paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1); //centering the panels on each side of the screen (x, y)
		paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2); //centering the panels on each side of the screen (x, y)
	}
	public void paint(Graphics g){
		image = createImage(getWidth(),getHeight());
		graphics = image.getGraphics();
		draw(graphics);
		g.drawImage(image,0,0,this);// passing through the image, the x and y co-ordinates and this(JPanel called GamePanel)
	}
	public void draw(Graphics g){
		paddle1.draw(g);
		paddle2.draw(g);
		ball.draw(g);
		score.draw(g);
		Toolkit.getDefaultToolkit().sync(); // this helps with the animation
	}
	public void move(){ //when called paddles tp from place to place
		//in this method we can call from the loop to make the paddles less sluggish and more responsive
		//paddle1.move();
		//paddle2.move();
		ball.move();
	}
	public void checkCollision(){
		//bounce ball off top & bottom window edges
		if(ball.y <=0) {
			ball.setYDirection(-ball.yVelocity);
		}
		if(ball.y >= GAME_HEIGHT-BALL_DIAMETER) {
			ball.setYDirection(-ball.yVelocity);
		}
		//bounce ball off paddles
		if(ball.intersects(paddle1)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
		if(ball.yVelocity>0)
			ball.yVelocity++; //optional for more difficulty
		else
			ball.yVelocity--;
			ball.setXDirection(ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		if(ball.intersects(paddle2)) {
			ball.xVelocity = Math.abs(ball.xVelocity);
			ball.xVelocity++; //optional for more difficulty
			if(ball.yVelocity>0)
				ball.yVelocity++; //optional for more difficulty
			else
			ball.yVelocity--;
			ball.setXDirection(-ball.xVelocity);
			ball.setYDirection(ball.yVelocity);
		}
		//this stops paddle 1 from going off the window screen
		if(paddle1.y <= 0)//stops it from going up off the screen
			paddle1.y = 0;
		if(paddle1.y >= (GAME_HEIGHT-PADDLE_HEIGHT))//stops it from going down off the screen
			paddle1.y = GAME_HEIGHT-PADDLE_HEIGHT;
		//this stops paddle 1 from going off the window screen
		if(paddle2.y <= 0) //stops it from going up off the screen
			paddle2.y = 0;
		if(paddle2.y >= (GAME_HEIGHT-PADDLE_HEIGHT))//stops it from going down off the screen
			paddle2.y = GAME_HEIGHT-PADDLE_HEIGHT;
	}
	public void run(){
		//basic game loop (few adjustments made but its a game loop from minecraft). And it gives roughly 60fps
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;//ns = nano seconds (1 billion)
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			if(delta >= 1) {
				move();
				checkCollision();
				repaint();
				delta--;
				//System.out.println("test"); // to test if the loop works which it does
		//give a player 1 point and creates new paddles & ball
		if(ball.x <=0) {
			score.player2++;
			newPaddles();
			newBall();
			System.out.println("Player 2: "+score.player2);
		}
		if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
			score.player1++;
			newPaddles();
			newBall();
			System.out.println("Player 1: "+score.player1);
		}
		}
	}
}
	public class AL extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			paddle1.keyPressed(e);
			paddle2.keyPressed(e);
		}
		public void keyReleased(KeyEvent e){
			paddle1.keyReleased(e);
			paddle2.keyReleased(e);
		}
	}
}