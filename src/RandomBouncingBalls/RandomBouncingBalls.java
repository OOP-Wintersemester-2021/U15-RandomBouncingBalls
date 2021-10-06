package RandomBouncingBalls;

import de.ur.mi.oop.app.GraphicsApp;
import de.ur.mi.oop.colors.Color;
import de.ur.mi.oop.colors.Colors;
import de.ur.mi.oop.launcher.GraphicsAppLauncher;

public class RandomBouncingBalls extends GraphicsApp {

    /* private Constants */
    private static final int CANVAS_HEIGHT = 800;
    private static final int CANVAS_WIDTH = 800;
    private static final int FRAME_RATE = 60;
    private static final Color BACKGROUND_COLOR = Colors.WHITE;
    private static final float MIN_BALL_VELOCITY = 2;
    private static final float MAX_BALL_VELOCITY = 10;

    private RandomBall ballOne;
    private RandomBall ballTwo;

    /**
     * the initialize-Methode gets called a single time at the start of the programm
     **/

    @Override
    public void initialize() {
        setupCanvas();
        setupRandomBalls();
    }

    private void setupRandomBalls() {
        ballOne = new RandomBall(CANVAS_WIDTH, CANVAS_HEIGHT, MIN_BALL_VELOCITY, MAX_BALL_VELOCITY);
        ballTwo = new RandomBall(CANVAS_WIDTH, CANVAS_HEIGHT, MIN_BALL_VELOCITY, MAX_BALL_VELOCITY);
    }

    private void setupCanvas() {
        setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        setFrameRate(FRAME_RATE);
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
        drawBall(ballOne);
        drawBall(ballTwo);
    }

    private void drawBall(RandomBall ball) {
        ball.checkWallCollision(CANVAS_WIDTH, CANVAS_HEIGHT);
        ball.update();
        ball.draw();
    }

    public static void main(String[] args) {
        GraphicsAppLauncher.launch();
    }
}
