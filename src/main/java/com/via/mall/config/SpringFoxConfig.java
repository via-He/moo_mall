package com.via.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Qingqing.He
 * @date 2020/12/31 9:57
 */
@Configuration
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
            .title("momo生鲜")
            .description("")
            .termsOfServiceUrl("")
            .build();
    }

}
