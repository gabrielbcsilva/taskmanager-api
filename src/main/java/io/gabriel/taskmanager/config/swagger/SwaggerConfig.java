package io.gabriel.taskmanager.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${spring.application.name}")
    private String appName;

    @Bean
    public OpenAPI bookOpenAPI() {
        return new OpenAPI()
            .info(new Info().title(appName)
                .description("Task APP by Gabriel")
                .version("v0.0.1"));
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
            .group("User")
            .pathsToMatch("/user", "/user/**")
            .build();
    }



    @Bean
    public GroupedOpenApi taskApi() {
        return GroupedOpenApi.builder()
            .group("Task")
            .pathsToMatch("/task", "/task/**")
            .build();
    }

    @Bean
    public GroupedOpenApi subtaskApi() {
        return GroupedOpenApi.builder()
            .group("TaskItems")
            .pathsToMatch("/subtask", "/subtask/**")
            .build();
    }

}
