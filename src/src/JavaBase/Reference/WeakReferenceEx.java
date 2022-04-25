package JavaBase.Reference;

import java.lang.ref.WeakReference;

public class WeakReferenceEx {

    public static void main(String[] args) {
        //strong reference
        String str = new String("abc");
        //weak reference
        WeakReference<String> weakReference = new WeakReference<>(str);
        //remove the strong reference of the object
        str = null;
        //notify gc
        System.gc();
    }
}
