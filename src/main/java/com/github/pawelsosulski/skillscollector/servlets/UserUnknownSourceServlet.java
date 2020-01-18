package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.entity.Source;
import com.github.pawelsosulski.skillscollector.entity.User;
import com.github.pawelsosulski.skillscollector.dao.SourceDao;
import com.github.pawelsosulski.skillscollector.dao.UserDao;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/unknown-sources")
public class UserUnknownSourceServlet extends HttpServlet {
    private UserDao userDao;
    private SourceDao sourceDao;
    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
        sourceDao= new SourceDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<Source> allSource = sourceDao.getAllWithSkills();
       // List<Source> allSource = sourceDao.getAll();

        List<Source> userSource = userDao.getUserSource(user);
        userSource.forEach(s -> {
            if (allSource.contains(s)) {
                allSource.remove(s);
            }
        });
        req.setAttribute("userUnknownSource",allSource);
        req.getRequestDispatcher("/WEB-INF/views/user-unknown-sources.jsp").forward(req,resp);
    }
}
