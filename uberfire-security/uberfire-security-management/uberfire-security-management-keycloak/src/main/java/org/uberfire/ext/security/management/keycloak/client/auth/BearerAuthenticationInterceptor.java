/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.uberfire.ext.security.management.keycloak.client.auth;

import org.jboss.resteasy.annotations.interception.ClientInterceptor;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.spi.interception.AcceptedByMethod;
import org.jboss.resteasy.spi.interception.ClientExecutionContext;
import org.jboss.resteasy.spi.interception.ClientExecutionInterceptor;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import java.lang.reflect.Method;

@Provider
@ClientInterceptor
/**
 * A Resteasy client interceptor used for Keycloak's client authentication based on the Bearer authentication method.
 * It does not intercept the "grantToken" and "refreshToken" calls from the token service endpoint (those requests are basic authentication based).
 * 
 * @since 0.9.0
 */
public class BearerAuthenticationInterceptor implements ClientExecutionInterceptor, AcceptedByMethod {

    private TokenManager tokenManager;

    public BearerAuthenticationInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }
    
    @Override
    public ClientResponse execute(ClientExecutionContext ctx) throws Exception {
        String token = tokenManager.getAccessTokenString();
        if ( null != token ) {
            ctx.getRequest().header(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }
        return ctx.proceed();
    }

    @Override
    public boolean accept(Class declaring, Method method) {
        String name = method.getName();
        boolean isToken = "grantToken".equals(name) || "refreshToken".equals(name);
        return !isToken;
    }
}
