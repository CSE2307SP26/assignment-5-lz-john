package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import Player;
import Enemy;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	public static void main(String[] args) {
		double lowerVelocity = 0.005;
		double upperVelocity = 0.01;
		int numberOfEnemy = 3;
		double radius = 0.025;
		double playerXPosition = 0.5;
		int s = 0;
		int hs = 0;
		double playerYPosition = 0.5;
		double playerSpeed = 0.01;
		int score = 0;
		int highScore = 0;
		Player player = new Player(playerXPosition, playerYPosition, playerSpeed, score, highScore);
		Enemy[] enemies = new Enemy[numberOfEnemy];
		initializeEnemies(enemies, numberOfEnemy, lowerVelocity, upperVelocity);
		
		StdDraw.enableDoubleBuffering();
		
		long startTime = System.currentTimeMillis();
		long deadTime = System.currentTimeMillis();
		
		while (true) {
			
			StdDraw.clear();
			boolean c = false;
			for(int i = 0; i < numberOfEnemy; i++) {
				enemies[i].move();	
				for(int j = 0; j < numberOfEnemy; j++) {
					if(i != j) {
						enemies[i].collision(enemies[j]);
					}
				}
				
				double d = Math.sqrt(Math.pow(enemies[i].xPosition - playerXPosition, 2) + Math.pow(enemies[i].yPosition - playerYPosition, 2));
				if(d < 2 * radius) {
					c = true;
				}
			}
			
			if(c) {
				numberOfEnemy= 3;
				for(int i = 0; i < numberOfEnemy; i++) {
					enemies[i] = new Enemy(Math.random(), Math.random(), Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity, Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity);
					s = 0;
					startTime = System.currentTimeMillis();
					deadTime = System.currentTimeMillis();
					playerXPosition = 0.5;
					playerYPosition = 0.5;
				}
				
				
			}
			
			if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
				player.move(0, 1);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				player.move(0, -1);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
				player.move(-1, 0);
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
				player.move(1, 0);
			}
			
			long now = System.currentTimeMillis();
			if(now > startTime + 1000) {
				s++;
				if(s > hs) {
					hs = s;
				}
				startTime = now;
			}
			
			if(now > deadTime + 10000) {
				numberOfEnemy++;
				Enemy[] newEnemies = new Enemy[numberOfEnemy];
				for (int i = 0; i < numberOfEnemy - 1; i++) {
					newEnemies[i] = enemies[i];
				}	
				newEnemies[numberOfEnemy - 1] = new Enemy(Math.random(), Math.random(), Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity, Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity);
				enemies = newEnemies;
				deadTime = now;
			}
			StdDraw.setPenColor(Color.red);
			for(int i = 0; i < numberOfEnemy; i++) {
				StdDraw.filledCircle(enemies[i].xPosition, enemies[i].yPosition, enemies[i].radius);
			}
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledCircle(playerXPosition, playerYPosition, player.getRadius());
			StdDraw.text(0.5, 0.1, "Score: " + s + " High Score: " + hs);
			
			StdDraw.show();
			StdDraw.pause(10);
			
		}
	}

	public static void initializeEnemies(Enemy[] enemies, int numberOfEnemy, double lowerVelocity, double upperVelocity) {
			for(int i = 0; i < numberOfEnemy; i++) {
				double ballXPosition = Math.random();
				double ballYPosition = Math.random();
				double ballXVelocity = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
				double ballYVelocity = Math.random() * (upperVelocity - lowerVelocity) + lowerVelocity;
				enemies[i] = new Enemy(ballXPosition, ballYPosition, ballXVelocity, ballYVelocity);
		}
	}

	public boolean checkCollision(Player player, Enemy enemy) {
		double d = Math.sqrt(Math.pow(enemy.xPosition - player.xPosition, 2) + Math.pow(enemy.yPosition - player.yPosition, 2));
		return d < 2 * player.getRadius();
	}

}
