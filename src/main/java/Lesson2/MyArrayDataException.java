package Lesson2;

/**
 * Java2. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Oct 29, 2018
 */

public class MyArrayDataException extends NumberFormatException {

    public MyArrayDataException(int row, int col) {
        super("Not integer data format in cell [" + (col+1) + "; " + (row+1) + "]!");
    }
}