package Singleton;

public class LazyStaSingleton {
    //懒汉式静态内部类加载

    //Singleton类加载的时候，InnerSingleton类不会加载
    private static class InnerSingleton {
        private static LazyStaSingleton INSTANCE = new LazyStaSingleton();
    }

    private LazyStaSingleton(){
        System.out.println("对象创建");
    }

    public static LazyStaSingleton getInstance() {
        //当有线程调用getInstance方法时，方法栈帧中动态链接里发现InnerSingleton类还没被加载，所以就会去加载这个内部类，
        //然后内部类的静态成员变量INSTANCE只会被记载一次
        return InnerSingleton.INSTANCE;
    }

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(LazyStaSingleton.getInstance().hashCode());
        }).start();
        new Thread(() -> {
            System.out.println(LazyStaSingleton.getInstance().hashCode());
        }).start();
    }
}
