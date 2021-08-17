package jp.co.neosystem.kadai.api;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.antMatchers("/css/**")
				.permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/js/**")
				.permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/task")
				.permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/task/**")
				.permitAll();

		CookieCsrfTokenRepository repository = CookieCsrfTokenRepository.withHttpOnlyFalse();
		http.csrf().csrfTokenRepository(repository);
		return;
	}
}
