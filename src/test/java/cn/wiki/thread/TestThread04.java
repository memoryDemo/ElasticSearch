package cn.wiki.thread;


//发现问题：多个线程操作同一个资源的情况下，线程不安全，资源紊乱
public class TestThread04 implements Runnable {

  private int ticket = 10;

  @Override
  public void run() {
    while (true) {
      if (ticket <= 0) {
        break;
      }
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "--->拿到了第" + ticket-- + "票");
    }
  }

  public static void main(String[] args) {
      TestThread04 thread04 = new TestThread04();
      new Thread(thread04,"小明").start();
      new Thread(thread04,"老师").start();
      new Thread(thread04,"黄牛党").start();
  }
}
