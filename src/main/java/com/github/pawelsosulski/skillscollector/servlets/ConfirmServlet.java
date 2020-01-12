package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.dao.SourceDao;
import com.github.pawelsosulski.skillscollector.dao.UserDao;
import com.github.pawelsosulski.skillscollector.entity.Source;
import com.github.pawelsosulski.skillscollector.entity.User;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sources/confirm")
public class ConfirmServlet extends HttpServlet {
private SourceDao sourceDao;
private UserDao userDao;

    @Override
    public void init() throws ServletException {
        sourceDao = new SourceDao((SessionFactory) getServletContext().getAttribute("session_factory"));
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sourceId = req.getParameter("sourceId");
        if (sourceId.length()>0) {
            Source source = sourceDao.get(Long.valueOf(sourceId));
            req.setAttribute("source",source);
            req.getRequestDispatcher("/WEB-INF/views/confirm-source.jsp").forward(req,resp);
        } else {
            resp.sendRedirect("/user/unknown-sources");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sourceId = (String)req.getParameter("sourceId");
        Source source = sourceDao.get(Long.valueOf(sourceId));
        User user = (User)req.getSession().getAttribute("user");
     //   userDao.addNewSource(user,source);
       userDao.confirmSource(user,source);
        resp.sendRedirect("/user/sources");
    }
}
