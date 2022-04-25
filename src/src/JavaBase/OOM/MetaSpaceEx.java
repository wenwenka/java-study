package JavaBase.OOM;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MetaSpaceEx {
    //通过CGLib来不断创建类来模拟塞满MetaSpace
    //JVM Param :
    //-Xms200M
    //-Xmx200M
    //-Xmn150M
    //-XX:SurvivorRatio=8
    //-XX:MetaspaceSize=10M
    //-XX:MaxMetaspaceSize=10M
    //-XX:+UseConcMarkSweepGC
    //-XX:+TraceClassLoading 类加载追踪
    //-XX:+TraceClassUnloading 类卸载追踪
    //-XX:+PrintGC
    //-XX:+PrintGCDetails
    //-XX:+PrintGCDateStamps
    //-Xloggc:./gc.log
    //-XX:+IgnoreUnrecognizedVMOptions 不能随意加，有些参数可能就不执行
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(IService.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o,objects);
                }
            });
            enhancer.create();
        }
    }

    static class IService {}
}
