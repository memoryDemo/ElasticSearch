package cn.wiki.thread;

// 创建线程方式2：
// 实现runnable接口，重写run方法，执行线程需要丢入runnable接口实现类，
// 调用start方法
public class TestThread03 implements Runnable {

  @Override
  public void run() {
    for (int i = 0; i < 200; i++) {
      //
      System.out.println("我在看代码---" + i);
    }
  }

  public static void main(String[] args) {
    // 创建runnable接口的实现类对象
    TestThread03 thread03 = new TestThread03();

    // 创建线程对象，通过线程对象来开启我们的线程，代理
    //    Thread thread = new Thread(thread03);
    //
    //    thread.start();
    new Thread(thread03).start();

    for (int i = 0; i < 1000; i++) {
      //
      System.out.println("我在学习多线程--" + i);
    }
  }
}
