package com.exposit.web;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("webConfigurationStarter")
@ComponentScan(basePackages = {"com.exposit.web"})
@AutoConfigureBefore({com.exposit.core.StarterConfig.class})
public class StarterConfiguration {}
