package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AcceptServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("Accept doPost");
        //Request newReq = new Request();
        User me = new User();
        int requestid = -1;
        try {
            requestid = Integer.parseInt(req.getParameter("requirement-receive-id"));
            System.out.println("requestid:"+requestid);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                int userId = LoginedManager.getInstance().getIdByCookie(cookie.getValue());
                if (0 != userId&& 0!=requestid) {
                    me.acceptRequest(requestid,userId);
                    resp.getWriter().print("success");
                }
            }
        }

//        System.out.println("正在进行重定向");
//        resp.sendRedirect("RequestServlet");

    }
}

