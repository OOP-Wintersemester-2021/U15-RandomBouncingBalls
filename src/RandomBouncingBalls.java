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

    private Circle ball1;
    private Circle ball2;
    private float[] ball1Vector;
    private float[] ball2Vector;

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
        ball1 = setUpBall();
        ball2 = setUpBall();
    }

    private Circle setUpBall() {
        int randomRadius = getRandomNumber(MIN_DIAMETER/2, MAX_DIAMETER/2);
        int randomXPos = getRandomNumber(randomRadius, CANVAS_WIDTH - randomRadius);
        int randomYPos = getRandomNumber(randomRadius, CANVAS_HEIGHT - randomRadius);
        Color randomColor = getRandomColor();
        return new Circle(randomXPos, randomYPos, randomRadius, randomColor);
    }


    private void setUpSpeeds() {
        ball1Vector = getRandomVector();
        ball2Vector = getRandomVector();
    }

    private float[] getRandomVector() {
        float[] vector = new float[2];
        vector[0] = getRandomFloat(MIN_BALL_VELOCITY, MAX_BALL_VELOCITY);
        vector[1] = getRandomFloat(MIN_BALL_VELOCITY, MAX_BALL_VELOCITY);
        return vector;
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
        drawAndUpdateBall(ball1, ball1Vector);
        drawAndUpdateBall(ball2, ball2Vector);
    }

    private void drawAndUpdateBall(Circle ball, float[] vector) {
        update(ball, vector);
        checkWallCollision(ball, vector);
        ball.draw();
    }

    private void update(Circle ball, float[] vector) {
        ball.move(vector[0], vector[1]);
    }

    private void checkWallCollision(Circle ball, float[] vector) {
        checkXWallCollision(ball, vector);
        checkYWallCollision(ball, vector);
    }

    private void checkXWallCollision(Circle ball, float[] vector) {
        float ballXPos = ball.getXPos();
        float ballRadius = ball.getRadius();

        if(ballXPos - ballRadius <= 0 || ballXPos + ballRadius >= CANVAS_WIDTH) {
            vector[0] *= -1;
            ball.setColor(getRandomColor());
        }
    }

    private void checkYWallCollision(Circle ball, float[] vector) {
        float ballYPos = ball.getYPos();
        float ballRadius = ball.getRadius();

        if(ballYPos - ballRadius <= 0 || ballYPos + ballRadius >= CANVAS_HEIGHT) {
            vector[1] *= -1;
            ball.setColor(getRandomColor());
        }
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
