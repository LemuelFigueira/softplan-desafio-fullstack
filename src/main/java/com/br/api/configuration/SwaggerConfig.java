package com.br.api.configuration;

import com.br.api.constants.SecurityType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Process API", version = "1.0", description = "API para o controle de processos"))
@SecurityScheme(name = SecurityType.BEARER, type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER, scheme = SecurityType.BEARER)
public class SwaggerConfig {
}