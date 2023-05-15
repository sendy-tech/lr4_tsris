package sport.competitions.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ControllersConfig implements WebMvcConfigurer  {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/competitions").setViewName("competitions");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/forbidden").setViewName("forbidden");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/").setViewName("competitions");
    }
}