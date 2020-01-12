package com.github.pawelsosulski.skillscollector.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebFilter("/*")
public class LinkFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String servletPath = req.getServletPath();
        Set<String> hiddenLinks = new HashSet<>();
        if("/register".equalsIgnoreCase(servletPath)) {
            hiddenLinks.add("/register");
        }
        if ("/login".equalsIgnoreCase(servletPath)) {
            hiddenLinks.add("/login");
        }

        req.setAttribute("hiddenLinks",hiddenLinks);
        chain.doFilter(req,res);

    }
}
