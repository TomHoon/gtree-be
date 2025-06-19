package org._4.gtree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @CreatedDate,@LastModifiedDate
public class GtreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GtreeApplication.class, args);
	}

}
