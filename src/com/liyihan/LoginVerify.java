package com.liyihan;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginVerify {

    public boolean userVerify(Connection conn, String s1, String s2) throws SQLException {
        Statement state = conn.createStatement();
        ResultSet rs = null;
        boolean isValid = true;
        int userid=-1;
        String username="",passwd="";
        String inputName = replace(s1);
        String inputPasswd = replace(s2);
        String sql = "SELECT * FROM users WHERE username='" +inputName+"'";
        rs = state.executeQuery(sql);
        if(!rs.next()){
            isValid = false;
        }else {
            passwd=rs.getString("upasswd");
            if(inputPasswd.equals(passwd)) {

                isValid = true;
            }else {
                isValid=false;
            }
        }
        return  isValid;
    }

    public boolean adminVerify(Connection conn,String s1,String s2)throws SQLException{
        Statement state = conn.createStatement();
        ResultSet rs = null;
        boolean isValid = true;
        int adminid = -1;
        String aName="",aPasswd="";
        String inputName = replace(s1);
        String inputPasswd = replace(s2);
        String sql = "SELECT * FROM administrators WHERE aname='"+inputName+"'";
        rs = state.executeQuery(sql);
        System.out.println("正在查询结果集");
        if(!rs.next()){
            isValid = false;
        }else {
            aPasswd=rs.getString("apasswd");
            if(inputPasswd.equals(aPasswd)){
                isValid=true;
            }else{
                isValid=false;
            }
        }
        return isValid;
    }


    public String replace(String s) {
        try {
            if ((s == null) || (s.equals("")))
                return "";

            StringBuffer stringbuffer = new StringBuffer();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                switch (c) {
                    case '"':
                        stringbuffer.append("&quot;");
                        break;
                    case '\'':
                        stringbuffer.append("&#039;");
                        break;
                    case '|':
                        stringbuffer.append("");
                        break;
                    case '&':
                        stringbuffer.append("&amp;");
                        break;
                    case '<':
                        stringbuffer.append("&lt;");
                        break;
                    case '>':
                        stringbuffer.append("&gt;");
                        break;
                    default:
                        stringbuffer.append(c);
                }
            }
            return stringbuffer.toString().trim();
        } catch (Exception e) {
        }
        return "";
    }



}
