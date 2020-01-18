package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.dao.SourceDao;
import com.github.pawelsosulski.skillscollector.entity.Source;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/source-manage")
public class SourceManageServlet extends HttpServlet {
    private SourceDao sourceDao;

    @Override
    public void init() throws ServletException {
        sourceDao = new SourceDao((SessionFactory)getServletContext().
                getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Source> allSource = sourceDao.getAllWithSkills();
        req.setAttribute("allSource",allSource);
        //String errorMsg = (String) req.getAttribute("errorMsg");
        //req.setAttribute("errorMsg",errorMsg);
       // System.out.println("error:" + errorMsg);
        req.getRequestDispatcher("/WEB-INF/views/source-manage.jsp").forward(req,resp);
    }



}
