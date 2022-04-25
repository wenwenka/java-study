package JavaBase.ClassLoaderEx;

public class Car {
    public Car() {
        System.out.println("Car" + getClass().getClassLoader());
        System.out.println("Car Parent:" + getClass().getClassLoader().getParent());
    }

    public String print() {
        System.out.println("Car:print()");
        return "carPrint";
    }
}
