package Lesson3;

import java.util.HashMap;

/**
 * Java2. Lesson 3. Homework
 *
 * @author Egor Patrashkin
 * @version dated Nov 01, 2018
 */

/**
 * Задание.
 * 2.Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
 * В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать номер
 * телефона по фамилии. Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
 * тогда при запросе такой фамилии должны выводиться все телефоны.
 * Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись добавлять еще
 * дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем через консоль и т.д. Консоль
 * желательно не использовать для ввода, тестировать просто из метода main(), прописывая add() и get().
 */

public class PhonebookTest {
    public static void main(String[] args) {
        Phonebook phonebook = new Phonebook();
        phonebook.addPerson(222322,"Petya");
        phonebook.addPerson(321445,"Vasya");
        phonebook.addPerson(734654,"Vova");
        phonebook.addPerson(535415,"Dima");
        phonebook.addPerson(654622,"Masha");
        phonebook.addPerson(266666,"Dasha");
        phonebook.addPerson(645454,"Vova");
        phonebook.addPerson(222322,"Lena");

        phonebook.getNumber("Vova");
        phonebook.getNumber("Lena");
    }
}





class Phonebook {
    HashMap<Integer,String> hs;
    public Phonebook(){
        hs= new HashMap();
    }

    public void addPerson(int phoneNumber, String Name){
        hs.put(phoneNumber,Name);
    }

    public void getNumber(String Name){
        System.out.println("The phone number of " + Name + " is:");
        for(HashMap.Entry<Integer,String> o : hs.entrySet()){
            if (o.getValue().equals(Name)){
                System.out.println(o.getKey());
            }
        }
    }
}
