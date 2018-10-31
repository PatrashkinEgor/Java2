package Lesson2;


/**
 * Java2. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Oct 29, 2018
 */


/**
 * Задание:
 * 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4, при подаче массива другого
 * размера необходимо бросить исключение MyArraySizeException.
 * 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать. Если в каком-то
 * элементе массива преобразование не удалось (например, в ячейке лежит символ или текст вместо числа), должно быть
 * брошено исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
 * 3. В методе main() вызвать полученный метод, обработать возможные исключения MySizeArrayException и
 * MyArrayDataException, и вывести результат расчета.
 */

public class Java2Lesson2HW {
    public static void main(String[] args) {


        String[][][] arr = { //Массив проверочных массивов
                {
                        // Правильный массив
                        {"1", "2", "3", "4"},
                        {"5", "6", "7", "8"},
                        {"9", "10", "11", "12"},
                        {"13", "14", "15", "16"},
                },
                {
                        // Массив некорректоной длины
                        {"1", "2", "3", "4"},
                        {"5", "6", "7", "8"},
                        {"9", "10", "11", "12"},
                        {"13", "14", "15", "16"},
                        {"17", "18", "19", "20"},
                },
                {
                        // Массив с символьным значением
                        {"1", "S", "3", "4"},
                        {"5", "6", "7", "8"},
                        {"9", "10", "11", "12"},
                        {"13", "14", "15", "16"},
                },
                {       //Массив с long
                        {"1", "2", "3", "4"},
                        {"5", "6", "7", "8"},
                        {"9", "10", "11", "12"},
                        {"13", "14", "15", "2147483648"},
                }
        };

        //Выполняем проверку написанной функции
        for (String subArr[][]:arr) {
            try {
                System.out.println("The sum of the numbers in the array is: "+ sumOfArrayCells(subArr));
            } catch(MyArrayDataException e) {
                e.printStackTrace();
            } catch(MySizeArrayException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Метод преобразующий строки в массиве в числа и считающий их сумму.
     * @param arr входной массив, должен иметь размер 4*4.
     * @return  сумма чисел всех ячеек в формате long.
     * @throws MyArrayDataException выбрасывается при несоответствии данных в строке формату int;
     * @throws MySizeArrayException выбрасывается неудовлетворительном размере входного массива;
     */

    static long sumOfArrayCells( String[][] arr) throws MyArrayDataException,MySizeArrayException{
        long sum=0;
        if ((arr.length!=4)||(arr[0].length!=4)){       // Примем что все подмассивы равной длины.
            throw new MySizeArrayException();
        }
        for (int i =0; i<4;i++) {
            for (int j =0; j<4;j++) {
                try{
                    sum += Integer.parseInt(arr[i][j]);
                } catch (
                        NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }





}
