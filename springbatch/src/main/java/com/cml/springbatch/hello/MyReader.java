package com.cml.springbatch.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class MyReader implements ItemReader<Person> {
	private static final Logger log = LoggerFactory.getLogger(MyReader.class);

	private int count;

	@Override
	public Person read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		count++;
		if (count > 11) {
			return null;
		}
		log.info("=======reader 读入数据====>"+count);
		return new Person("first:" + count, "lastName:" + count);
	}

}
