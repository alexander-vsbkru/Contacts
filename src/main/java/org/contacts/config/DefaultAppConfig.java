package org.contacts.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("org.contacts")
@Configuration
@PropertySource("classpath:application.properties")
public class DefaultAppConfig {
 }
