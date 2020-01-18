package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.entity.Skill;
import com.github.pawelsosulski.skillscollector.entity.User;
import com.github.pawelsosulski.skillscollector.dao.UserDao;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@WebServlet("/user/skills")
public class UserSkillServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User)req.getSession().getAttribute("user");
        List<Skill> userSkills = userDao.getUserSkills(user);

        Map<String,Integer> mapSkill=userSkills.stream()
                .collect(Collectors.toMap(Skill::getName, i ->1,(v, v2)->v+1));
    /*    userSkills.forEach(s -> {
            if (mapSkill.containsKey(s)) {
                Integer value = mapSkill.get(s);
                value = value + 1;
            } else {
                mapSkill.put(s.getName(),new Integer(1));
            }
        });*/
        req.setAttribute("userSkills",mapSkill);

        req.getRequestDispatcher("/WEB-INF/views/user-skills.jsp").forward(req,resp);
    }
}
