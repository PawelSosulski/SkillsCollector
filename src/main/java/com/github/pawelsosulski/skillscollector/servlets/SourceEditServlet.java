package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.dao.SkillDao;
import com.github.pawelsosulski.skillscollector.dao.SourceDao;
import com.github.pawelsosulski.skillscollector.entity.Skill;
import com.github.pawelsosulski.skillscollector.entity.Source;
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

@WebServlet("/admin/source-edit")
public class SourceEditServlet extends HttpServlet {
    private SourceDao sourceDao;
    private SkillDao skillDao;

    @Override
    public void init() throws ServletException {
        sourceDao = new SourceDao((SessionFactory) getServletContext()
                .getAttribute("session_factory"));
        skillDao = new SkillDao((SessionFactory) getServletContext()
                .getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sourceId = req.getParameter("sourceId");
        Source source = sourceDao.getWithSkills(Long.valueOf(sourceId)).get(0);
        req.setAttribute("source", source);
        req.setAttribute("skills", skillDao.getAll());
        req.setAttribute("job", "edit");
        req.getRequestDispatcher("/WEB-INF/views/source-addOrEdit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sourceId = req.getParameter("sourceId");
        Source source = sourceDao.get(Long.valueOf(sourceId));
        String name = req.getParameter("sourceName");
        source.setName(name);
        String description = req.getParameter("description");
        source.setDescription(description);
        String[] skills = req.getParameterValues("skills");
        if (skills!=null) {
            List<Long> skillsId = Arrays.stream(skills)
                    .map(Long::valueOf).collect(Collectors.toList());
            source.setAttachedSkills(skillDao.getAllByIds(skillsId));
        } else {
            source.setAttachedSkills(new ArrayList<>());
        }
        sourceDao.update(source);
        resp.sendRedirect("/admin/source-manage");
    }
}
