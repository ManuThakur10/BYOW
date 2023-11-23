package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;

import java.awt.*;
public class PlayGame {
    private final TERenderer ter = new TERenderer();

    private World thisWorld;
    private Avatar thisAvatar;
    private Movement thisMovement;
    private TETile[][] clonedTilesWithAvatar;
    private long prevActionTimestamp;
    private long prevFrameTimestamp; //initialized to zero!
    private boolean isGameOver;
    private boolean readyToQuit;
    private String currWorldMoves;
    private static final int SIXTEEN = 16;
    public PlayGame(World thisWorld) {
        this.thisWorld = thisWorld;
        int[] randomGrassCoords = thisWorld.getRandomGrassCoords();
        this.thisAvatar = new Avatar(randomGrassCoords[0], randomGrassCoords[1], Tileset.AVATAR);
        this.thisMovement = new Movement(thisWorld.getWidth(), thisWorld.getHeight(), this.thisWorld, this.thisAvatar);
        currWorldMoves = "";
    }

    public PlayGame(World thisWorld, String loadedGame) {
        this.thisWorld = thisWorld;
        int[] randomGrassCoords = thisWorld.getRandomGrassCoords(); //should still give same pseudorandom #s
        this.thisAvatar = new Avatar(randomGrassCoords[0], randomGrassCoords[1], Tileset.AVATAR);
        this.thisMovement = new Movement(thisWorld.getWidth(), thisWorld.getHeight(), this.thisWorld, this.thisAvatar);
        currWorldMoves = loadedGame;
        loadedGameHelper(loadedGame);
        TETile[][] clonedTiles = thisWorld.getTilesCopy();
        clonedTiles[thisAvatar.getX()][thisAvatar.getY()] = thisAvatar.getAvatar();
        clonedTilesWithAvatar = clonedTiles;

    }

    public void loadedGameHelper(String loadedGame) {
        char[] loadedGameToArray = loadedGame.toCharArray();
        for (int a = 0; a < loadedGameToArray.length; a++) {
            char helper = loadedGameToArray[a];
            if (helper == 'a') {
                thisMovement.moveLeft();
            } else if (helper == 'd') {
                thisMovement.moveRight();
            } else if (helper == 's') {
                thisMovement.moveDown();
            } else if (helper == 'w') {
                thisMovement.moveUp();
            }
        }
    }
    public Avatar getAvatar() {
        return thisAvatar;
    }

    public void runGame() {
        ter.initialize(thisWorld.getWidth(), thisWorld.getHeight() + 5); //offset for gui
        while (!isGameOver) {
            if (shouldRenderNewFrame()) {
                updateGame();
                renderBoard();
                mouseHover();
            }
        }
    }

    public void renderBoard() {
        ter.renderFrame(clonedTilesWithAvatar);
    }

    public void updateGame() {
        if (StdDraw.hasNextKeyTyped()) {
            char helper = StdDraw.nextKeyTyped();
            if ((helper == 'q' || helper == 'Q') && readyToQuit) {
                isGameOver = true;
                quitAndSave();
            } else if (helper == 'a') {
                currWorldMoves += 'a';
                thisMovement.moveLeft();
                readyToQuit = false;
            } else if (helper == 'd') {
                currWorldMoves += 'd';
                thisMovement.moveRight();
                readyToQuit = false;
            } else if (helper == 's') {
                currWorldMoves += 's';
                thisMovement.moveDown();
                readyToQuit = false;
            } else if (helper == 'w') {
                currWorldMoves += 'w';
                thisMovement.moveUp();
                readyToQuit = false;
            } else if (helper == ':') {
                readyToQuit = true;
            } else {
                readyToQuit = false;
            }
        }
        TETile[][] clonedTiles = thisWorld.getTilesCopy();
        //.clone() results in a shallow copy, which for some reason keeps previous positions
        // of the character. I don't know why, though.
        clonedTiles[thisAvatar.getX()][thisAvatar.getY()] = thisAvatar.getAvatar();
        clonedTilesWithAvatar = clonedTiles;
    }

    public void quitAndSave() {
        FileUtils.writeFile("thisGame.txt", "n" + thisWorld.getSeed() + "s" + currWorldMoves);
        //looks like this overrides anything in the file so it works perfectly
        System.exit(0);
    }

    public void mouseHover() {
        int mouseX = (int) StdDraw.mouseX();
        int mouseY = (int) StdDraw.mouseY();
        if (mouseX > 0 && mouseX < thisWorld.getWidth() && mouseY > 0 && mouseY < thisWorld.getHeight()) {
            TETile underMouse = clonedTilesWithAvatar[mouseX][mouseY];
            String displayTile = underMouse.description();
            StdDraw.setPenColor(Color.white);
            StdDraw.text(5, thisWorld.getHeight() + 3, "Tile type: " + displayTile, 0);
            StdDraw.show();
        }
    }

    public boolean shouldRenderNewFrame() {
        if (frameDeltaTime() > SIXTEEN) {
            resetFrameTimer();
            return true;
        }
        return false;
    }

    private long actionDeltaTime() {
        return System.currentTimeMillis() - prevActionTimestamp;
    }

    private long frameDeltaTime() {
        return System.currentTimeMillis() - prevFrameTimestamp;
    }

    private void resetFrameTimer() {
        prevFrameTimestamp = System.currentTimeMillis();
    }

    public World getThisWorld() {
        return thisWorld;
    }

    public TETile[][] getClonedTilesWithAvatar() {
        return clonedTilesWithAvatar;
    }

    public String getCurrWorldMoves() {
        return currWorldMoves;
    }

    //    public static void main(String[] args) {
    //        long seed = 10;
    //        World testWorld = new World(50, 40, seed);
    //        PlayGame testGame = new PlayGame(testWorld, "ssss");
    //        testGame.runGame();
    //
    //    }
}
