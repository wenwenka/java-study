package JavaBase.OOM;

public class StackEx {
    //模拟栈溢出
    //-Xms200M
    //-Xmx200M
    //-Xmn150M
    //-Xss256K
    //-XX:SurvivorRatio=8
    //-XX:MetaspaceSize=10M
    //-XX:MaxMetaspaceSize=10M
    //-XX:+PrintGC
    //-XX:+PrintGCDetails
    public static void main(String[] args) {
        recursion(1);
    }
    public static void recursion(int count) {
        System.out.println("times: "+ count++);
        recursion(count);
    }
}
