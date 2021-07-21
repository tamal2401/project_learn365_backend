package org.learn365.subscription.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Learn365DatasourceConfiguration {

	@Bean
	@ConfigurationProperties("learn365.datasource")
	public DatasourceProperties datasourceproperties() {
		return new DatasourceProperties();
	}

}
