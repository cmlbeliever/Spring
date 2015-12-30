package com.cml.springbatch.hello;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

public class JdbcWriter implements ItemWriter<Person> {

	private static final Logger log = LoggerFactory.getLogger(JdbcWriter.class);

	@Override
	public void write(List<? extends Person> items) throws Exception {
		log.info("=====writer 写入数据===>" + items);
	}

}
