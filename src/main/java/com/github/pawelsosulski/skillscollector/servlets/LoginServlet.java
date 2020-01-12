package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.model.User;
import com.github.pawelsosulski.skillscollector.model.dao.UserDao;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({"/login"})
public class LoginServlet extends HttpServlet {

    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<User> allUsers = userDao.getAllByUsernameAndPassword(login, password);
        if (allUsers.size()==1) {
            User user = allUsers.get(0);
            req.getSession().invalidate();
            req.getSession(true).setAttribute("user",user);
            resp.sendRedirect("/user/skills");
        } else {
            req.setAttribute("error","Błędny login i hasło");
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req,resp);
        }



    }
}
