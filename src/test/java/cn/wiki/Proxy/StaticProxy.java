package cn.wiki.Proxy;

public class StaticProxy {}

interface Marry {
  // 人间四大喜事
  void HappyMarry();
}

class You implements Marry {
  @Override
  public void HappyMarry() {
    System.out.println("要结婚了，超开心");
  }
}

class WeddingCompany implements Marry {
    private Marry target;

    public WeddingCompany(Marry target) {
        this.target = target;

    }

    public void HappyMarry(){

    }
}


