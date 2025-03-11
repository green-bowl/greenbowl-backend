package com.greenbowl.greenbowlserver.fridge.adapter.config;

import com.greenbowl.greenbowlserver.common.application.aop.LoggingAspect;
import com.greenbowl.greenbowlserver.common.domain.excpeption.GlobalExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({LoggingAspect.class, GlobalExceptionHandler.class})
public class CommonConfig {
}
