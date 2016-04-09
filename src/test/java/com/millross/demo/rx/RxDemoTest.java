package com.millross.demo.rx;

import org.junit.Test;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class RxDemoTest {

    volatile boolean x = false;

    @Test
    public void demoRx() throws Exception {

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        Observable<Boolean> originalObs = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.from(executorService))
                .map(x -> {
                    try {
                        Thread.sleep(1100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return (x % 3 == 0);
                });
        originalObs.subscribe(v -> {
            x = v;
        });
        while(true) {
            TimeUnit.MILLISECONDS.sleep(500);
            System.out.println(x);
        }

    }
}