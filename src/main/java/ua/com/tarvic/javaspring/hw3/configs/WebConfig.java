package ua.com.tarvic.javaspring.hw3.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    // /av/homer.jpg->path
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // folder files
        String path = "file:///" + System.getProperty("user.dir") + File.separator + "files" + File.separator;
        // https://localhost:8080/av/img.jpg
        registry
                .addResourceHandler(
                        "/av/*/*.gif",
                        "/av/*.png",
                        "/av/*/*.png",
                        "/av/*/*.jpeg")
                .addResourceLocations(path);
    }
}