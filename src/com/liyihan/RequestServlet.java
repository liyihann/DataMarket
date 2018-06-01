package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RequestServlet extends HttpServlet{
    private Connection conn = null;

    public RequestServlet(){
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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        System.out.println("RequestServlet doGet");
        Request reqInfo = new Request();
        List<Request> list = reqInfo.getAllRequest();
        req.setAttribute("requests",list);
        req.getRequestDispatcher("requirement.jsp").forward(req,resp);

    }
}
