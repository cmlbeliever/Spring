package com.cml.springbatch.file;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("application-file.xml");
		JobLauncher launcher = context.getBean(JobLauncher.class);
		Job job = context.getBean("firstJob", Job.class);

		JobParameters params = new JobParameters();
		params.getParameters().put("inputFilePath", new JobParameter(
				"F:/gitrepository/Spring/springbatch/src/main/java/com/cml/springbatch/file/data.csv"));
		JobExecution result = launcher.run(job, params);
		System.out.println("====result===>" + result);
	}
}
