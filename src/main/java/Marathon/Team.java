package Marathon;

/**
 * Java2. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Oct 25, 2018
 */



public class Team {
    String nameOfTheTeam;
    Competitor[] competitor = new Competitor[4];



    public Team(String teamName, String nameOfFirstHuman, String nameOfSecondHuman,String nameOfCat, String nameOfDog) {
        this.nameOfTheTeam = teamName;
        competitor[0] = new Human(nameOfFirstHuman);
        competitor[1] = new Human(nameOfSecondHuman);
        competitor[2] = new Cat(nameOfCat);
        competitor[3] = new Dog(nameOfDog);
    }


    public void isOnDistance() {
        for (Competitor с : competitor) {
            if (с.isOnDistance()) {
                с.info();
            }
        }
    }

    public void showResults() {
        System.out.println("\n" +"Результат команды " + nameOfTheTeam +":");
        for (Competitor с : competitor) {
            с.info();
        }
    }



}