package com.greenbowl.greenbowlserver.recommendation.configuration;

import com.greenbowl.greenbowlserver.common.application.aop.LoggingAspect;
import com.greenbowl.greenbowlserver.common.domain.exception.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({LoggingAspect.class, GlobalExceptionHandler.class})
public class CommonConfig {
}
