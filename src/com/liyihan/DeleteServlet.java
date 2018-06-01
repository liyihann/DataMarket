package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteServlet  extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("Delete doPost");

        //User me = new User();
        Request re = new Request();
        int requestid = -1;
        try {
            requestid = Integer.parseInt(req.getParameter("requirement-delete-id"));
            System.out.println("requestid:"+requestid);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        try {
            re.deleteRequest(requestid);
            resp.getWriter().print("success");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


//        System.out.println("正在进行重定向");
//        resp.sendRedirect("RequestServlet");

    }
}

