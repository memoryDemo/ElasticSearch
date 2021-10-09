package cn.wiki.callable;

import cn.wiki.thread.TestThread02;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class TestCallable01 implements Callable<Boolean> {
  private String url; // 网络图片地址
  private String name; // 保存的文件名

  public TestCallable01(String url, String name) {
    this.url = url;
    this.name = name;
  }

  @Override
  public Boolean call() {
    WebDownload webDownload = new WebDownload();
    webDownload.downloadr(url, name);
    System.out.println("下载了文件名为：" + name);
    return true;
  }

  public static void main(String[] args) {

    TestCallable01 t1 =
        new TestCallable01(
            "https://tse1-mm.cn.bing.net/th/id/R-C.c6c03edea530e9caa677c9d17f193a4d?rik=MBgpsjumbTD5eQ&riu=http%3a%2f%2fwww.desktx.com%2fd%2ffile%2fwallpaper%2fscenery%2f20170209%2fca186d97701674b996264b2d352894a7.jpg&ehk=HunG%2fPF7pUbpcS34cWpNvlS%2faoDPbcaTYL6LFFPQIIM%3d&risl=&pid=ImgRaw&r=0",
            "1.jpg");
    TestCallable01 t2 =
        new TestCallable01(
            "https://tse1-mm.cn.bing.net/th/id/R-C.33d0949ed1d018fbd23b9264027e58e0?rik=XYcoHb%2foHS2aFw&riu=http%3a%2f%2fwww.desktx.com%2fd%2ffile%2fwallpaper%2fscenery%2f20170120%2fdf204ebd7a4829933463e2989deb54c6.jpg&ehk=oIKu9Q7Dsgg8tGWfXQ%2fdcQBt4l%2f92jwsatz95dhd0oE%3d&risl=&pid=ImgRaw&r=0",
            "2.jpg");
    TestCallable01 t3 =
        new TestCallable01(
            "https://tse1-mm.cn.bing.net/th/id/R-C.8c372fd892b3bd371eb3a1df8bd7fc88?rik=4KxekfOQD28FKA&riu=http%3a%2f%2fwww.desktx.com%2fd%2ffile%2fwallpaper%2fscenery%2f20170303%2fdfe53a7300794009a029131a062836d5.jpg&ehk=6ayU5y%2fwtGnzhu7g%2bJimm2REgEbHGczl9Mkbg3I1%2b5I%3d&risl=&pid=ImgRaw&r=0",
            "3.jpg");

    // 创建执行服务
    ExecutorService executorService = Executors.newFixedThreadPool(1);
    // 提交执行
    Future<Boolean> future1 = executorService.submit(t1);
    Future<Boolean> future2 = executorService.submit(t2);
    Future<Boolean> future3 = executorService.submit(t3);
    // 获取结果
      try {
          Boolean a1 = future1.get();
          Boolean a2 = future2.get();
          Boolean a3 = future3.get();
      } catch (InterruptedException e) {
          e.printStackTrace();
      } catch (ExecutionException e) {
          e.printStackTrace();
      }

      // 关闭服务
    executorService.shutdown();
  }

  // 下载器
  class WebDownload {
    public void downloadr(String url, String name) {
      try {
        FileUtils.copyURLToFile(new URL(url), new File(name));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
