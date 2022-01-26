package ru.itmo.wp.lesson8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.itmo.wp.lesson8.security.interceptor.SecurityInterceptor;

@SpringBootApplication
public class Lesson8Application implements WebMvcConfigurer {
	private final SecurityInterceptor securityInterceptor;

	public Lesson8Application(SecurityInterceptor securityInterceptor) {
		this.securityInterceptor = securityInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(securityInterceptor);
	}

	public static void main(String[] args) {
		SpringApplication.run(Lesson8Application.class, args);
	}

}
