package com.liyihan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Feedback {

    private int feedbackid;
    private String title;
    private int tagid;



    private String tagname;
    private String content;
    private String contact;

    public int getFeedbackid() {
        return feedbackid;
    }

    public void setFeedbackid(int feedbackid) {
        this.feedbackid = feedbackid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTagid() {
        return tagid;
    }

    public void setTagid(int tagid) {
        this.tagid = tagid;
    }

    public String getTagname() {
        return tagname;
    }

    public void setTagname(String tagname) {
        this.tagname = tagname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTitleById(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT feedback_title FROM feedback WHERE feedbackid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                title = rs.getString("feedback_title");
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

    public int getTagIdById(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT feedback_tag FROM feedback WHERE feedbackid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                tagid = rs.getInt("feedback_tag");
            }
            return tagid;
        }catch (SQLException e){
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

    public String getContentById(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT feedback_content FROM feedback WHERE feedbackid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                content = rs.getString("feedback_content");
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

    public String getContactById(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT contact FROM feedback WHERE feedbackid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                contact = rs.getString("contact");
            }
            return contact;
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
/*
    public String getAllFeedback(){
        String result = "";
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();

            String sql = "SELECT * FROM feedback";
            rs = state.executeQuery(sql);
            //System.out.println("正在查询feedback结果集");
            while (rs.next()){
                //System.out.println("feedback累加中");
                result += rs.getInt("feedbackid");
                result += rs.getString("feedback_title");
                result += rs.getInt("feedback_tag");
                result += rs.getString("feedback_content");
                result += rs.getString("contact");
                result += "<br />";
                //System.out.println(result);
            }
            //System.out.println(result);
            return result;
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
*/
/*
    public String getFeedBack() {
        String sqlStr = "SELECT * FROM feedback;";
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        String result = "";
        try {
            state = conn.createStatement();
            rs = state.executeQuery(sqlStr);
            while (rs.next()) {
                result += "<div class=\"navigation-container\" id=\"" + rs.getInt("feedbackid") + "\">";
                result += "<label class=\"navigation-class\" >" + rs.getInt("feedback_tag") + "</label>";
                result += "<label class=\"navigation-name\">" +rs.getString("feedback_title") + "</label>";
                result += "<p class=\"navigation-content\" >" + rs.getString("feedback_content") + "</p>\n";
                result += "<label class=\"navigation-contact\">"+rs.getString("contact")+"</label>"+
                        "        </div>\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        return result;
    }
*/
    public String getTagByTagId(int id){
        Connection conn = DBConnection.getConnection();
        Statement state = null;
        ResultSet rs = null;
        try{
            state = conn.createStatement();
            String sql = "SELECT feedback_tagname FROM feedback_tags WHERE feedback_tagid='" + id + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                tagname = rs.getString("feedback_tagname");
            }
            return tagname;
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
            String sql = "SELECT feedback_tagid FROM feedback_tags WHERE feedback_tagname='" + tag + "'";
            rs = state.executeQuery(sql);
            if(rs.next()){
                tagid = rs.getInt("feedback_tagid");
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

    public List<Feedback> getAllFeedback() {
        Connection conn = DBConnection.getConnection();
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT feedbackid,feedback_title,feedback_content,feedback_tagname,contact FROM feedback JOIN feedback_tags ON feedback_tagid = feedback_tag";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()){
                Feedback f = new Feedback();
                f.setFeedbackid(rs.getInt("feedbackid"));
                f.setTitle(rs.getString("feedback_title"));
                f.setContent(rs.getString("feedback_content"));
                f.setTagname(rs.getString("feedback_tagname"));
                f.setContact(rs.getString("contact"));
                list.add(f);
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



    public int releaseFeedback(String inputTitle,  String inputTag,String inputContent,String inputContact){
        Connection conn = DBConnection.getConnection();
        int i = 0;//feedbackid
        int inputTagid = this.getTagIdByTag(inputTag);
        String sql = "INSERT INTO feedback(feedback_title,feedback_tag,feedback_content,contact)values(?,?,?,?);";
        String sql2 = "SELECT feedbackid FROM feedback WHERE feedback_title = '"+inputTitle+"' AND feedback_content = '"+inputContent+"' AND contact = '"+inputContact+"';";

        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        ResultSet rs = null;
        System.out.println(inputTitle);
        System.out.println(inputTag);
        System.out.println(inputTagid);
        System.out.println(inputContent);
        System.out.println(inputContact);
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,inputTitle);
            pstmt.setInt(2,inputTagid);
            pstmt.setString(3,inputContent);
            pstmt.setString(4,inputContact);
            i = pstmt.executeUpdate();
            conn.commit();
            pstmt2 = conn.prepareStatement(sql2);
            rs = pstmt2.executeQuery();
            if(rs.next()){
                i = rs.getInt("feedbackid");
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

}
