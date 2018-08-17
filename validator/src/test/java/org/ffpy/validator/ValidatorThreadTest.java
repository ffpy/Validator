package org.ffpy.validator;

import org.ffpy.validator.exception.ValidateFailException;
import org.junit.Test;

/**
 * 多线程测试
 */
public class ValidatorThreadTest {

    @Test
    public void test() {
        final int THREAD_NUM = 100;
        Thread[] threads = new Thread[THREAD_NUM];
        for (int i = 0; i < THREAD_NUM; i++) {
            threads[i] = new Thread(() -> {
                try {
                    for (int j = 0; j < 10000; j++) {
                        for (int k = 0; k < 10; k++) {
                            Class cls = Class.forName("org.ffpy.validator.bean.Bean" + k);
                            Object obj = cls.newInstance();
                            Validator.validateThrow(obj);
                        }
                    }
                } catch (ValidateFailException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}