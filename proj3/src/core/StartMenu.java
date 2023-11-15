package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.math.BigInteger;
import java.util.Random;

import static java.awt.Font.*;
public class StartMenu {
    private static final double POINT2 = 0.2;
    private static final int THIRTY = 30;
    private static final int TWENTY = 20;
    private static final int FIFTY = 50;
    private static final int SIXTY = 60;
    private static final double POINT5 = 0.5;
    private static final double POINT4 = 0.4;

    private static final double POINT8 = 0.8;
    private static final double POINT3 = 0.3;
    private static final double POINT35 = 0.35;
    private static final double POINT05 = 0.05;

    public static void startMenuInitializer() {
        startMenuDisplay();
        while (true) {
            if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                StdDraw.text(POINT5, POINT2, "CS61B: The Game");
                inputMenu();
            }
        }
    }

    public static void startMenuDisplay() {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.white);
        StdDraw.setPenRadius(5.0); //Had a dream about this!
        Font bigFont = new Font(SANS_SERIF, PLAIN, THIRTY);
        StdDraw.setFont(bigFont);
        StdDraw.text(POINT5, POINT8, "CS61B: The Game");
        Font smallFont = new Font(SANS_SERIF, PLAIN, TWENTY);
        StdDraw.setFont(smallFont);
        StdDraw.text(POINT5, POINT4, "New Game (N)");
        StdDraw.text(POINT5, POINT35, "Load Game (L)");
        StdDraw.text(POINT5, POINT3, "Quit (Q)");
    }
    public static void inputMenu() {
        StdDraw.clear(Color.black);
        StdDraw.setPenColor(Color.white);
        Font bigFont = new Font(SANS_SERIF, PLAIN, THIRTY);
        StdDraw.setFont(bigFont);
        StdDraw.text(POINT5, POINT8, "Start New Game");
        Font smallFont = new Font(SANS_SERIF, PLAIN, TWENTY);
        StdDraw.setFont(smallFont);
        StdDraw.text(POINT3, POINT4, "Seed: ");
        double inputX = 0;
        String seed = "";
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                Character nextKey = StdDraw.nextKeyTyped();
                if (Character.isDigit(nextKey)) {
                    seed += nextKey;
                    String insert = String.valueOf(nextKey);
                    StdDraw.text(POINT3 + inputX, POINT2, insert);
                    inputX += POINT05;
                }
                if (nextKey == 'S' || nextKey == 's') {
                    break;
                }

            }
        }
        long inputSeed;
        Random testRandom = new Random();
        if (seed.isBlank()) {
            inputSeed = testRandom.nextLong();
        } else {
            BigInteger seedToBigIntForCompare = new BigInteger(seed);
            if (seedToBigIntForCompare.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) <= 0
                && seedToBigIntForCompare.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) >= 0) {
                inputSeed = Long.parseLong(seed);
            } else {
                inputSeed = testRandom.nextLong();
            }
        }
        TERenderer ter = new TERenderer();
        ter.initialize(SIXTY, FIFTY);
        World testWorld = new World(SIXTY, FIFTY, inputSeed);
        ter.renderFrame(testWorld.getTiles());

    }
    public static void main(String[] args) {
        startMenuInitializer();
    }
}
