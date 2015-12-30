package com.cml.springbatch.xml;

import java.util.Map;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class FirstTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Map<String, Object> jobParams = chunkContext.getStepContext().getJobParameters();
		System.out.println("==========================================tasklet========>"+jobParams);
		return RepeatStatus.FINISHED;
	}

}
