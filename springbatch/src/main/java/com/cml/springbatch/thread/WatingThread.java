package com.cml.springbatch.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public class WatingThread {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		System.out.println("dd");
		System.out.println("threadId:" + Thread.currentThread().getId());
		FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				System.out.println("call threadId:" + Thread.currentThread().getId());
				return 11;
			}
		});

		Executors.newCachedThreadPool().execute(task);
		System.out.println(task.get());
		System.out.println("end");
	}
}
