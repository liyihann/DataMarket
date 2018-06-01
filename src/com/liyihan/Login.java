package com.liyihan;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;


//@WebServlet("/Login")
public class Login extends HttpServlet {
    private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使用 GBK 设置中文正常显示
        doPost(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        User newUser = new User();
        String account =  request.getParameter("account");
        String password =  MD5.getMD5(request.getParameter("password"));
        System.out.println(account + password);
        try{
            if (newUser.userVerify(account,password)) {
                int id = newUser.getIdByUsername(account);
                String newCookie = MD5.getMD5(account + Calendar.getInstance().toString());
                LoginedManager.getInstance().addLoginedUser(newCookie, id);
                response.addCookie(new Cookie("user", newCookie));
                response.getWriter().print("success");
            } else {
                response.getWriter().print("fail");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
