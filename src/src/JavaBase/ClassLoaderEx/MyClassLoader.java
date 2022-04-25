package JavaBase.ClassLoaderEx;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class MyClassLoader extends ClassLoader{
    private String classPath;

    public  MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] data = loadByte(name);
            return defineClass(name,data,0,data.length);
        }catch(Exception e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

    private byte[] loadByte(String name) throws Exception {
        name = name.replaceAll("\\.","/");
        FileInputStream fis = new FileInputStream(classPath + "/" + name + ".class");
        int len = fis.available();
        byte[] data = new byte[len];
        //insert the data from .class into the byte array
        fis.read(data);
        fis.close();

        //byte stream decode
        data = DESInstance.deCode("1234567890qwertyuiopasdf".getBytes(),data);

        return data;
    }
}

class  DESInstance {
    private static  String ALGORITHM = "DESede";

    /**
     * encode
     * @param key
     * @param src
     * @return
     */
    public static byte[] enCode(byte[] key,byte[] src) {

        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key,ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE,deskey);
            value = cipher.doFinal(src);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public static byte[] deCode(byte[]key, byte[] src) {
        byte[] value = null;
        SecretKey deskey = new SecretKeySpec(key,ALGORITHM);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE,deskey);
            value = cipher.doFinal(src);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }
}
