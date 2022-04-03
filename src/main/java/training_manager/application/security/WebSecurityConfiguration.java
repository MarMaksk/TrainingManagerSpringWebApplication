package training_manager.application.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderImplementation authenticationProvider;
    private final UserDetailsServiceImplementation userService;
    private final BCryptPasswordEncoder encoder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userService).passwordEncoder(encoder);
//        auth.inMemoryAuthentication()
//                .passwordEncoder(encoder)
//                .withUser("user")
//                .password("$2a$10$0C3XNq8tuxvevO8jQyPvcuTF7Vdc6fx4CAc1YaecL.Kd2QiUP//FO") //user
//                .roles("user", "admin")
//                .and()
//                .withUser("admin")
//                .password("admin") //admin
//                .roles("user", "admin");
    }

//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().and().debug(true);
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated()
//                .and().httpBasic();
//                .antMatchers("/api/**")
//                .access("hasRole('ROLE_USER')");
//        http.formLogin()
//                .defaultSuccessUrl("/", true)
//                .loginPage("/login");
        http
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers("/register").anonymous()
//                .antMatchers("/notSecure").permitAll()
//                .antMatchers("/admin").hasRole("ADMIN")
//                .antMatchers("/user", "/user/**").hasRole("USER")
//                .antMatchers("/logout").authenticated()
//                .antMatchers("/").authenticated()
                .antMatchers("/register", "/register/**").anonymous()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
//                .and().httpBasic()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authorize")
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
//        http.cors().and().csrf().disable().httpBasic().and()
//                .authorizeRequests()
//                .anyRequest().authenticated()
//                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");
//                .antMatchers("/api/**").permitAll()
//                .anyRequest().authenticated().and()
//                .formLogin()
//                .defaultSuccessUrl("/", true)
//                .loginPage("/login");


//        http
//                .authorizeRequests()
//                .antMatchers("/css/**", "/js/**", "/index*", "/json/**", "/*.ico", "/images/**", "/login/**").permitAll()
//                .antMatchers("/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/**").hasRole(RoleEnum.USER.name())
//                .antMatchers(HttpMethod.GET, "/**").authenticated()
//                .antMatchers(HttpMethod.GET, "/**").hasRole(RoleEnum.ADMIN.name())
//                .antMatchers(HttpMethod.GET, "/**").authenticated()
//                .antMatchers(HttpMethod.GET, "/**").hasRole(RoleEnum.HELPER.name())
//                .antMatchers(HttpMethod.GET, "/**").authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/")
//                .failureForwardUrl("/login?error=true")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout=true")
//                .and()
//                .exceptionHandling().accessDeniedPage("/access-denied")
//                .and()
//                .rememberMe()
//                .and()
//                .csrf().disable()
//        ;
    }


  /*  @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // На случай вынесения фронта в отдельный проект
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "UPDATE")); // На случай вынесения фронта в отдельный проект

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return urlBasedCorsConfigurationSource;
    }*/
}

