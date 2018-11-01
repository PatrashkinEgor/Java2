package Lesson3;

/**
 * Java2. Lesson 3. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 01, 2018
 */

import java.util.ArrayList;

/**
 * Задание.
 *
 * 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). Найти и вывести список уникальных слов,
 * из которых состоит массив (дубликаты не считаем). Посчитать, сколько раз встречается каждое слово.
 */
public class Glossary {



    public static void main(String[] args) {


        ArrayList<Word> listOfWords = new ArrayList<Word>();
        String[] arr = {"a","b","c","d","a","f","g","h","i","j","k","l","m","n","o"};
        for (String s:arr) {
            for (int i = 0; i<= listOfWords.size(); i++){
                if (listOfWords.size() == i){
                    listOfWords.add(new Word(s));
                    break;
                }
                if (listOfWords.get(i).spelling.equalsIgnoreCase(s)){
                    Word temp = listOfWords.get(i);
                    temp.quantity++;
                    listOfWords.set(i,temp);
                }
            }
        }

        System.out.println(listOfWords.toString());
    }

}

class Word{
    String spelling;
    int quantity;

    public Word(String spelling) {
        this.quantity = 1;
        this.spelling = spelling;
    }

    @Override
    public String toString(){
        return "The word : '" + spelling + "' occurs " + quantity + " times."+ "\n";
    }



}
