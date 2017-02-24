package com.sharebo.emp.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.sharebo.emp.web.service.impl.LoginService;
/**
 * spring Security安全配置
 * @author niewei
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
	   @Autowired
	   public LoginService service;
	   @Override
	    protected void configure(HttpSecurity http) throws Exception {
		   http.headers().frameOptions().disable();
		   http.csrf().disable()
	        .authorizeRequests()
	                .antMatchers("/login","/css/**","/img/*","/js/*")
	                .permitAll()//这个链接不被保护
	                .anyRequest().authenticated()
	                .and()
	            .formLogin()
	                .loginPage("/login")//**登陆页面**//*
	                .permitAll()
	                .successHandler(loginSuccessHandler())
	                .and()
	            .logout()
	            .logoutUrl("/logout")// 登出页面
	            .logoutSuccessUrl("/login")// 登出后页面
	            .permitAll()
                .invalidateHttpSession(true)// 清除SESSION
                .deleteCookies("JSESSIONID");// 清cookies  
		   }
	    @Autowired
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        /*auth
	            .inMemoryAuthentication()
	                .withUser("user").password("password").roles("USER");*/
	    	 auth.userDetailsService(service);
	    }
	    @Bean  
	    public LoginSuccessHandler loginSuccessHandler(){  
	        return new LoginSuccessHandler();  
	    } 
}
