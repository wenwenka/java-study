package JavaBase.Clone.DeepClone;

import java.io.*;

public class DeepCopy {
    public static void main(String[]args) throws Exception{
        Teacher t = new Teacher();
        t.setName("Taylor");
        t.setAge(28);

        Student s = new Student();
        s.setAge(20);
        s.setName("Join");
        s.setTeacher(t);

        Student s2 = (Student)s.deepClone();
        //比较各个字段
    }
}
class Student implements Serializable {
    private String name;
    private int age;
    private Teacher teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Object deepClone() throws Exception {
        //序列化
        //在内存中创建一个字节数组缓冲区，所有发送到输出流的数据保存在该字节数组中
        //默认创建一个大小为32的缓冲区
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);//通过字节数组的方式进行传输
        oos.writeObject(this);//将当前student对象写入字节数组中

        //反序列化
        //在内存中创建一个字节数组缓冲区，从输入流读取的数据保存在该字节数组缓冲区
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());//接收字节数组作为参数进行创建
        ObjectInputStream ois = new ObjectInputStream(bis);

        return ois.readObject();//从字节数组中读取
    }
}

class Teacher implements Serializable {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

