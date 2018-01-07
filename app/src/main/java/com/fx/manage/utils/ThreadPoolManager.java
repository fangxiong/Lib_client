package com.fx.manage.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by fangxiong on 2017/12/12.
 */

public class ThreadPoolManager {
        private static ThreadPoolManager tpm;
        private ExecutorService service;

        private ThreadPoolManager() {
            // 返回java虚拟机可用处理器的数量
            int num = Runtime.getRuntime().availableProcessors();
            service = Executors.newFixedThreadPool(num * 2);
        }

        public static ThreadPoolManager getInstance() {
            if (null == tpm) {
                tpm = new ThreadPoolManager();
            }
            return tpm;
        }

        public void addTask(Runnable task) {
            service.submit(task);
        }
    }

