package br.com.my.seguranca;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(createUserDetailsResolver());
	}

	@Bean
	public UsuarioLogadoDetalhesArgumentResolver createUserDetailsResolver() {
		return new UsuarioLogadoDetalhesArgumentResolver();
	}
}
