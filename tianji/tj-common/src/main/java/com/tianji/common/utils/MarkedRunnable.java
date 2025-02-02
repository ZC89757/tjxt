package com.tianji.common.utils;

import org.slf4j.MDC;

import java.util.Map;

/**
 * 在日志中附加线程的信息
 */
public class MarkedRunnable implements Runnable{
    //线程
    private Runnable runnable;
    //上下文
    private Map<String, String> context;

    //接受一个线程
    public MarkedRunnable(Runnable runnable) {
        this.runnable = runnable;
        this.context = MDC.getCopyOfContextMap();
    }

    @Override
    public void run() {
        if(context == null){
            MDC.clear();
        }else {
            MDC.setContextMap(context);
        }
        try {
            //调用传入线程的run方法
            runnable.run();
        }finally {
            MDC.clear();
        }
    }
}
