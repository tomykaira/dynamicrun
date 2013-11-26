package io.github.tomykaira.dynamicrun;

/**
 * Sample source code to copy & paste
 */
public class RandomCharacter extends Character {

    @Override
    public void walk() {
        int type = (int)(Math.random() * 4);
        switch (type) {
            case 0:
                up();
                break;
            case 1:
                down();
                break;
            case 2:
                left();
                break;
            case 3:
                right();
                break;
        }
    }
}
