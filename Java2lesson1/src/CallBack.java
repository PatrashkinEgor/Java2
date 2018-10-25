public interface CallBack {
    String a = null;
    void callback(int param);
}
interface GetUp{
    void test(int params);
}

class Client implements CallBack, GetUp {

    @Override
    public void callback(int param) {

    }

    @Override
    public void test(int params) {

    }
}
