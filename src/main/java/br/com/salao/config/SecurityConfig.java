package br.com.salao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	//@Autowired
	//private DataSource datasource;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.authorizeRequests()
				.antMatchers("/").permitAll()// permit public access to home page			
				.antMatchers("/console/**").permitAll()
				.antMatchers("/design").hasAnyRole("USER", "ADMIN", "MANAGER")
			.and()
			.formLogin();
		
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		/*auth.inMemoryAuthentication()
			.withUser("caco")
				.password("{noop}123456")
				.authorities("ROLE_USER")
			.and()
			.withUser("lucas")
				.password("{noop}123456")
				.authorities("ROLE_USER");*/
		
		/*String users_query = 
			"SELECT USERNAME, PASSWORD, ENABLED FROM USER U\r\n" + 
			" WHERE U.USERNAME = ?";
		
		String authoryties_query = 
			"SELECT \r\n" + 
			"   U.USERNAME USERNAME,  \r\n" + 
			"   A.NAME     AUTHORITY\r\n" + 
			"  FROM USER U, AUTHORITY A, USER_AUTHORITIES UA\r\n" + 
			" WHERE U.USERNAME = ?\r\n" + 
			"   AND U.ID = UA.USER_ID\r\n" + 
			"   AND A.ID = UA.AUTHORITIES_ID";
		
		String groups_query = 
			"SELECT \r\n" + 
			"  G.ID ID,\r\n" + 
			"  G.NAME GROUP_NAME,\r\n" + 
			"  A.NAME AUTHORITY\r\n" + 
			"FROM \r\n" + 
			"  USER U,\r\n" + 
			"  GROUPS G,\r\n" + 
			"  AUTHORITY A,\r\n" + 
			"  GROUPS_USERS GU,\r\n" + 
			"  GROUPS_AUTHORITIES GA\r\n" + 
			"WHERE U.USERNAME = ?\r\n" + 
			"  AND U.ID = GU.USERS_ID\r\n" + 
			"  AND G.ID = GU.GROUPS_ID\r\n" + 
			"  AND G.ID = GA.GROUP_ID\r\n" + 
			"  AND A.ID = GA.AUTHORITIES_ID";
		
		auth.jdbcAuthentication()
			.dataSource(datasource)
			.usersByUsernameQuery(users_query)
			.authoritiesByUsernameQuery(authoryties_query)
			.groupAuthoritiesByUsername(groups_query);*/
		
		auth.userDetailsService(userDetailsService);
		
				
	}
			
}
