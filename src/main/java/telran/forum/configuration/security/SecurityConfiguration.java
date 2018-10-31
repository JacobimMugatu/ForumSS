package telran.forum.configuration.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/account/register");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/forum/post/*/like",
				"/forum/post/*/comment", "/forum/posts/tags"
				,"/forum/posts/period", "/forum/posts/author/*").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/forum/post")
		.hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/forum/post/*")
		.hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/forum/post/*")
		.hasAnyRole("USER", "MODERATOR");
		http.authorizeRequests().antMatchers(HttpMethod.PUT,"/forum/post")
		.hasAnyRole("USER", "ADMIN");
		
		
	}
}
