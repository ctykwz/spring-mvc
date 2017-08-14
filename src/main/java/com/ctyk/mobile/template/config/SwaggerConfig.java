package com.ctyk.mobile.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * Restful API 访问路径:
 * http://IP:port/{context-path}/swagger-ui.html
 * eg:http://localhost:8080/jd-config-web/swagger-ui.html
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket createRestApi(PropertiesConfig propertiesConfig) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(propertiesConfig))
                .select()
                .apis(RequestHandlerSelectors.basePackage(propertiesConfig.getSwaggerScanPackage()))
                .paths(propertiesConfig.isSwaggerEnabled() ? PathSelectors.any() : PathSelectors.none())
                .build();
    }

    private ApiInfo apiInfo(PropertiesConfig propertiesConfig) {
        return new ApiInfoBuilder()
                .title(propertiesConfig.getSwaggerTitle())
                .termsOfServiceUrl(propertiesConfig.getSwaggerServiceUrl())
                .contact(new Contact(propertiesConfig.getSwaggerContactName(),
                        propertiesConfig.getSwaggerContactUrl(),
                        propertiesConfig.getSwaggerContactEmail()))
                .version(propertiesConfig.getSwaggerVersion())
                .build();
    }
}