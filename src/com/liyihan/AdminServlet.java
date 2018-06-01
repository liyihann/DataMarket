package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdminServlet extends HttpServlet{
    private Connection conn = null;

    public AdminServlet(){
        try{
            this.conn = DBConnection.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        System.out.println("AdminServlet doGet");
        Admin admin = new Admin();
        int adminId = 0;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("adminCookie")) {
                adminId = BackLoginedManager.getInstance().getIdByCookie(cookie.getValue());
                System.out.println(adminId);
                if (0 != adminId) {
                    //System.out.println(adminId);
                    admin.setAdminid(adminId);
                }
            }
        }
        User userInfo = new User();
        List<User> userList = userInfo.getAllUser();
        req.setAttribute("users",userList);

        Request reqInfo = new Request();
        List<Request> requestList = reqInfo.getAllRequest();
        req.setAttribute("requests",requestList);

        Feedback feInfo = new Feedback();
        List<Feedback> feedbackList = feInfo.getAllFeedback();
        req.setAttribute("feedbacks",feedbackList);
        /*
        feInfo = feedbackList.get(0);
        req.setAttribute("admin",admin);
        req.setAttribute("feedback",feedbackList);
        req.setAttribute("firstf",feInfo);*/
        req.getRequestDispatcher("administration.jsp").forward(req,resp);

    }

}








