package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.dao.SkillDao;
import com.github.pawelsosulski.skillscollector.dao.UserDao;
import com.github.pawelsosulski.skillscollector.entity.Skill;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/skill-manage")
public class SkillManageServlet extends HttpServlet {

    private SkillDao skillDao;
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        skillDao = new SkillDao((SessionFactory) getServletContext().getAttribute("session_factory"));
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Skill> allSkills = skillDao.getAll();
        req.setAttribute("allSkills", allSkills);
        req.getRequestDispatcher("/WEB-INF/views/skill-manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("choice");
        String skill_id = req.getParameter("skill_id");
        if (skill_id != null) {
            Skill skill = skillDao.get(Long.valueOf(skill_id));
            switch (choice) {
                case "edit":
                    req.setAttribute("job", "edit");
                    req.setAttribute("skill", skill);
                    req.getRequestDispatcher("/WEB-INF/views/skill-addOrEdit.jsp").forward(req, resp);
                    break;
                case "delete":
                    List<Skill> allUsersSkills = userDao.getAllUsersSkills();
                    if (!allUsersSkills.contains(skill))
                    skillDao.delete(skill);
                    else {
                        //TODO
                        // make info about cannot delete skill

                    }
                    break;
            }
        }
        resp.sendRedirect("/admin/skill-manage");

    }
}
