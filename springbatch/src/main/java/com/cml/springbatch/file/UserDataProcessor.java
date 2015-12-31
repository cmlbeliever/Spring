package com.cml.springbatch.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.StringUtils;

public class UserDataProcessor implements ItemProcessor<User, User> {
	private static final Logger log = LoggerFactory.getLogger(UserDataProcessor.class);

	@Override
	public User process(User item) throws Exception {
		if (StringUtils.isEmpty(item.getPass())) {
			log.info("===UserDataProcessor====>数据不合法：" + item);
			return null;
		}
		return item;
	}

}
