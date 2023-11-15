package core;

import tileengine.TETile;
import tileengine.Tileset;

import java.util.Random;

public class AutograderBuddy {
    private static final int SIXTY = 60;
    private static final int THIRTY = 30;


    /**
     * Simulates a game, but doesn't render anything or call any StdDraw
     * methods. Instead, returns the world that would result if the input string
     * had been typed on the keyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quit and
     * save. To "quit" in this method, save the game to a file, then just return
     * the TETile[][]. Do not call System.exit(0) in this method.
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public static TETile[][] getWorldFromInput(String input) {
        Random r = new Random();
        long seedLong;
        String stringBuild = "";
        char[] parsedString = input.toCharArray();
        if (parsedString[0] == 'n' || parsedString[0] == 'N') { //Single quotes for chars, double for strings!
            int index = 1;
            while (index < parsedString.length && (parsedString[index] != 's' && parsedString[index] != 'S')) {
                stringBuild += parsedString[index];
                index++;
            }
        }
        if (stringBuild.isBlank()) {
            seedLong = r.nextLong();
        } else {
            seedLong = Long.parseLong(stringBuild);
        }
        World ourWorld = new World(SIXTY, THIRTY, seedLong);
        return ourWorld.getTiles();
    }


    /**
     * Used to tell the autograder which tiles are the floor/ground (including
     * any lights/items resting on the ground). Change this
     * method if you add additional tiles.
     */
    public static boolean isGroundTile(TETile t) {
        return t.character() == Tileset.FLOOR.character()
                || t.character() == Tileset.AVATAR.character()
                || t.character() == Tileset.GRASS.character()
                || t.character() == Tileset.FLOWER.character();
    }

    /**
     * Used to tell the autograder while tiles are the walls/boundaries. Change
     * this method if you add additional tiles.
     */
    public static boolean isBoundaryTile(TETile t) {
        return t.character() == Tileset.WALL.character()
                || t.character() == Tileset.LOCKED_DOOR.character()
                || t.character() == Tileset.UNLOCKED_DOOR.character();
    }
}
