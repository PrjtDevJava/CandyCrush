package model;

public enum Type {

    EMPTY(true),
    NORMAL(true),
    BLOCK(false),
    BOMB(true);
    boolean gravity;

    Type(boolean gravity) {
        this.gravity = gravity;
    }

    public boolean getGravity() {
        return this.gravity;
    }
}
