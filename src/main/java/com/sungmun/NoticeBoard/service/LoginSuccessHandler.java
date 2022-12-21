package com.sungmun.NoticeBoard.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	public LoginSuccessHandler(String defaultUrl) {
		setDefaultTargetUrl(defaultUrl);
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		String redirectUrl = RedirectUrl(request);

		if (redirectUrl == null) {
			super.onAuthenticationSuccess(request, response, authentication);
			return;
		}

		request.getSession().removeAttribute("prevPage");
		getRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}

	private String RedirectUrl(HttpServletRequest request) {
		try {
			return (String) request.getSession().getAttribute("prevPage");
		} catch (NullPointerException e) {
			return null;
		}
	}
}
