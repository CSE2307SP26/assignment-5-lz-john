package main;

public class Player {
    public double xPosition;
    public double yPosition;
    public double speed;
    public double currentScore;
    public double highScore;
    public double radius = 0.025;
    
    public Player(double x, double y, double s, double currentScore, double highScore) {
        this.xPosition = x;
        this.yPosition = y;
        this.speed = s;
        this.currentScore = currentScore;
        this.highScore = highScore;
    }
    
    public void move(double xDirection, double yDirection) {
        this.xPosition += xDirection * speed;
        this.yPosition += yDirection * speed;
        if (this.xPosition < 0) this.xPosition = 0;
        if (this.xPosition > 1) this.xPosition = 1;
        if (this.yPosition < 0) this.yPosition = 0;
        if (this.yPosition > 1) this.yPosition = 1;
    }

    public void updateScore(double score) {
        this.currentScore = score;
        if (score > this.highScore) {
            this.highScore = score;
        }
    }

    public double getRadius() {
        return radius;
    }

    public void reset() {
        this.xPosition = 0.5;
        this.yPosition = 0.5;
        this.currentScore = 0;
    }

}
