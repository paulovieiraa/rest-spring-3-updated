package br.com.pvprojects.restspring3.core.retry;

import br.com.pvprojects.restspring3.domain.exception.MissingAttributesException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;

@EnableRetry
@Configuration
@Slf4j
@RequiredArgsConstructor
public class RetryConfiguration {

    private final MicroserviceSpringRetryProperties microserviceSpringRetryProperties;

    @Bean
    public RetryTemplate DocumentImageRetryTemplate() {
        return RetryTemplate.builder()
                .maxAttempts(microserviceSpringRetryProperties.getMaxAttempts())
                .fixedBackoff(microserviceSpringRetryProperties.getDelayInMs())
                .retryOn(MissingAttributesException.class)
                .build();
    }
}
