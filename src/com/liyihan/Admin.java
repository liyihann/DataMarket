package com.liyihan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Admin {
    LoginVerify Check;

    {
        Check = new LoginVerify();
    }

    private int adminid;
    private String adminame;
    private String password;

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminame() {
        return adminame;
    }

    public void setAdminame(String adminame) {
        this.adminame = adminame;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MD5.getMD5(password);
    }

    public boolean adminVerify(String name,String passwd) {
        Connection conn = DBConnection.getConnection();
        try {

            boolean isValid = Check.adminVerify(conn, name, passwd);
            System.out.println("正在进行验证");
            return isValid;
        } catch (Exception e) {
            return false;
        }finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
            }
        }
    }

    public int getIdByUsername(String name) {
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            String inputName = Check.replace(name);
            String sql = "SELECT adminid FROM administrators WHERE aname='" + inputName + "'";
            rs = state.executeQuery(sql);
            if (rs.next()) {
                adminid = rs.getInt("adminid");
            }
            return adminid;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }finally {
            try {
                if (conn != null)
                    conn.close();
                if (state != null)
                    state.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
            }
        }
    }

}
