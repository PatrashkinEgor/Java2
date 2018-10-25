public class Box {
    void clap() {
        System.out.println("test Box");
    }
}

class BigBox extends Box {
    @Override
    void clap() {
        super.clap();
        System.out.println("test BigBox");
    }
}

class MainBox {
    public static void main(String[] args) {
        Box box = new Box();
        BigBox bbox = new BigBox();
//        box.clap();
        bbox.clap();

    }

}
