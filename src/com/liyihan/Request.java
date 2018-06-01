package com.liyihan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Request {

    private int requestid;
    private String title;
    private String content;
    private int tagid;
    private String  tag;
    private int stateid;
    private String statename;

    public int getRequestid() {
        return requestid;
    }

    public void setRequestid(int requestid) {
        this.requestid = requestid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
    public int getStateid() {
        return stateid;
    }

    public void setStateid(int stateid) {
        this.stateid = stateid;
    }

    public String getStatename() {
        return statename;
    }

    public void setStatename(String statename) {
        this.statename = statename;
    }

    public String getTitleById(int id){
        Connection conn = DBConnection.getConnection();
        Statement state =null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT request_title FROM requests WHERE requestid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                title = rs.getString("request_title");
            }
            return title;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
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

    public String getContentById(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT request_content FROM requests WHERE requestid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                content = rs.getString("request_content");
            }
            return content;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
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

    public int getTagIdById(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT request_tag FROM requests WHERE requestid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                tagid = rs.getInt("request_tag");
            }
            return tagid;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        finally {
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
    public String getStateByStateId(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT statename FROM request_state WHERE stateid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                statename = rs.getString("statename");
            }
            return statename;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        finally {
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

    public String getTagByTagId(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT request_tagname FROM request_tags WHERE request_tagid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                tag = rs.getString("request_tagname");
            }
            return tag;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
        finally {
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

    public int getTagIdByTag(String tag){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT request_tagid FROM request_tags WHERE request_tagname='" + tag + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                tagid = rs.getInt("request_tagid");
            }
            return tagid;
        }catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        finally {
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



    public List<Request> getAllRequest() {
        Connection conn = DBConnection.getConnection();
        List<Request> list = new ArrayList<Request>();
        String sql = "SELECT requestid,request_title,request_content,request_tagname FROM requests JOIN request_tags ON request_tagid = request_tag AND state = 1";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Request r = new Request();
                r.setRequestid(rs.getInt("requestid"));
                r.setTitle(rs.getString("request_title"));
                r.setContent(rs.getString("request_content"));
                r.setTag(rs.getString("request_tagname"));
                list.add(r);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
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

    public int releaseRequest(String inputTitle, String inputContent, String inputTag){
        Connection conn = DBConnection.getConnection();
        int i = 0;
        int inputTagid = this.getTagIdByTag(inputTag);
        String sql = "INSERT INTO requests(request_title,request_content,request_tag)values(?,?,?);";
        String sql2 = "SELECT requestid FROM requests WHERE request_title = '"+inputTitle+"' AND request_content = '"+inputContent+"';";
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        System.out.println(inputTitle);
        System.out.println(inputContent);
        System.out.println(inputTag);
        System.out.println(inputTagid);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,inputTitle);
            pstmt.setString(2,inputContent);
            pstmt.setInt(3,inputTagid);
            pstmt.executeUpdate();
            conn.commit();
            pstmt2 = conn.prepareStatement(sql2);
            rs = pstmt2.executeQuery();
            if(rs.next()){
                i = rs.getInt("requestid");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                if (conn != null)
                    conn.close();
                if (pstmt != null)
                    pstmt.close();
                if (pstmt2 != null)
                    pstmt2.close();
                if (rs != null)
                    rs.close();

            } catch (SQLException e) {
            }
        }
        return i;
    }

    public void deleteRequest(int requestid){
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM requests WHERE requestid='"+requestid+"' ";
        int i = 0;
        System.out.println(requestid);
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            pstmt = conn.prepareStatement(sql);
            i=pstmt.executeUpdate();
            System.out.println(i);
            conn.commit();

        }catch (SQLException e){
            e.printStackTrace();

        }
        finally {
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
    }
}

