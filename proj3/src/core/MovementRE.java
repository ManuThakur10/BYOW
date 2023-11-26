//package core;
//
//import tileengine.TETile;
//
//public class MovementRE {
//    private int WIDTH;
//
//    private int GAME_HEIGHT;
//
//    RandomEncounter re;
//    TETile[][] reArray;
//    Avatar playerAvatar;
//    public MovementRE(int width, int gameHeight, RandomEncounter re, Avatar avatar) {
//        this.WIDTH = width;
//        this.GAME_HEIGHT = gameHeight;
//        this.re = re;
//        this.playerAvatar = avatar;
//
//        //        this.worldArray = world.getTiles();
//
//    }
//
//    public boolean canMove(int deltaX, int deltaY) {
//        reArray = re.getTiles();
//        return reArray[playerAvatar.getX() + deltaX][playerAvatar.getY() + deltaY] == world.getGroundTile();
//    }
//
//    public void moveUp() {
//        if (canMove(0, 1)) {
//            playerAvatar.setY(1);
//        }
//    }
//    public void moveDown() {
//        if (canMove(0, -1)) {
//            playerAvatar.setY(-1);
//        }
//    }
//    public void moveLeft() {
//        if (canMove(-1, 0)) {
//            playerAvatar.setX(-1);
//        }
//    }
//    public void moveRight() {
//        if (canMove(1, 0)) {
//            playerAvatar.setX(1);
//        }
//    }
//
//
//
//}
