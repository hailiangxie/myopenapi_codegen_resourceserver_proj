package xie.hailiang.resourceserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	@Value("${jwtkey.publicKey}")
	private String publicKey;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
	    log.info(">>> ResourceServerConfig.configure(ResourceServerSecurityConfigurer resources) started");
	    resources.tokenStore(tokenStore());
	}

	@Bean
	public TokenStore tokenStore() {
	    return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		var converter = new JwtAccessTokenConverter();
	    converter.setVerifierKey(publicKey);
	    return converter;
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		log.info(">>> ResourceServerConfig.configure(HttpSecurity http) started");
		// Only the swagger ui and h2-console can be accessed without a bearer token.
		http
			.authorizeRequests()
			.antMatchers("/swagger-ui**", "/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**").permitAll()
		.and()
			.authorizeRequests()
			.anyRequest().authenticated();
	
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
}
