package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.dao.SkillDao;
import com.github.pawelsosulski.skillscollector.dao.SourceDao;
import com.github.pawelsosulski.skillscollector.entity.Skill;
import com.github.pawelsosulski.skillscollector.entity.Source;
import com.sun.jmx.snmp.SnmpOidRecord;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/admin/source-add")
public class SourceAddServlet extends HttpServlet {
    private SkillDao skillDao;
    private SourceDao sourceDao;

    @Override
    public void init() throws ServletException {
        skillDao = new SkillDao((SessionFactory) getServletContext()
                .getAttribute("session_factory"));
        sourceDao = new SourceDao((SessionFactory) getServletContext()
                .getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Source source = new Source();
        List<Skill> allSkills = skillDao.getAll();
        req.setAttribute("source", source);
        req.setAttribute("skills", allSkills);
        req.setAttribute("job", "add");
        req.getRequestDispatcher("/WEB-INF/views/source-addOrEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Source source = new Source();
        String name = req.getParameter("sourceName");
        String description = req.getParameter("description");
        String[] skills = req.getParameterValues("skills");
        if (skills != null) {
            List<Long> skillsId = Arrays.stream(skills)
                    .map(Long::valueOf).collect(Collectors.toList());
            source.setAttachedSkills(skillDao.getAllByIds(skillsId));
        } else {
            source.setAttachedSkills(new ArrayList<>());
        }
        source.setName(name);
        source.setDescription(description);
        sourceDao.save(source);
        resp.sendRedirect("/admin/source-manage");
    }
}
