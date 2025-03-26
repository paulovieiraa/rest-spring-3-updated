package br.com.pvprojects.restspring3.core.retry;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "microservices.document-retry")
public class MicroserviceSpringRetryProperties {

    private Integer maxAttempts;
    private Long delayInMs;
}
