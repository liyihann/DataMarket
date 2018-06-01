package com.liyihan;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String CONTENT_TYPE = "text/html; charset=utf-8";
        resp.setContentType(CONTENT_TYPE);
        req.setCharacterEncoding("UTF-8");
        System.out.println("Update doPost");
        User me = new User();
        String username = req.getParameter("nick-name");
        String message = req.getParameter("message");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                int userId = LoginedManager.getInstance().getIdByCookie(cookie.getValue());
                if (0 != userId) {
                    me.updateInformation(userId,username,message);
                    PrintWriter out = resp.getWriter();
                    out.println("<SCRIPT LANGUAGE='JavaScript'>alert('个人信息更新成功！');location.href='UserServlet';</SCRIPT>");

                }
            }
        }

//        System.out.println("正在进行重定向");
//        resp.sendRedirect("RequestServlet");

    }
}
