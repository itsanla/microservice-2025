package com.anla.cqrs;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(excludeAutoConfiguration = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ActiveProfiles("test")
class CqrsApplicationTests {

	@Test
	void contextLoads() {
	}

}