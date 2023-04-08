// Java Pong game (class)
// Adam Plesca
// 8/4/23

import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

	GamePanel panel; //canvas on what I'm painting

	GameFrame(){ // is the frame of the painting
		 panel = new GamePanel();
		 this.add(panel);
		 this.setTitle("Pong Game");
		 this.setResizable(false); // so you can't resize
		 this.setBackground(Color.black);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.pack();
		 this.setVisible(true); //window frame will adjust to fit the size of the game panel
		 this.setLocationRelativeTo(null);
	}

}