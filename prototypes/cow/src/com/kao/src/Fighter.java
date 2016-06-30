package com.kao.src;

import java.awt.Color;
import java.awt.Graphics;

public class Fighter {
	
	public int x, y, width = 20, height = 20;
	
	public double angle = 0;
	
	public boolean turning = false;
	
	public Double velX, velY;
		
	public static double speedForward = .35, speedMilk = .9, speedRotate;
	
	public int distanceFromBorder = 20, windowCorrectionBottom = 60;
	
	public static Milk blobA, blobB, blobC;
	public static Milk[] blobs = {blobA, blobB, blobC};
	public static int ammo = blobs.length;
	
	public Fighter(Cow cow, int fighterNumber) {
		
		switch (fighterNumber) {
		case 1: // lower left corner
			x = 0 + distanceFromBorder;
			y = cow.height - distanceFromBorder - windowCorrectionBottom;
			velY *= -1;
			break;
		case 2: // upper right corner
			x = cow.width - distanceFromBorder;
			y = 0 + distanceFromBorder;
			velX *= -1;
			break;
		case 3: // upper left corner
			x = 0 + distanceFromBorder;
			y = 0 + distanceFromBorder;
			break;
		case 4: // lower right corner
			x = cow.width - distanceFromBorder;
			y = cow.height - distanceFromBorder - windowCorrectionBottom;
			velX *= -1;
			velY *= -1;
			break;
		}
	}
	
	public void move() {
		if (turning) {
			angle += Math.PI / 40;
		}
		
		velX = new Double(Math.cos(angle) * 10);
		int motionX = (int) (velX.intValue() * speedForward);
		velY = new Double(Math.sin(angle) * 10); 
		int motionY = (int) (velY.intValue() * speedForward);
		
		boolean outX = x + motionX < 0 || x + width + motionX > Cow.cow.width;
		boolean outY = y + motionX < 0 || y + height + motionY > Cow.cow.height - windowCorrectionBottom;
		
		if (outX) {
			y += motionY;
		} else if (outY) {
			x += motionX;
		} else {
			x += motionX;
			y += motionY;
		}
	}
	
	public void shoot() {

	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, height);
	}

}
