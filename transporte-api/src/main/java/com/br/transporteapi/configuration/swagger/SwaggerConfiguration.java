package com.br.transporteapi.configuration.swagger;

import com.br.transporteapi.configuration.jackson.JsonListSerializer;
import com.br.transporteapi.configuration.jackson.JsonPageSerializer;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import({springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class})
public class SwaggerConfiguration {

    @Bean
    public Docket transporteApiDocumentation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.br.transporteapi"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .directModelSubstitute(JsonPageSerializer.class, Page.class)
                .directModelSubstitute(JsonListSerializer.class, List.class)
                .apiInfo(this.apiInfoBuilder().build())
                .useDefaultResponseMessages(true)
                .securitySchemes(Lists.newArrayList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReferences())
                .forPaths(PathSelectors.any()).build();
    }

    private List<SecurityReference> securityReferences() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "acessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiInfoBuilder apiInfoBuilder() {
        return new ApiInfoBuilder()
                .title("API Transporte")
                .description("Um serviço com informações sobre o transporte público de São Paulo")
                .version("1.0")
                .contact(this.contact())
                .license("Licença MIT")
                .licenseUrl("https://pt.wikipedia.org/wiki/Licen%C3%A7a_MIT")
                .termsOfServiceUrl("Use para o programa de estágio Aiko");
    }

    private Contact contact() {
        return new Contact("Aiko Digital", "https://aiko.digital/contato/", "aiko@gmail.com");
    }


}
