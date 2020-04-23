package br.com.compasso.pautas.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())
          .build()
          .apiInfo(metaInfo());                                           
    }
 
    
    private ApiInfo metaInfo(){
    	
    	@SuppressWarnings("rawtypes")
		Collection<VendorExtension> ve= new ArrayList<>();
    	
    	ApiInfo apiInfo = new ApiInfo("Vote Session API", "An rest API for voting in polls", "1.0", 
    			"Terms of Service",
    			new Contact("William Silveira", "https://github.com/williamNext", "williamsilveira99@gmail.com"),
    			"no license","https://github.com/williamNext",ve);
    	return apiInfo;
    }
}