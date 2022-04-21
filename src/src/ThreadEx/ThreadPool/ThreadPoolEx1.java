package ThreadEx.ThreadPool;

import java.util.concurrent.*;
import java.util.*;

public class ThreadPoolEx1 {

    int corePoolSize = 6;
    int maximumPoolSize = 7;
    int keepAliveTime = 1000;
    TimeUnit timeUnit = TimeUnit.MILLISECONDS;


    ThreadFactory myThreadFactory;
    ThreadPoolExecutor threadPoolExecutor;
    ThreadPoolExecutor fiexdThreadPool;
    BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<Runnable>(20);


    public ThreadPoolEx1() {
        myThreadFactory = new MyThreadFactory("executeTask");
        threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,//corePoolSize保持在池中的线程数，即使它们是空闲的
                maximumPoolSize,//maximumPoolSize池中允许的最大线程数
                keepAliveTime,//keepAliveTime当线程数量大于内核时，这是多余的空闲线程在终止新任务之前等待的最大时间。
                timeUnit,//单位是{@code keepAliveTime}参数的时间单位
                blockingQueue,//用于在任务执行之前保存它们的队列。这个队列将只包含由{@code execute}方法提交的{@code Runnable}任务。
                myThreadFactory,//执行程序创建新线程时使用的工厂
                new ThreadPoolExecutor.CallerRunsPolicy());//当执行被阻塞时使用的处理程序，因为达到了线程界限和队列容量

        fiexdThreadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(corePoolSize);//根据工具类直接创建

    }

    public Map<String,Integer> task(Map<String,Integer> data,CountDownLatch cdl) throws ExecutionException ,InterruptedException {
        FutureTask<Map<String,Integer>> futureTask = new FutureTask<>(new Callable<Map<String,Integer>>(){
            @Override
            public Map<String, Integer> call() throws Exception {
                Map<String,Integer> ans = new HashMap<>();
                for(String key : data.keySet()) {
                    ans.put(key,data.get(key));
                }
                cdl.countDown();
                return ans;
            }
        });
        threadPoolExecutor.submit(futureTask);
        return futureTask.get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolEx1 t1 = new ThreadPoolEx1();
        CountDownLatch cdl = new CountDownLatch(3);
        Map<String,Integer> map1 = new HashMap<>();
        map1.put("1",1);map1.put("2",2);
        Map<String,Integer> map2 = new HashMap<>();
        map2.put("1",1);map2.put("2",2);
        Map<String,Integer> map3 = new HashMap<>();
        map3.put("1",1);map3.put("2",2);

        Map<String,Integer> ans1 = t1.task(map1,cdl);
        Map<String,Integer> ans2 = t1.task(map2,cdl);
        Map<String,Integer> ans3 = t1.task(map3,cdl);
        cdl.await();
        System.out.println(ans3.get("1"));



    }
}
