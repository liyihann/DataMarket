package com.liyihan;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReleaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("Release doPost");
        //Request newReq = new Request();
        User me = new User();
        Request newReq = new Request();

        String title = req.getParameter("requirement-title");
        String content = req.getParameter("requirement-content");
        String tag = req.getParameter("requirement-tag");
        int requestid = newReq.releaseRequest(title,content,tag);

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                int userId = LoginedManager.getInstance().getIdByCookie(cookie.getValue());
                if (0 != userId&& 0!=requestid) {
                    me.releaseRequest(requestid,userId);
                    resp.getWriter().print("success");
                }
            }
        }
        System.out.println(title);
        System.out.println(content);
        System.out.println(tag);
        System.out.println(requestid);
        System.out.println("正在进行重定向");
        resp.sendRedirect("RequestServlet");
    }

}