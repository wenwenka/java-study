package JavaBase.Clone.ShallowCopy;

public class ShallowCopy {
    //浅拷贝
    public static void main(String[] args) {
        Person person1 = new Person(new Address("武汉"));
        Person person1Copy = person1.clone();
        //生成对象不同
        System.out.println(person1.hashCode());
        System.out.println(person1Copy.hashCode());

        // 引用类型直接指向原来对象，因此为true
        System.out.println(person1.getAddress() == person1Copy.getAddress());
    }
}
