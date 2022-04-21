package JavaBase.StringEx;

public class StringEx1 {

    public static String getStr() {
        return "ing";
    }

    public static void main(String[] args) {
        final String str1 = "str";
        final String str2 = getStr();//编译时str2未加载，因此仍无法知道确切值
        String c = "str" + "ing";// 常量池中的对象
        String d = str1 + str2; // 在堆上创建的新的对象
        System.out.println(c == d);// false
    }
}
