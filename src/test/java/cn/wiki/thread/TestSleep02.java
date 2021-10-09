package cn.wiki.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

// 模拟倒计时
public class TestSleep02 {

  private static boolean flag = true;

  public static void main(String[] args) {
    TestSleep02 sleep02 = new TestSleep02();
    Date startTime = new Date(System.currentTimeMillis());
    while (flag) {
      sleep02.tenDown();
      System.out.println(new SimpleDateFormat("HH:mm:ss").format(startTime));
      startTime = new Date(System.currentTimeMillis());
    }
  }

  public void tenDown() {
    int num = 10;
    while (flag) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(num--);
      if (num < 0) {
        stop();
        break;
      }
    }
  }

  private void stop() {
    flag = false;
  }
}
