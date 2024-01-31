package com.ruben.catalogservice.Infra.Web.Config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
//@ConditionalOnMissingBean(HttpSecurity.class)
public class OpenAPISecurityConfig {

    @Value("${keycloak.auth-server-url}")
    String authServerUrl;
    @Value("${keycloak.realm}")
    String realm;

    private static final String OAUTH_SCHEME_NAME = "Client Authentication";
    private static final String OPENID_SCHEME_NAME = "User Authentication";

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components()
                            .addSecuritySchemes(OAUTH_SCHEME_NAME, createOAuthScheme())
                            .addSecuritySchemes(OPENID_SCHEME_NAME, createOpenIdScheme())
                )
                .addSecurityItem(new SecurityRequirement().addList(OAUTH_SCHEME_NAME))
                .addSecurityItem(new SecurityRequirement().addList(OPENID_SCHEME_NAME))
                .info(new Info().title("Catalog Service")
                        .description("Book catalog service")
                        .version("1.0"));
    }

    private SecurityScheme createOAuthScheme() {
        OAuthFlows flows = createOAuthFlows();
        return new SecurityScheme().type(SecurityScheme.Type.OAUTH2)
                .flows(flows);
    }

    private OAuthFlows createOAuthFlows() {
        OAuthFlow flow = createAuthorizationCodeFlow();
        return new OAuthFlows().implicit(flow);
    }

    private OAuthFlow createAuthorizationCodeFlow() {
        return new OAuthFlow()
                .authorizationUrl(authServerUrl + "/realms/" + realm + "/protocol/openid-connect/auth")
                .scopes(new Scopes()
                        .addString("book.read", "read book")
                        .addString("book.write", "write book")
                        .addString("author.read", "read author")
                        .addString("author.write", "write author")
                )
                .tokenUrl(authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token");
    }

    private SecurityScheme createOpenIdScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.OPENIDCONNECT)
                .flows(createOpenIdFlows());
    }

    private OAuthFlows createOpenIdFlows() {
        OAuthFlow authorizationCodeFlow = createAuthorizationCodeFlow();
//        OAuthFlow implicitFlow = createImplicitFlow();

        return new OAuthFlows()
                .password(authorizationCodeFlow);
                //.implicit(implicitFlow);
    }

//    private OAuthFlow createImplicitFlow() {
//        return new OAuthFlow()
//                .authorizationUrl(authServerUrl + "/realms/" + realm + "/protocol/openid-connect/auth")
//                .scopes(new Scopes()
//                        .addString("openid", "OpenID Connect")
//                        .addString("profile", "User profile information")
//                );
//    }





}