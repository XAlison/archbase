package org.soho.auth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collections;

/**
 */
public class SsoAuthProvider implements AuthenticationProvider {
    private static final Logger log = LoggerFactory.getLogger(SsoAuthProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.debug("自定义provider调用");

        //return createSuccessAuthentication();
        //GrantedAuthorityImpl grant = new GrantedAuthorityImpl("ROLE_SUPERVISOR");
        //GrantedAuthority[] arrayAuths = {grant};


        // 返回一个Token对象表示登陆成功
         return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(),
                Collections.<GrantedAuthority>emptyList());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
