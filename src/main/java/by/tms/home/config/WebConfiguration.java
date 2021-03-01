package by.tms.home.config;

import by.tms.home.interceptor.ExampleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Autowired
    private ExampleInterceptor exampleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry
//                .addInterceptor(exampleInterceptor)
//                .addPathPatterns("/book")
//                .addPathPatterns("/book/save");
    }
}
