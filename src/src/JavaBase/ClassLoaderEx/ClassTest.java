package JavaBase.ClassLoaderEx;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class ClassTest {

    @Test
    public void testEncode() {
        String classFile =  "D:/data/proj/gitDemo/Car.class";
        FileInputStream fis = null;
        try{
            fis = new FileInputStream(classFile);
            int len = fis.available();
            byte[] data = new byte[len];
            fis.read(data);
            fis.close();

            data = DESInstance.enCode("1234567890qwertyuiopasdf".getBytes(),data);
            String outFile = "D:/data/proj/gitDemo/com/Car.class";
            FileOutputStream fos = new FileOutputStream(outFile);
            fos.write(data);
            fos.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClassLoader() throws Exception{
        MyClassLoader myClassLoader = new MyClassLoader("D:/data/proj/gitDemo/com");
        Class clazz = myClassLoader.loadClass("Car");
        Object o = clazz.newInstance();
        Method print = clazz.getDeclaredMethod("print",null);
        print.invoke(o,null);
    }
}
