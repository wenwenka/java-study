## 自定义类加载器
### 各个类的说明
- MyClassLoader类
    - 定义自定义的类加载器，重写了findClass的方法，
      使得自定义的类加载器能够加载指定路径的类
    - 通过loadByte()方法，
      将需要加载的类进行解密操作（为了防止类被反编译）
- DESInstance类
    - 定义了一个具有加密和解密方法的类

因为没有重写loadClass，所以没有破坏双亲委派机制

但是在写的过程中，出现了一个问题，
刚开始是将JavaBase.ClassLoaderEx包下的Car.java，
通过javac的方式直接编译成Car.class，然后放在指定路径下，
再通过ClassTest类中的testEncode方法，加密输出到指定路径的子路径。
后来发现一直会报NoClassDefFoundError JavaBase.ClassLoaderEx/Car (wrong name: Car)
的异常，刚开始看到这个问题确实有点百思不得其解，
经过排查 + 查阅资料，发现，问题就出在将包下的文件直接编译，
子路径中的Car.class，其实头文件里还包含了包名，但是加载的类名并没有包名，
所以就会报错，解决方案就是将.java文件里的包名信息去掉，再进行编译，
此时的加密和解密就不会出问题了