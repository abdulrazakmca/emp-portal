package com.abdul.empportal.config;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	

    private final OpenApiConfigProperties properties;

    public OpenApiConfig(OpenApiConfigProperties properties)
    {
        this.properties = properties;
    }

    @Bean
    public OpenAPI customOpenAPI()
    {
        final String apiTitle = String.format("%s API",
                StringUtils.capitalize(properties.getName()));
        final OpenAPI openAPI = new OpenAPI();
        addSecuritySchema(openAPI);
        addServerUrl(openAPI);
        return openAPI.info(
                new Info().title(apiTitle).version(properties.getVersion()));

    }

    private void addServerUrl(OpenAPI openAPI)
    {
        if (properties.getServerUrl() != null
                && !properties.getServerUrl().isBlank())
            openAPI.addServersItem(new Server().url(properties.getServerUrl()));
    }
    
    private void addSecuritySchema(OpenAPI openAPI)
    {
        final String securitySchemeName = properties.getSecuritySchemeName();
        if (!"noauth".equalsIgnoreCase(securitySchemeName))
            openAPI.addSecurityItem(
                    new SecurityRequirement().addList(securitySchemeName))
                    .components(new Components().addSecuritySchemes(
                            securitySchemeName,
                            new SecurityScheme().name(securitySchemeName)
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme(properties.getScheme())));
    }

    @Bean
    public OperationCustomizer customize()
    {
        return (operation, handlerMethod) -> operation
                .addParametersItem(
                       new Parameter()
                        .in("header")
                        .required(false)
                        .description("Tenant Id")
                        .name("X-TenantID")
                        .example("default"));
    }
    
    @Configuration
    @ConfigurationProperties(prefix = "openapi.configuration")
    public static class OpenApiConfigProperties
    {
        private String name               = null;

        private String version            = null;

        private String securitySchemeName = null;

        private String scheme             = null;

        private String serverUrl          = null;

        /**
         * @return the name
         */
        public String getName()
        {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name)
        {
            this.name = name;
        }

        /**
         * @return the version
         */
        public String getVersion()
        {
            return version;
        }

        /**
         * @param version the version to set
         */
        public void setVersion(String version)
        {
            this.version = version;
        }

        /**
         * @return the securitySchemeName
         */
        public String getSecuritySchemeName()
        {
            return securitySchemeName;
        }

        /**
         * @param securitySchemeName the securitySchemeName to set
         */
        public void setSecuritySchemeName(String securitySchemeName)
        {
            this.securitySchemeName = securitySchemeName;
        }

        /**
         * @return the scheme
         */
        public String getScheme()
        {
            return scheme;
        }

        /**
         * @param scheme the scheme to set
         */
        public void setScheme(String scheme)
        {
            this.scheme = scheme;
        }

        /**
         * @return the serverUrl
         */
        public String getServerUrl()
        {
            return serverUrl;
        }

        /**
         * @param serverUrl the serverUrl to set
         */
        public void setServerUrl(String serverUrl)
        {
            this.serverUrl = serverUrl;
        }
        
        

    }



}
