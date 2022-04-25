package JavaBase.OOM;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class HeapEx {
    //-Xms200M
    //-Xmx200M
    //-Xmn100M 新生代大小
    //-Xss1M
    //-XX:SurvivorRatio=8
    //-XX:MetaspaceSize=10M
    //-XX:MaxMetaspaceSize=10M
    //-XX:+UseParNewGC
    // -XX:+PrintGC
    //-XX:+PrintGCDetails
    public static void main(String[] args) {
        Set<String> datas = new HashSet<>();
        while(true) {
            datas.add(UUID.randomUUID().toString());
        }
    }
}
