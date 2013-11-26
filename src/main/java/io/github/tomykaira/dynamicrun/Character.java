package io.github.tomykaira.dynamicrun;

abstract public class Character {
    public abstract void walk();

    protected void up() {
        System.out.println("up");
    }

    protected void down() {
        System.out.println("down");
    }

    protected void left() {
        System.out.println("left");
    }

    protected void right() {
        System.out.println("right");
    }
}
