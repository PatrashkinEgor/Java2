package Lesson5;

/**
 * Java2. Lesson 5. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 13, 2018
 */

import java.util.Arrays;

/**
 * Задание:
 * 1. Необходимо написать два метода, которые делают следующее:
 * 1) Создают одномерный длинный массив;
 * 2) Заполняют этот массив единицами;
 * 3) Засекают время выполнения
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis();
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
 *
 * Отличие первого метода от второго:
 * Первый просто бежит по массиву и вычисляет значения.
 * Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
 * */




public class Java2Lesson5HW {
    private static final int SIZE = 10000000;       //Длина исходного массива
    private static final int HALF_SIZE = SIZE / 2;  //Длина подмассивов при делении на два потока
    private static final int  THIRD_SIZE = SIZE /3; //Длина подмассивов при делении на три потока(к длине одного из
    // массивов будет добавлена 1, так как SIZE не кратен 3)
    private static final int  QUARTER_SIZE = SIZE /4;//Длина подмассивов при делении на четыре потока

    public static void main(String[] args) {
        System.out.println(Arrays.equals(singleThreadMethod(), doubleThreadMethod()));
        threeThreadMethod();
        fourThreadMethod();
    }


    public static float[] singleThreadMethod(){
        float[] arr = new float[SIZE];

        for (int i = 0; i < arr.length; i++) {  //Заполняем массив единицами
            arr[i] = 1.0f;
        }

        long startTime = System.currentTimeMillis();    //Засекаем время старта

        MyThread mt1 = new MyThread("singleThreadMethod",arr);  //Создаем поток считающий формулу
        do {
            try {
                Thread.sleep(0, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (mt1.thrd.isAlive());                                   //Ждем пока поток не завершится

        System.out.println("Single thread method was performed in " + (System.currentTimeMillis() - startTime) + " ms");
        return arr;
    }


    public static float[] doubleThreadMethod(){

        float[] arr = new float[SIZE];

        for (int i = 0; i < arr.length; i++) {                  //Заполняем массив единицами
            arr[i] = 1.0f;
        }

        float[] firstHalfOfArr = new float[HALF_SIZE];
        float[] secondHalfOfArr = new float[HALF_SIZE];

        long startTime = System.currentTimeMillis();            //Засекаем время старта

        System.arraycopy(arr, 0, firstHalfOfArr, 0, HALF_SIZE); //"Делим" на два массива
        System.arraycopy(arr, HALF_SIZE, secondHalfOfArr, 0, HALF_SIZE);

        MyThread mt1 = new MyThread("doubleThreadMethod1",firstHalfOfArr); //Запускаем два потока
        MyThread mt2 = new MyThread("doubleThreadMethod2",secondHalfOfArr);
        do {
            try {
                Thread.sleep(0, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (mt1.thrd.isAlive()||mt2.thrd.isAlive());                         //Ждем пока потоки не завершится

        System.arraycopy(firstHalfOfArr, 0, arr, 0, HALF_SIZE);     //"Склеиваем" в один массив
        System.arraycopy(secondHalfOfArr, 0, arr, HALF_SIZE, HALF_SIZE);

        System.out.println("Double thread method was performed in " + (System.currentTimeMillis() - startTime) + " ms");
        return arr;
    }


    public static float[] threeThreadMethod(){ //Дополнительный метод выполняющий задачу с разбиением на три потока,
        // выполнено аналогично методу doubleThreadMethod().

        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }

        float[] firstPartOfArr = new float[THIRD_SIZE];
        float[] secondPartOfArr = new float[THIRD_SIZE];
        float[] thirdPartOfArr = new float[THIRD_SIZE+1];

        long startTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, firstPartOfArr, 0, THIRD_SIZE);
        System.arraycopy(arr, THIRD_SIZE, secondPartOfArr, 0, THIRD_SIZE);
        System.arraycopy(arr, THIRD_SIZE*2, thirdPartOfArr, 0, THIRD_SIZE+1);

        MyThread mt1 = new MyThread("threeThreadMethod1",firstPartOfArr);
        MyThread mt2 = new MyThread("threeThreadMethod2",secondPartOfArr);
        MyThread mt3 = new MyThread("threeThreadMethod3",thirdPartOfArr);

        do {
            try {
                Thread.sleep(0, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (mt1.thrd.isAlive() || mt2.thrd.isAlive() || mt3.thrd.isAlive());

        System.arraycopy(firstPartOfArr, 0, arr, 0, THIRD_SIZE);
        System.arraycopy(secondPartOfArr, 0, arr, THIRD_SIZE, THIRD_SIZE);
        System.arraycopy(thirdPartOfArr, 0, arr, THIRD_SIZE*2, THIRD_SIZE+1);

        System.out.println("The three-thread method was performed in " + (System.currentTimeMillis() - startTime) + " ms");
        return arr;
    }

    public static float[] fourThreadMethod(){//Дополнительный метод выполняющий задачу с разбиением на четыре потока,
        // выполнено аналогично методу doubleThreadMethod().
        float[] arr = new float[SIZE];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        float[] firstPartOfArr = new float[QUARTER_SIZE];
        float[] secondPartOfArr = new float[QUARTER_SIZE];
        float[] thirdPartOfArr = new float[QUARTER_SIZE];
        float[] fourthPartOfArr = new float[QUARTER_SIZE];

        long startTime = System.currentTimeMillis();

        System.arraycopy(arr, 0, firstPartOfArr, 0, QUARTER_SIZE);
        System.arraycopy(arr, QUARTER_SIZE, secondPartOfArr, 0, QUARTER_SIZE);
        System.arraycopy(arr, QUARTER_SIZE*2, thirdPartOfArr, 0, QUARTER_SIZE);
        System.arraycopy(arr, QUARTER_SIZE*3, fourthPartOfArr, 0, QUARTER_SIZE);

        MyThread mt1 = new MyThread("fourThreadMethod1",firstPartOfArr);
        MyThread mt2 = new MyThread("fourThreadMethod2",secondPartOfArr);
        MyThread mt3 = new MyThread("fourThreadMethod3",thirdPartOfArr);
        MyThread mt4 = new MyThread("fourThreadMethod4",fourthPartOfArr);

        do {
            try {
                Thread.sleep(0, 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (mt1.thrd.isAlive() || mt2.thrd.isAlive() || mt3.thrd.isAlive()|| mt4.thrd.isAlive());

        System.arraycopy(firstPartOfArr, 0, arr, 0, QUARTER_SIZE);
        System.arraycopy(secondPartOfArr, 0, arr, QUARTER_SIZE, QUARTER_SIZE);
        System.arraycopy(thirdPartOfArr, 0, arr, QUARTER_SIZE*2, QUARTER_SIZE);
        System.arraycopy(fourthPartOfArr, 0, arr, QUARTER_SIZE*3, QUARTER_SIZE);

        System.out.println("The four-thread method was performed in " + (System.currentTimeMillis() - startTime) + " ms");
        return arr;
    }


    /**
     * Функция рассчета значения по "Хитрой" формуле
     * @param arr входной массив
     * @return
     */
    public static float[] calc(float[] arr) {
        for (int i = 0; i < arr.length; i++)
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        return arr;
    }
}

/**
 *  Поток
 */
class MyThread implements Runnable{

    Thread thrd;
    float[] arr;
    MyThread(String name, float[] arr){
        this.arr = arr;
        thrd = new Thread(this,name);
        thrd.start();
    }

    @Override
    public void run() {
        System.out.println(thrd.getName() + " thread is started.");
        Java2Lesson5HW.calc(arr);
        System.out.println(thrd.getName() + " thread is finished.");
    }

}

