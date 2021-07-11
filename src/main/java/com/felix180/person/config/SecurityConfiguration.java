package com.felix180.person.config;

import com.felix180.person.workfactor.BcCryptWorkFactorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final BcCryptWorkFactorService bcCryptWorkFactorService;

  public SecurityConfiguration(BcCryptWorkFactorService bcCryptWorkFactorService) {
    this.bcCryptWorkFactorService = bcCryptWorkFactorService;
  }

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/*")
        .permitAll()
        /* TODO add Security all request
              .anyRequest()
              .authenticated()

        */
        .and()
        .httpBasic();

    httpSecurity.headers().frameOptions().disable();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    // we must use deprecated encoder to support their encoding
    String encodingId = "bcrypt";
    Map<String, PasswordEncoder> encoders = new HashMap<>();
    encoders.put(
        encodingId, new BCryptPasswordEncoder(bcCryptWorkFactorService.calculateStrength()));
    encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
    encoders.put("scrypt", new SCryptPasswordEncoder());

    return new DelegatingPasswordEncoder(encodingId, encoders);
  }
}
