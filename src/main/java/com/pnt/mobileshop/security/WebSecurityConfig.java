package com.pnt.mobileshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.authenticationProvider(authenticationProvider());
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/aboutPage/**","/shopPage/**", "/shopCategoryPage/**","/contactPage/**", "/registerPage", "/register", "/uploads/**", "/css/**","/fonts/**", "/images/**", "/loginPage/**").permitAll()
                .antMatchers("/admin/productsPage").hasAnyAuthority("ADMIN","CREATOR","EDITOR")
                .antMatchers("/admin/productFormPage").hasAnyAuthority("ADMIN","CREATOR")
                .antMatchers("/admin/addProduct").hasAnyAuthority("ADMIN", "CREATOR")
                .antMatchers("/admin/edit/**").hasAnyAuthority("ADMIN", "EDITOR")
                .antMatchers("/admin/delete/**").hasAuthority("ADMIN")
                .antMatchers("/admin/usersPage/**").hasAuthority("ADMIN")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/cartPage/**").hasAuthority("USER")
                .antMatchers("/checkoutPage/**").hasAuthority("USER")
                .antMatchers("/billPage/**").hasAuthority("USER")
                .antMatchers("/admin/updateProductPage/**").hasAuthority("EDITOR")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .permitAll()
                .loginProcessingUrl("/j_spring_security_check")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/loginPage");





    }
}
