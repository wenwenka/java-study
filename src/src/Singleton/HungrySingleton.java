package Singleton;

public class HungrySingleton {
    //饿汉式加载

    private static HungrySingleton instance = new HungrySingleton();
    private HungrySingleton(){}

    public static HungrySingleton getInstance() {
        return instance;
    }
}
