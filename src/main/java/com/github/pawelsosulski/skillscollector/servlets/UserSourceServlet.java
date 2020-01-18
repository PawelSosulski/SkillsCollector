package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.entity.Source;
import com.github.pawelsosulski.skillscollector.entity.User;
import com.github.pawelsosulski.skillscollector.dao.UserDao;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet ("/user/sources")
public class UserSourceServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<Source> userSource = userDao.getUserSourceWithSkills(user);
        req.setAttribute("userSource",userSource);
        req.getRequestDispatcher("/WEB-INF/views/user-sources.jsp").forward(req,resp);

    }
}
