import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.graphics.Circle;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

import java.util.Random;

public class RandomBouncingBalls extends GraphicsApp {

    /* private Constants */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final float MIN_BALL_VELOCITY = 2;
    private static final float MAX_BALL_VELOCITY = 10;
    private static final int MIN_DIAMETER = 50;
    private static final int MAX_DIAMETER = 100;
    private static final int RGB_MAX = 255;
    private static final int BALL_NUMBER = 2;

    private Random rand;

    private Circle[] randomBalls;
    private float[] xSpeeds;
    private float[] ySpeeds;

    /**
     * the initialize-Methode gets called a single time at the start of the programm
     **/

    @Override
    public void initialize() {
        setupCanvas();
        rand = new Random();
        setupRandomBalls();
        setUpSpeeds();
    }

    private void setupRandomBalls() {
        randomBalls = new Circle[BALL_NUMBER];
        for (int i = 0; i < BALL_NUMBER; i++) {
            setUpBall(i);
        }
    }

    private void setUpBall(int idx) {
        int randomRadius = getRandomNumber(MIN_DIAMETER/2, MAX_DIAMETER/2);
        int randomXPos = getRandomNumber(randomRadius, CANVAS_WIDTH - randomRadius);
        int randomYPos = getRandomNumber(randomRadius, CANVAS_HEIGHT - randomRadius);
        Color randomColor = getRandomColor();
        randomBalls[idx] = new Circle(randomXPos, randomYPos, randomRadius, randomColor);
    }

    private void setUpSpeeds() {
        xSpeeds = new float[BALL_NUMBER];
        ySpeeds = new float[BALL_NUMBER];
        for (int i = 0; i < BALL_NUMBER; i++) {
            xSpeeds[i] =  getRandomFloat(MIN_BALL_VELOCITY, MAX_BALL_VELOCITY);
            ySpeeds[i] = getRandomFloat(MIN_BALL_VELOCITY, MAX_BALL_VELOCITY);
        }
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
    }

    private float getRandomFloat(float min, float max) {
        return rand.nextFloat() * (max - min) + min;
    }

    private int getRandomNumber(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private Color getRandomColor() {
        int r = getRandomNumber(0, RGB_MAX);
        int g = getRandomNumber(0, RGB_MAX);
        int b = getRandomNumber(0, RGB_MAX);

        return new Color(r, g, b);
    }

    /**
     * the draw-method gets called repeated as long the programm is running
     **/

    @Override
    public void draw() {
        drawBackground(BACKGROUND_COLOR);
        drawBalls();
    }

    private void drawBalls() {
        for (int i = 0; i < BALL_NUMBER; i++) {
            drawBall(i);
        }
    }

    private void drawBall(int idx) {
        Circle ball = randomBalls[idx];
        update(ball, xSpeeds[idx], ySpeeds[idx]);
        if(checkXWallCollision(ball)) {
            xSpeeds[idx] *= -1;
            ball.setColor(getRandomColor());
        }
        if(checkYWallCollision(ball)) {
            ySpeeds[idx] *= -1;
            ball.setColor(getRandomColor());
        }
        ball.draw();
    }
    
    private void update(Circle ball, float xSpeed, float ySpeed) {
        ball.move(xSpeed, ySpeed);
    }

    private boolean checkXWallCollision(Circle ball) {
        float ballXPos = ball.getXPos();
        float ballRadius = ball.getRadius();
        if(ballXPos - ballRadius <= 0 || ballXPos + ballRadius >= CANVAS_WIDTH) {
            return true;
        }
        return false;
    }
    
    private boolean checkYWallCollision(Circle ball) {
        float ballYPos = ball.getYPos();
        float ballRadius = ball.getRadius();
        if (ballYPos - ballRadius <= 0 || ballYPos + ballRadius >= CANVAS_HEIGHT) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
