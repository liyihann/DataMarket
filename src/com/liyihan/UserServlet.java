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

public class UserServlet extends HttpServlet{
    private Connection conn = null;

    public UserServlet(){
        try{
            this.conn = DBConnection.getConnection();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(conn != null)
                    conn.close();
            }catch (SQLException e){

            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        System.out.println("UserServlet doPost");
        User userInfo = new User();
        List<Request> accepted = null;
        List<Request> released = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                int userId = LoginedManager.getInstance().getIdByCookie(cookie.getValue());
                if (0 != userId) {
                    userInfo.getMyInfo(userId);
                    accepted = userInfo.getMyAccepted(userId);
                   /* if(accepted!=null){
                        System.out.println("accepted");
                    }else{
                        System.out.println("accepted null");
                    }
                    */
                    released = userInfo.getMyReleased(userId);
                 /* if(accepted!=null){
                        System.out.println("released");
                    }else{
                        System.out.println("released null");
                    }*/
                }
            }
        }
        req.setAttribute("user",userInfo);
        req.setAttribute("accepted",accepted);
        req.setAttribute("released",released);
        req.getRequestDispatcher("myinformation.jsp").forward(req,resp);
    }
}

