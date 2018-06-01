package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Register extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = DBConnection.getConnection();
        String account = request.getParameter("account");
        String password = MD5.getMD5(request.getParameter("password"));
        System.out.println(account + password);
        ResultSet rs = null;
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "select * from users where username=\'" + account + "\';";
            rs = statement.executeQuery(sql);
            if (rs.next()) {
                response.getWriter().print("fail");
                return;
            } else {
                sql = "insert into users(username, upasswd, avatar, message) values(\'" + account + "\', \'" + password + "\', \'/img/moren.png\', \'\');";
                int i = statement.executeUpdate(sql);
                connection.commit();
                if (i == 1) {
                    response.getWriter().print("success");
                } else {
                    response.getWriter().print("fail");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (null != rs) {
                    rs.close();
                }
                if (null != statement) {
                    statement.close();
                }
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
