/**
 * 
 */
package dev.atanu.ecom.cart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Atanu Bhowmick
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Value("${spring.application.name}")
	private String title;

	@Value("${info.app.description}")
	private String description;
	
	@Value("${info.app.version:1.0.0}")
	private String version;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("dev.atanu.ecom")).paths(PathSelectors.regex("/.*")).build()
				.apiInfo(apiEndPointsInfo());
	}

	/**
	 * @return ApiInfo
	 */
	private ApiInfo apiEndPointsInfo() {
		return new ApiInfoBuilder().title(this.title).description(this.description)
				.contact(new Contact("Atanu Bhowmick", "https://github.com/atanubhowmick", "mail2atanu007@gmail.com"))
				.version(this.version)
				.build();
	}
}
