public abstract class Animal {
    public abstract void voice();

    public void jump() {
        System.out.println("ЖИвотное подпрыгнуло");
    }
}

class Cat extends Animal {
    @Override
    public void voice() {
        System.out.println("Кот мяукнул");
    }
}


class MainClass {
    public static void main(String[] args) {

        short a = 1;
        int b = 0;
        a = (short)b;
//        Animal b = new Animal();
//        Object o = new Cat();
//        ((Cat) o).voice();
//        Animal a = new Cat();
//        a.voice();
    }
}

