package xie.hailiang.resourceserver.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

/**
 * 
 * @author Hailiang XIE
 * To enable the Authorization button on the swagger ui page and use the bearer token authentication method.
 *
 */
@Configuration
@SecurityScheme(
        name = "BearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
@OpenAPIDefinition(
        info = @Info(title = "Customer API", version = "v3"),
        security = @SecurityRequirement(name = "BearerAuth")
)
public class DocsConfig {

}
