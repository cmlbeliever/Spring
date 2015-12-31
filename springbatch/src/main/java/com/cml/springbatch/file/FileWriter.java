package com.cml.springbatch.file;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class FileWriter implements ItemWriter<User> {

	@Override
	public void write(List<? extends User> items) throws Exception {
		System.out.println("writer==========>"+items);
	}

}
