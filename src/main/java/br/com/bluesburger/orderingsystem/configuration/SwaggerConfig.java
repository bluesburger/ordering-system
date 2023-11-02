package br.com.bluesburger.orderingsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.bluesburger.orderingsystem.adapters.in"))
                .paths(PathSelectors.any()).build().apiInfo(metadata()).useDefaultResponseMessages(false)
                .globalResponses(HttpMethod.GET, responseMessage())
                .globalResponses(HttpMethod.POST, responseMessage())
                .globalResponses(HttpMethod.PUT, responseMessage())
                .globalResponses(HttpMethod.DELETE, responseMessage());
    }

    public static ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("API - Sistema de pedidos")
                .description("Projeto API Spring - Sistema de pedidos")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/bluesburger/ordering-system")
                .build();
    }


    private static List<Response> responseMessage() {
        return new ArrayList<>() {
            private static final long serialVersionUID = 1L;

            {
                add(new ResponseBuilder().code("200").description("Sucesso!").build());
                add(new ResponseBuilder().code("201").description("Criado!").build());
                add(new ResponseBuilder().code("400").description("Erro na requisição!").build());
                add(new ResponseBuilder().code("401").description("Não Autorizado!").build());
                add(new ResponseBuilder().code("403").description("Proibido!").build());
                add(new ResponseBuilder().code("404").description("Não Encontrado!").build());
                add(new ResponseBuilder().code("500").description("Erro!").build());
            }
        };
    }
}

