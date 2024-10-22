package ma.youcode.cch.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ImportResource("classpath:application-context.xml")
@ComponentScan(basePackages = "ma.youcode.cch")
public class WebConfig implements WebMvcConfigurer {

}
