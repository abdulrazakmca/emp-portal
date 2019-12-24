package com.com.mcompany.securityoauthjwtapp.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private final TokenStore tokenStore;

	public ResourceServerConfiguration(final TokenStore tokenStore) {
		this.tokenStore = tokenStore;
	}

	@Override
	public void configure(final ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurer#configure(org.
	 * springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(final HttpSecurity http) throws Exception {
		/*
		 * http.httpBasic().disable().sessionManagement().sessionCreationPolicy(
		 * SessionCreationPolicy.STATELESS).and()
		 * .anonymous().and().authorizeRequests().antMatchers(HttpMethod.OPTIONS,
		 * "/**").permitAll() .anyRequest().permitAll()
		 * 
		 * .and().csrf().disable().exceptionHandling() .authenticationEntryPoint(new
		 * HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)).and().logout()
		 * .logoutUrl("/logout").logoutSuccessUrl("/");
		 */

//		 http
//	        .authorizeRequests()
//	            .antMatchers("/oauth", "/logout.do").permitAll()
//	            .antMatchers("/**").authenticated()
//	        .and()
//	        .formLogin()
//	            .loginProcessingUrl("/login")
//	            .loginPage("/login.html");
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("oauth/token").permitAll().
//		anyRequest().authenticated();
		/*
		 * http.csrf().disable().formLogin().permitAll().loginPage("/oauth/token").
		 * loginProcessingUrl("/login")
		 * .failureUrl("/common/error.html").defaultSuccessUrl("/oauth/authorize").
		 * usernameParameter("username")
		 * .passwordParameter("password").and().authorizeRequests().antMatchers("/login"
		 * ).permitAll() .anyRequest().authenticated().and().httpBasic().disable();
		 */
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers("/oauth/token").permitAll().
		anyRequest().authenticated();
	}

}
