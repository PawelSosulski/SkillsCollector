package com.github.pawelsosulski.skillscollector.servlets;

import com.github.pawelsosulski.skillscollector.dao.UserDao;
import com.github.pawelsosulski.skillscollector.entity.Skill;
import com.github.pawelsosulski.skillscollector.entity.Source;
import com.github.pawelsosulski.skillscollector.entity.User;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet("/")
public class MainSiteServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        userDao = new UserDao((SessionFactory) getServletContext().getAttribute("session_factory"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> allUsers = userDao.getAll();

        Map<User, Integer> usersSkillMap = new HashMap<>();
        for (User user : allUsers) {
            usersSkillMap.put(user, userDao.getUserSkills(user).size());
        }
        LinkedHashMap<User, Integer> usersAndSkillsValueDesc = usersSkillMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.
                        toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        req.setAttribute("userMap", usersAndSkillsValueDesc);

        Map<String, Integer> collectAllSkills = userDao.getAllUsersSkills().stream()
                .collect(Collectors.toMap(Skill::getName, i -> 1, (v, v2) -> v + 1));

        LinkedHashMap<String, Integer> skillMapSorted= collectAllSkills.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        req.setAttribute("skillMap",skillMapSorted);


        Map<String, Integer> collectAllSources = userDao.getAllUsersSource()
                .stream()
                .collect(Collectors.toMap(Source::getName, i -> 1, (v, v2) -> v + 1));


        LinkedHashMap<String, Integer> sourceMapSorted= collectAllSources.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        req.setAttribute("sourceMap",sourceMapSorted);

        req.getRequestDispatcher("/WEB-INF/views/main-site.jsp").forward(req, resp);

    }


}
