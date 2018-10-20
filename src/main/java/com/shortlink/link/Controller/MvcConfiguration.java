package com.shortlink.link.Controller;

/**
 * <b>Mvc Configuration</b>
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@EnableWebMvc
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    /**
     * <b>Mvc configuration enable</b>
     * @param configurer Configurer object
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * <b>View resolver</b>
     * @return set prefix and suffix
     */
    @Bean
    public InternalResourceViewResolver viewResolver () {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }
}
