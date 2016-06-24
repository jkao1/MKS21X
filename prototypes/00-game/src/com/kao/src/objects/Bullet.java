package com.kao.src.objects;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.kao.src.GlobalPosition; // gives x and y coordinates

public class Bullet extends GlobalPosition {
	
	private String bulletimage = "/images/bulletimage.png";

	public Bullet(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(getBulletImage(), x, y, null);
	}
	
	public Image getBulletImage(){ // returns image
		ImageIcon i = new ImageIcon(getClass().getResource(bulletimage)); // gets image
		return i.getImage(); 
	}
}