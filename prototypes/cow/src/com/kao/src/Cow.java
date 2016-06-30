package com.kao.src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Cow implements ActionListener, KeyListener {
	
	public static Cow cow = new Cow();
	public Renderer renderer;
	public static Fighter[] players;
	
	public int tracker = 0;
	public int menuPlayers = 2;
	
	public int gameStatus = 0; // 0 = menu
	
	public boolean shot = false;
	
	public int width = 700, height = 700;
		
	 // max is 3 bullets per player

	public Cow() {
		
		Timer timer = new Timer(20, this);
		JFrame jframe = new JFrame("Cow");
		renderer = new Renderer();
		
		jframe.pack(); // used to reposition window
		jframe.setVisible(true);
		jframe.setSize(width, height);
		jframe.setResizable(true);
		jframe.setLocationRelativeTo(null);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jframe.add(renderer);
		jframe.addKeyListener(this);
		
		timer.start();
	}
	
	public void start() {
		gameStatus = 1;
		
		players = new Fighter[menuPlayers];
		for (int i = 0; i < menuPlayers; i++) {
			players[i] = new Fighter(this, i + 1);
		}
				
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		
		switch (gameStatus) {
		case 0:
			g.setColor(Color.WHITE);
			g.setFont(new Font(null, 1, 28));
			g.drawString("COW", width / 2 - 18,height / 2 - 18);
			
			g.setFont(new Font(null, 1, 12));
			g.drawString("Press Enter for multiplayer.", width / 2 - 70,height / 2 + 20);
			
			g.setFont(new Font(null, 1, 12));
			g.drawString("> "+String.valueOf(menuPlayers)+" players", width / 2 - 18,height / 2 + 48);
			break;
		case 1:
			for (int i = 0; i < menuPlayers; i++) {
				players[i].render(g);
				for (int j = 0; j < tracker; j++) {
					players[j].blobs[j].render(g);
				}
			}
			break;
		}
		
		
	}
	
	public void update() {
		for (int i = 0; i < menuPlayers; i++) {
			players[i].move();
			for (int j = 0; j < tracker; j++) {
				players[j].blobs[j].move();
			}
		}
	}

	public static void main(String[] args) throws ScriptException {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (gameStatus == 1) {
			update();
		}
		renderer.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		
		switch (gameStatus) {
		case 0:
			if (id == KeyEvent.VK_ENTER) {
				start();
			} else if (id == KeyEvent.VK_RIGHT) {
				if (menuPlayers == 4) {
					menuPlayers = 2;
				} else {
					menuPlayers++;
				}
			}
		case 1:
			if (id == KeyEvent.VK_SPACE) {
				players[0].turning = true;
			} else if (id == KeyEvent.VK_A) {
				players[0].blobs[tracker] = new Milk(players[0]);

				tracker++;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int id = e.getKeyCode();
			
		if (id == KeyEvent.VK_SPACE) {
			players[0].turning = false;
		}
	}

}
