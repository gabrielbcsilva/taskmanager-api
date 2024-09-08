package io.gabriel.taskmanager.config.swagger;

import io.gabriel.taskmanager.infra.CustomErrorResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
    @ApiResponse(
            responseCode = "400",
            description = "Requisição mal sucedida!",
            content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    ),
    @ApiResponse(
        responseCode = "401",
        description = "Usuário não autorizado!",
        content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    ),
    @ApiResponse(
            responseCode = "403",
            description = "Acesso negado!",
            content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    ),
    @ApiResponse(
        responseCode = "404",
        description = "Recurso não encontrado!",
        content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    ),
    @ApiResponse(
            responseCode = "409",
            description = "Recurso já existe!",
            content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    ),
    @ApiResponse(
        responseCode = "500",
        description = "Erro interno no servidor!",
        content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    ),
    @ApiResponse(
            responseCode = "501",
            description = "Ação não realizada!",
            content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    ),
    @ApiResponse(
            responseCode = "504",
            description = "Tempo excedido!",
            content = @Content(schema = @Schema(implementation = CustomErrorResponse.class))
    )
})
public @interface DefaultApiResponse {
}
