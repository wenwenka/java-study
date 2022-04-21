package JavaBase.Generic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericMethod {
    public static <T> T get(T obj) {
        return obj;
    }

    //泛型方法中可以使用可变参数列表
    public static <T> List<T> makeList(T... args) {
        List<T> ans = new ArrayList<>();
        Collections.addAll(ans,args);
        return ans;
    }

    public static void main(String[] args) {
        String a = "abc";
        String b = get(a);

        System.out.println(makeList(a));
        String[] ss = new String[]{"A","B","C"};
        System.out.println(makeList(ss));

    }

}
