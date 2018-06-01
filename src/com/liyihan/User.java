package com.liyihan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    LoginVerify Check = new LoginVerify();

    private int userid;
    private String username;
    private String password;
    private String avatar;
    private String message;

    private List<Request> accepted;
    private List<Request> released;

    public User() {
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = MD5.getMD5(password);
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Request> getAccepted() {
        return accepted;
    }

    public void setAccepted(List<Request> accepted) {
        this.accepted = accepted;
    }

    public List<Request> getReleased() {
        return released;
    }

    public void setReleased(List<Request> released) {
        this.released = released;
    }

    public boolean userVerify(String username, String passwd) {
        try {
            Connection conn = DBConnection.getConnection();
            boolean isValid = Check.userVerify(conn, username, passwd);
            return isValid;
        } catch (SQLException e) {
            return false;
        }
    }

    public int getIdByUsername(String name) {
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();

            String inputName = Check.replace(name);
            String sql = "SELECT userid FROM users WHERE username='" + inputName + "'";
            rs = state.executeQuery(sql);
            if (rs.next()) {
                System.out.println("准备读取用户id");
                this.userid = rs.getInt("userid");
                System.out.println(userid);
            }
            return userid;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        } finally {
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

    public String getAvartarById(int id) {
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            String sql = "SELECT avatar FROM users WHERE userid='" + id + "'";
            rs = state.executeQuery(sql);
            if (rs.next()) {
                avatar = rs.getString("avatar");
            }
            return avatar;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
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

    public String getMessageById(int id) {
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();

            String sql = "SELECT message FROM users WHERE userid='" + id + "'";
            rs = state.executeQuery(sql);
            if (rs.next()) {
                message = rs.getString("message");
            }
            return message;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
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

    public List<User> getAllUser() {
        Connection conn = DBConnection.getConnection();
        List<User> list = new ArrayList<>();
        String sql = "SELECT userid,username,upasswd,message FROM users";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUserid(rs.getInt("userid"));
                u.setUsername(rs.getString("username"));
                u.setPassword(rs.getString("upasswd"));
                u.setMessage(rs.getString("message"));
                list.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt != null)
                    pstmt.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
            }
        }
        return list;
    }

    public void getMyInfo(int id) {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM users WHERE userid = '" + id + "'";
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            if (rs.next()) {
                this.setUserid(rs.getInt("userid"));
                this.setUsername(rs.getString("username"));
                this.setPassword(rs.getString("upasswd"));
                this.setAvatar(rs.getString("avatar"));
                this.setMessage(rs.getString("message"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    public List<Request> getMyAccepted(int id) {
        List<Request> list = new ArrayList<Request>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT request_title,request_content,request_tag,state " +
                "FROM (user_request RIGHT JOIN requests ON user_request.requestid=requests.requestid)" +
                //"LEFT JOIN request_state ON request_state.stateid=requests.state"+
                "WHERE userid = '" + id + "' AND request_type=1";
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Request r = new Request();
                r.setTitle(rs.getString("request_title"));
                r.setContent(rs.getString("request_content"));
                r.setTagid(rs.getInt("request_tag"));
                r.setTag(r.getTagByTagId(r.getTagid()));
                r.setStateid(rs.getInt("state"));
                r.setStatename(r.getStateByStateId(r.getStateid()));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return list;
    }

    public List<Request> getMyReleased(int id) {
        List<Request> list = new ArrayList<Request>();
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT request_title,request_content,request_tag,state " +
                "FROM (user_request RIGHT JOIN requests ON user_request.requestid=requests.requestid)" +
                //"LEFT JOIN request_state ON request_state.stateid=requests.state"+
                "WHERE userid = '" + id + "' AND request_type=2";
        Statement state = null;
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                Request r = new Request();
                r.setTitle(rs.getString("request_title"));
                r.setContent(rs.getString("request_content"));
                r.setTagid(rs.getInt("request_tag"));
                r.setTag(r.getTagByTagId(r.getTagid()));
                r.setStateid(rs.getInt("state"));
                r.setStatename(r.getStateByStateId(r.getStateid()));
                list.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return list;
    }


    public int acceptRequest(int requestid,int userid){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        int i = 0;//request id
        String sql1 = "INSERT INTO user_request(requestid, userid,request_type)VALUES(?,?,?);";
        String sql2 = "UPDATE requests SET state = 2 WHERE requestid='"+requestid+"'";
        System.out.println(requestid);
        System.out.println(userid);
        try{
            pstmt1 = conn.prepareStatement(sql1);

            pstmt1.setInt(1,requestid);
            pstmt1.setInt(2,userid);
            pstmt1.setInt(3,1);
            pstmt2 = conn.prepareStatement(sql2);
            i = pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt1 != null)
                    pstmt1.close();
            } catch (SQLException e) {
            }
        }
        return i;
    }

    public int releaseRequest(int requestid,int userid){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        int i = 0;//status
        String sql1 = "INSERT INTO user_request(requestid, userid,request_type)VALUES(?,?,?);";
        String sql2 = "UPDATE requests SET state = 1 WHERE requestid='"+requestid+"'";
        System.out.println(requestid);
        System.out.println(userid);
        try{
            pstmt1 = conn.prepareStatement(sql1);

            pstmt1.setInt(1,requestid);
            pstmt1.setInt(2,userid);
            pstmt1.setInt(3,2);
            pstmt2 = conn.prepareStatement(sql2);
            i = pstmt1.executeUpdate();
            pstmt2.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt1 != null)
                    pstmt1.close();
            } catch (SQLException e) {
            }
        }
        return i;
    }

    public int releaseFeedback(int feedbackid,int userid){
        Connection conn = DBConnection.getConnection();
        PreparedStatement pstmt1 = null;

        int i = 0;//status
        String sql1 = "INSERT INTO user_feedback(feedbackid, userid)VALUES(?,?);";

        System.out.println(feedbackid);
        System.out.println(userid);
        try{
            pstmt1 = conn.prepareStatement(sql1);

            pstmt1.setInt(1,feedbackid);
            pstmt1.setInt(2,userid);
            i = pstmt1.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt1 != null)
                    pstmt1.close();
            } catch (SQLException e) {
            }
        }
        return i;
    }

    public void updateInformation(int userid,String username, String message){
        Connection conn = DBConnection.getConnection();
        int i = 0;
        String sql = "UPDATE users SET username='"+username+"', message='"+message+"' WHERE userid='"+userid+"';";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            i = pstmt.executeUpdate();
            System.out.println(i);
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt != null)
                    pstmt.close();
            } catch (SQLException e) {
            }
        }
    }
}






