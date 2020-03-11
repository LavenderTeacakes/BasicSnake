package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sprites.*;


public class Game extends JPanel{
	
	public Snake snake;
	public Apple apple;
	
	public boolean validIn = true;
//	public int in = KeyEvent.VK_RIGHT;
//	public KeyEvent rollOver = null;
	
	public Game() {
		setBackground(Color.DARK_GRAY);
		
		apple = new Apple();
		snake = new Snake(this);
		
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
//				if(validIn) {
//					in = e.getKeyCode();
//					validIn = false;
//					System.out.println("button");
//				}
//				else {
//					System.out.println("savin");
//					rollOver = e;
//				}
				snake.keyPressed(e);
			}

			@Override
			public void keyReleased(KeyEvent e) {}
			
		});
		setFocusable(true);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		
		apple.paint(g2);
		snake.paint(g2);
	}
	
	public void move() {
//		if(rollOver != null) {
//			System.out.println("rollin");
//			snake.keyPressed(rollOver);
//			rollOver = null;
//			validIn = true;
//		}
//		else {
//			snake.keyPressed(in);
//			validIn = true;
//		}
		snake.move();
	}
	
	public void gameOver() {
		JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
		System.exit(ABORT);
	}
	
	public static void main(String[] args) throws InterruptedException{
		JFrame frame = new JFrame("Snake");
		Game game = new Game();
		
		game.setPreferredSize(new Dimension(Constants.boardSize * Constants.cellSize + Constants.bufferSize * (Constants.boardSize - 1), Constants.boardSize * Constants.cellSize + Constants.bufferSize * (Constants.boardSize - 1)));
		frame.getContentPane().add(game);
		frame.pack();
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		
		while(true) {
			game.move();
			game.repaint();
			Thread.sleep(Constants.tick);
		}
	}
}