package training_manager.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderImplementation authenticationProvider;
    private final UserDetailsServiceImplementation userService;
    @Qualifier("passwordEncoders")
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public WebSecurityConfiguration(AuthenticationProviderImplementation authenticationProvider, UserDetailsServiceImplementation userService, BCryptPasswordEncoder encoder) {
        this.authenticationProvider = authenticationProvider;
        this.userService = userService;
        this.encoder = encoder;
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("user")
                .password("$2a$10$0C3XNq8tuxvevO8jQyPvcuTF7Vdc6fx4CAc1YaecL.Kd2QiUP//FO") //user
                .roles("user", "admin");
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().and().debug(true);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers().permitAll() // Здесь добалвяются объекты к которым открываем доступ для всех
//                .antMatchers().hasRole(RoleEnum.ROLE_USER.name()) //Здесь тем у кого есть определнная роль
//                .antMatchers().authenticated() //Здесь разрешить авторизированным юзерам
//                .and()
//                .formLogin()
//                .loginPage("/login") //Путь к странице логин
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/")
//                .failureForwardUrl("/login?logout=false")
//                .and()
//                .logout()
//                .logoutSuccessUrl("/login?logout=true")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/access-denied")
//                .and()
//                .rememberMe()
//                .and()
//                .csrf().disable()
//                .cors();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoders() {
        return new BCryptPasswordEncoder();
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
