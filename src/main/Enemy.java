package main;

public class Enemy {
    public double xPosition;
    public double yPosition;
    public double xVelocity;
    public double yVelocity;
    public double upperVelocity = 0.01;
    public double lowerVelocity = 0.005;
    public double radius = 0.025;
    
    public Enemy(double x, double y, double xv, double yv) {
        this.xPosition = x;
        this.yPosition = y;
        this.xVelocity = xv;
        this.yVelocity = yv;
    }

    public void move() {
        this.xPosition += xVelocity;
        this.yPosition += yVelocity;
        if(this.xPosition + this.radius > 1 || this.xPosition - this.radius < 0) { 
            this.xVelocity = -this.xVelocity;
        }
        if(this.yPosition + this.radius > 1 || this.yPosition - this.radius < 0) { 
            this.yVelocity = -this.yVelocity;
        }
    }

    public void collision(Enemy other) {
        double d = Math.sqrt(Math.pow(this.xPosition - other.xPosition, 2) + Math.pow(this.yPosition - other.yPosition, 2));
        if(d < 2 * this.radius) {
            this.xVelocity = -this.xVelocity;
            this.yVelocity = -this.yVelocity;
        }
    }

}
