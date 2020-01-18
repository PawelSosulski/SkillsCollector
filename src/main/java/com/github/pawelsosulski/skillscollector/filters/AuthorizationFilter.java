package com.github.pawelsosulski.skillscollector.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter("/*")
public class AuthorizationFilter extends HttpFilter {


    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        Set<String> unprotectedLink = new HashSet<>();
        unprotectedLink.add("/login");
        unprotectedLink.add("/");
        unprotectedLink.add("/register");
        Set<String> protectedLink = new HashSet<>();
        protectedLink.add("/user/skills");
        protectedLink.add("/user/sources");
        protectedLink.add("/user/unknown-sources");
        protectedLink.add("/logout");
        protectedLink.add("/sources/confirm");
        protectedLink.add("/admin/source-manage");
        protectedLink.add("/admin/source-add");
        protectedLink.add("/admin/source-edit");
        protectedLink.add("/admin/source-remove");
        protectedLink.add("/admin/skill-manage");
        protectedLink.add("/admin/skill-addOrEdit");
        String servletPath = req.getServletPath();

        if (unprotectedLink.contains(servletPath)) {
            chain.doFilter(req,res);
        } else if (protectedLink.contains(servletPath) && req.getSession().getAttribute("user") != null) {
            chain.doFilter(req,res);
        } else {
            res.sendRedirect("/");
        }

    }
}
