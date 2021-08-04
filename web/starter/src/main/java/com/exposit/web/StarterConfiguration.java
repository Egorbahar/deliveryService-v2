package com.exposit.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration("webConfigurationStarter")
@ComponentScan(basePackages = {"com.exposit.web"})
public class StarterConfiguration {}
