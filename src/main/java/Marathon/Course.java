package Marathon;

/**
 * Java2. Lesson 1. Homework
 *
 * @author Egor Patrashkin
 * @version dated Oct 25, 2018
 */

public class Course {

    Barrier[] barriers = new Barrier[3];

    public Course(int crossLength, int poolLength, int wallHeight) {
        barriers[0] = new Cross(crossLength);
        barriers[1] = new Water(poolLength);
        barriers[2] = new Wall(wallHeight);
    }

    public void doIt(Team t){
        for (Competitor с : t.competitor) {
            for (Barrier b : barriers){
                b.doIt(с);
            }
        }
    }

}
