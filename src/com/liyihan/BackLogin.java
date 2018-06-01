package com.liyihan;


import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;


public class BackLogin extends HttpServlet{


    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String CONTENT_TYPE = "text/html; charset=utf-8";
        response.setContentType(CONTENT_TYPE);

        Admin newAdmin = new Admin();
        String account = request.getParameter("account");
        String password = MD5.getMD5(request.getParameter("password"));
        System.out.println(account + password);
        try {

            if(newAdmin.adminVerify(account,password)){
                int id = newAdmin.getIdByUsername(account);
                String newCookie = MD5.getMD5(account+ Calendar.getInstance().toString());
                BackLoginedManager.getInstance().addLoginedAdmin(newCookie,id);
                response.addCookie(new Cookie("adminCookie",newCookie));
                System.out.println("正在进行重定向");
                response.sendRedirect("AdminServlet");
                return;
            }else{
                PrintWriter out = response.getWriter();
                out.println("<SCRIPT LANGUAGE='JavaScript'>alert('用户名或密码不正确！');location.href='login.html';</SCRIPT>");
                return;

            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
