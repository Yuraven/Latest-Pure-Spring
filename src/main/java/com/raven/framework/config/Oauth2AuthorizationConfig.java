package com.raven.framework.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAuthorizationServer
public class Oauth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    TokenServiceFactory tokenServiceFactory;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .tokenServices(tokenServiceFactory.JwtTokenService())
                .authenticationManager(authenticationManager);
    }
}
