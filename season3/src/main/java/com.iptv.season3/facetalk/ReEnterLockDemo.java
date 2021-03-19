package com.iptv.season3.facetalk;

/**
 * @author liuqi
 * @description: 可重入锁:可重复可递归调用的锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁，这样的锁就叫做可重入锁。
 * <p>
 * 在一个synchronized修饰的方法或代码块的内部
 * 调用本类的其他synchronized修饰的方法或代码块时，是永远可以得到锁的
 * @create 2020-11-13 16:16
 */
public class ReEnterLockDemo {

    static Object olock = new Object();


    /**
     * 同步块
     */
    public static void m() {

        new Thread(() -> {
            synchronized (olock) {
                System.out.println(Thread.currentThread().getName() + "外");
                synchronized (olock) {
                    System.out.println(Thread.currentThread().getName() + "中");
                    synchronized (olock) {
                        System.out.println(Thread.currentThread().getName() + "内");
                    }
                }
            }
        }).start();
    }

    /**
     * 同步方法
     */
    public synchronized void m1(){
        System.out.println("----外层");
        m2();
    }

    private synchronized void m2() {
        System.out.println("----z层");
        m3();
    }
    private synchronized void m3() {
        System.out.println("----n层");
    }

    public static void main(String[] args) {
//        m();
        new ReEnterLockDemo().m1();
    }

}
