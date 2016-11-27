package org.iStat.api.iApp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// Adicionar as tags
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "org.iStat.api" })
public class ConfigiStat {

}
