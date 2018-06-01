package com.liyihan;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FeedbackServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User me = new User();
        req.setCharacterEncoding("UTF-8");
        Feedback newFeed = new Feedback();

        String title = req.getParameter("feedback-title");
        String tag = req.getParameter("feedback-tag");
        String content = req.getParameter("feedback-content");
        String contact = req.getParameter("contact");
        int feedbackid = newFeed.releaseFeedback(title,tag,content,contact);

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                int userId = LoginedManager.getInstance().getIdByCookie(cookie.getValue());
                if (0 != userId&& 0!=feedbackid) {
                    me.releaseFeedback(feedbackid,userId);
                    resp.getWriter().print("success");
                }
            }
        }
        System.out.println(title);
        System.out.println(tag);
        System.out.println(content);
        System.out.println(contact);
        System.out.println(feedbackid);
        System.out.println("正在进行重定向");
        resp.sendRedirect(req.getContextPath() + "/feedback.jsp");
    }

}
