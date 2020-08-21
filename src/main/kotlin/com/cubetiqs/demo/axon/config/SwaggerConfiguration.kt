package com.cubetiqs.demo.axon.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.Collections
import java.util.concurrent.CompletableFuture

@Configuration
@EnableSwagger2
class SwaggerConfiguration {
    @Bean
    fun apiDocket(): Docket {
        val ignoreClasses = arrayListOf(CompletableFuture::class.java).toTypedArray()
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(
                RequestHandlerSelectors
                    .basePackage("com.cubetiqs.demo.axon")
            )
            .paths(PathSelectors.any())
            .build()
            .ignoredParameterTypes(*ignoreClasses)
            .apiInfo(apiInfo)
    }

    private val apiInfo: ApiInfo
        get() = ApiInfo(
            "Spring Boot + Axon Demo",
            "Axon Project Demo (Event Sourcing, DDD and CQRS)",
            "0.0.1-SNAPSHOT",
            "Terms of Service",
            Contact("Sambo Chea", "https://cs.cubetiqs.com", "sombochea@cubetiqs.com"),
            "MIT",
            "",
            Collections.emptyList()
        )
}