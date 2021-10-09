package cn.wiki.thread;

public class TestStop implements Runnable {
  private Boolean flag = true;

  @Override
  public void run() {
    int i = 0;
    while (flag) {
      System.out.println("run ..... thread " + i++);
    }
  }

  void stop() {
    this.flag = false;
  }

  public static void main(String[] args) {
    TestStop stop = new TestStop();
    new Thread(stop).start();

    for (int i = 0; i < 1000; i++) {
      if (i == 900) {
        stop.stop();
        System.out.println("线程该停止了：" + i);
      }
    }
  }
}
