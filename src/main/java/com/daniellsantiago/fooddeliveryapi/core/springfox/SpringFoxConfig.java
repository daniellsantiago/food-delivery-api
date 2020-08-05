package com.daniellsantiago.fooddeliveryapi.core.springfox;

import com.daniellsantiago.fooddeliveryapi.api.dto.CuisineDTO;
import com.daniellsantiago.fooddeliveryapi.api.dto.OrderBasicDTO;
import com.daniellsantiago.fooddeliveryapi.api.exceptionhandler.ExceptionDetails;
import com.daniellsantiago.fooddeliveryapi.api.openapi.model.CuisinesModelOpenApi;
import com.daniellsantiago.fooddeliveryapi.api.openapi.model.OrderBasicModelOpenApi;
import com.daniellsantiago.fooddeliveryapi.api.openapi.model.PageableModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {
        var typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                            .apis(RequestHandlerSelectors.basePackage("com.daniellsantiago.fooddeliveryapi.api"))
                            .paths(PathSelectors.any())
                            .build()
                        .useDefaultResponseMessages(false)
                        .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
                        .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
                        .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
                        .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
                        .additionalModels(typeResolver.resolve(ExceptionDetails.class))
                        .ignoredParameterTypes(ServletWebRequest.class)
                        .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
                        .alternateTypeRules(AlternateTypeRules.newRule(
                                        typeResolver.resolve(Page.class, CuisineDTO.class),
                                        CuisinesModelOpenApi.class))
                        .alternateTypeRules(AlternateTypeRules.newRule(
                                        typeResolver.resolve(Page.class, OrderBasicDTO.class),
                                        OrderBasicModelOpenApi.class))
                        .apiInfo(apiInfo())
                        .tags(new Tag("Cities", "Manage Cities"),
                              new Tag("Roles", "Manage User Roles"),
                              new Tag("Payment Methods", "Manage Payment Methods"),
                              new Tag("Orders", "Manage Orders"),
                              new Tag("Cuisines", "Manage Cuisines"));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Food Delivery API")
                .description("An Api to help restaurants to delivery food to customers")
                .version("1")
                .contact(new Contact("Daniel", "https://github.com/daniellsantiago",
                                     "danielsantiago111@hotmail.com"))
                .build();
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Internal Server Error")
                        .responseModel(new ModelRef("ExceptionDetails"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Resource has no representation that could be accepted by the consumer")
                        .build()
        );
    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Invalid request (client error)")
                        .responseModel(new ModelRef("ExceptionDetails"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Internal Server Error")
                        .responseModel(new ModelRef("ExceptionDetails"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.NOT_ACCEPTABLE.value())
                        .message("Resource has no representation that could be accepted by the consumer")
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                        .message("Request declined because the body is in an unsupported format")
                        .responseModel(new ModelRef("ExceptionDetails"))
                        .build()
        );
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
                new ResponseMessageBuilder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("Invalid request (client error)")
                        .responseModel(new ModelRef("ExceptionDetails"))
                        .build(),
                new ResponseMessageBuilder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message("Internal Server Error")
                        .responseModel(new ModelRef("ExceptionDetails"))
                        .build()
        );
    }
}