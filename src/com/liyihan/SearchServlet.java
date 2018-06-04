package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Search doPost");
        User me = new User();
        req.setCharacterEncoding("UTF-8");
        Request reqInfo = new Request();

        String search = req.getParameter("search-keyword");
        System.out.println(search);
        List<Request> list = reqInfo.getResults(search);
        req.setAttribute("results",list);
        System.out.println("正在进行重定向");
        req.getRequestDispatcher("search.jsp").forward(req,resp);
    }

}
