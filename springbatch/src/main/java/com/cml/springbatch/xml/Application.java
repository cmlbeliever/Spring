package com.cml.springbatch.xml;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
		JobLauncher launcher = context.getBean(JobLauncher.class);
		Job job = context.getBean("firstJob", Job.class);
		JobExecution result = launcher.run(job, new JobParameters());
		System.out.println("====result===>" + result);
	}
}
