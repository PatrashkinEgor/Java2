package Lesson2;

/**
 * Java2. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Oct 29, 2018
 */


public class MySizeArrayException extends ArrayIndexOutOfBoundsException {

    public MySizeArrayException() {
        super("Invalid array size!");
    }
}