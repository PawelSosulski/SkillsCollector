package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.dao.SkillDao;
import com.github.pawelsosulski.skillscollector.entity.Skill;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/skill-addOrEdit")
public class SkillAddOrEditServlet extends HttpServlet {

    private SkillDao skillDao;

    @Override
    public void init() throws ServletException {
        skillDao = new SkillDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("job", "add");
        req.setAttribute("skill", new Skill());
        req.getRequestDispatcher("/WEB-INF/views/skill-addOrEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skill_id = req.getParameter("skill_id");
        String skillName = req.getParameter("skillName");
        List<Skill> allSkills = skillDao.getAll();
        List<String> skillsName = allSkills.stream().map(Skill::getName).collect(Collectors.toList());
        if (!skillsName.contains(skillName)) {
            if (skill_id.length() > 0) {
                Skill skill = skillDao.get(Long.valueOf(skill_id));
                skill.setName(skillName);
                skillDao.update(skill);
            } else {
                Skill skill = new Skill();
                skill.setName(skillName);
                skillDao.save(skill);
            }
        }
        resp.sendRedirect("/admin/skill-manage");
    }
}
