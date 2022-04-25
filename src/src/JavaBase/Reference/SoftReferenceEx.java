package JavaBase.Reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

public class SoftReferenceEx {

    public static void main(String[] args) {
        //强引用
        String strongReference = new String("abc");
        //软引用
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        String str = new String("abc");
        SoftReference<String> softReference = new SoftReference<String>(str,referenceQueue);

        str = null;
        //notify GC
        System.gc();
        System.out.println(softReference.get()); //abc
        //soft reference gc only happens when the memory of jvm is not enough
        Reference<? extends String> reference = referenceQueue.poll();
        System.out.println(reference); //null

    }
}
