package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class getAdminId extends HttpServlet {



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("getAdminId doPost");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("adminCookie")) {
                int adminId = BackLoginedManager.getInstance().getIdByCookie(cookie.getValue());
                System.out.println(adminId);
                if (0 != adminId) {
                    System.out.println(adminId);
                    response.getWriter().print(adminId);
                }
            }
        }
    }




}
