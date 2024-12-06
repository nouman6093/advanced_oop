package com.code;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class AutoLogoutFilter implements Filter {
    private static final int INACTIVITY_TIMEOUT = 30;

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("AutoLogoutFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        if (session != null) {
            Long lastAccessTime = (Long) session.getAttribute("lastAccessTime");
            if (lastAccessTime == null) {
                session.setAttribute("lastAccessTime", System.currentTimeMillis());
            } else {
                long currentTime = System.currentTimeMillis();
                long inactiveDuration = (currentTime - lastAccessTime) / 1000;
                if (inactiveDuration > INACTIVITY_TIMEOUT) {
                    session.invalidate();
                    httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp?message=Session%20Expired");
                    return;
                } else {
                    session.setAttribute("lastAccessTime", currentTime);
                }
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
