/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.8.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2014
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.core.security.bo.component;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hoteia.qalingo.core.domain.enumtype.BoUrls;
import org.hoteia.qalingo.core.security.RedirectStrategy;
import org.hoteia.qalingo.core.service.BackofficeUrlService;
import org.hoteia.qalingo.core.web.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;

public class LoginUrlAuthenticationEntryPoint extends org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
    protected BackofficeUrlService backofficeUrlService;
	
    protected RequestUtil requestUtil;
	
    protected RedirectStrategy redirectStrategy;
    
    protected boolean forceHttps = false;

    protected boolean useForward = false;
    
	public LoginUrlAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}
	
	@Override
	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response,
													 AuthenticationException exception) {
		try {
			String url = backofficeUrlService.generateRedirectUrl(BoUrls.LOGIN, requestUtil.getRequestData(request));
			return url;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, 
						 AuthenticationException authException) throws IOException, ServletException {
		
        String redirectUrl = null;

        if (useForward) {

            if (forceHttps && "http".equals(request.getScheme())) {
                // First redirect the current request to HTTPS.
                // When that request is received, the forward to the login page will be used.
                redirectUrl = buildHttpsRedirectUrlForRequest(request);
            }

            if (redirectUrl == null) {
                String loginForm = determineUrlToUseForThisRequest(request, response, authException);

                RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);

                dispatcher.forward(request, response);

                return;
            }
        } else {
            // redirect to login page. Use https if forceHttps true
            redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);

        }

        redirectStrategy.sendRedirect(request, response, redirectUrl);
	}
	
	@Override
	protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response, 
												 AuthenticationException authException) {
		try {
			String url = backofficeUrlService.generateRedirectUrl(BoUrls.LOGIN, requestUtil.getRequestData(request));
			return url;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
}