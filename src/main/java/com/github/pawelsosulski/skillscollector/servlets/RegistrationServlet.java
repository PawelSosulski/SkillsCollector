package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.entity.User;
import com.github.pawelsosulski.skillscollector.dao.UserDao;
import com.github.pawelsosulski.skillscollector.helpers.UserRole;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {

    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("newUser", new User());
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        newUser.setUsername(login);
        newUser.setRole(UserRole.BASIC);
        if (userDao.getAllByUsername(login).size() == 0) {
            userDao.save(newUser);
            resp.sendRedirect("/login");
        } else {
            req.setAttribute("newUser", newUser);
            req.setAttribute("error","Nazwa użytkownika jest już zajęta");
            req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
        }
    }
}
