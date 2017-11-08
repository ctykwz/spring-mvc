package com.ctyk.mobile.template.exception;

import org.junit.Test;

/**
 * @author wei.yang on 2017/11/8
 */
public class ExceptionCatchTest {

    @Test
    public void catchTest() {
        try {
            Thread thread = new Thread(new RunTest());
            Thread.UncaughtExceptionHandler handler = (t, e) -> System.out.println("msg:\t" + t.getName() + "\t" + e.getMessage());
            thread.setUncaughtExceptionHandler(handler);
            thread.start();
        } catch (Exception e) {
            System.out.println("msg:\t" + e.getMessage());
        }
    }

    class RunTest implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println(1 / 0);
            } catch (Exception e) {
                System.out.println("msg:\t" + e.getMessage());
            }
            System.out.println(1 / 0);
        }
    }
}
