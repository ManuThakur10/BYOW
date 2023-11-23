package core;

import tileengine.TETile;

public class Movement {
    private int WIDTH;

    private int GAME_HEIGHT;

    World world;
    TETile[][] worldArray;
    Avatar playerAvatar;
    public Movement(int width, int gameHeight, World world, Avatar avatar) {
        this.WIDTH = width;
        this.GAME_HEIGHT = gameHeight;
        this.world = world;
        this.playerAvatar = avatar;

        //        this.worldArray = world.getTiles();

    }

    public boolean canMove(int deltaX, int deltaY) {
        worldArray = world.getTiles();
        return worldArray[playerAvatar.getX() + deltaX][playerAvatar.getY() + deltaY] == world.getGroundTile();
    }

    public void moveUp() {
        if (canMove(0, 1)) {
            playerAvatar.setY(1);
        }
    }
    public void moveDown() {
        if (canMove(0, -1)) {
            playerAvatar.setY(-1);
        }
    }
    public void moveLeft() {
        if (canMove(-1, 0)) {
            playerAvatar.setX(-1);
        }
    }
    public void moveRight() {
        if (canMove(1, 0)) {
            playerAvatar.setX(1);
        }
    }



}
