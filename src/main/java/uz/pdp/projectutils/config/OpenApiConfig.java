package uz.pdp.projectutils.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
       info = @Info(
           title = "Это моя программа",
           description = "задачи решаются в этой программе",
           termsOfService = "/termsOfApp",
           contact = @Contact(
                   name = "G40",
                   url = "https://pdp.uz",
                   email = "mansurboqinazarov@gmail.com"
           )
       ),
       servers = {
               @Server(url = "http://localhost:8080",description = "For Local Mode 1"),
               @Server(url = "https://10.10.1.12.1:8080",description = "For Local Mode 2")
       }
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        description = "You have to add JWT Token",
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
