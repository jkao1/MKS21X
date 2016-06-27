package com.kao.src;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Ball {
	
	public int x, y, width = 15, height = 15;
	
	public int motionX, motionY;
	
	public Random random;

	public Ball(Pong pong) {
		random = new Random();
		this.x = pong.width / 2 - this.width / 2;
		this.y = pong.height / 2 - this.height / 2;
		
		if (random.nextBoolean()) {
			motionX = 1;
		} else {
			motionX = -1;
		}
		if (motionY == 0) {
			motionY = 1;
		}
		
		this.motionY = -2 + random.nextInt(4);
	}
	
	public void update(Paddle paddle1, Paddle paddle2){
		this.x += motionX;
		this.y += motionY;
		
		if (checkCollision(paddle1) == 1) {
			this.motionX = 1;
			this.motionY = -2 + random.nextInt(4);
			
			if (motionY == 0) {
				motionY = 1;
			}
		} else if (checkCollision(paddle2) == 1) {
			this.motionX = -1;
			this.motionY = -2 + random.nextInt(4);
			
			if (motionY == 0) {
				motionY = 1;
			}
		}
		if (checkCollision(paddle1) == 2) {
			paddle2.score++;
		} else if (checkCollision(paddle2) == 2) {
			paddle1.score++;
		}
	}
	
	public int checkCollision(Paddle paddle){
		if (paddle.x > x || paddle.x < x + width){
			if (paddle.y > y + height || paddle.y+ height < y) {
				return 1; // hit paddle
			} else {
				return 2; // hit paddle
			}
		}
		return 0; // hit nothing
	}
	
	public void render(Graphics2D g){
		g.setColor(Color.WHITE);
		g.fillOval(x, y, width, height);
	}

}