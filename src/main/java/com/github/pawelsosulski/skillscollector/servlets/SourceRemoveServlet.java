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
import java.util.List;

@WebServlet("/admin/source-remove")
public class SourceRemoveServlet extends HttpServlet {
    private SourceDao sourceDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        sourceDao = new SourceDao((SessionFactory) getServletContext()
                .getAttribute("session_factory"));
        userDao = new UserDao((SessionFactory) getServletContext()
                .getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String source_id = req.getParameter("source_id");
        System.out.println("Source id: " + source_id);
        Source source = sourceDao.get(Long.valueOf(source_id));
        getServletContext().setAttribute("source", source);
        req.getRequestDispatcher("/WEB-INF/views/source-delete.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String source_id = req.getParameter("source_id");
        Source source = sourceDao.get(Long.valueOf(source_id));
        List<User> allUserWithSource = userDao.getAllUserWithSource(source);
        if (allUserWithSource.size() == 0) {
            sourceDao.delete(source);
        } else {
            String errorMsg = new String("Nie można usunąć źródła: " + source.getName());
            req.setAttribute("errorMsg", errorMsg);

        }
        resp.sendRedirect("/admin/source-manage");

    }
}
