package ro.teamnet.zth.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Cosmin.Adamut on 7/19/2017.
 */
public class HttpSessionLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String user, password;
        Cookie[] cookies = req.getCookies();
        user = req.getParameter("username");
        password = req.getParameter("password");
        if (user.equals("admin") && password.equals("admin")) {
            PrintWriter printWriter = resp.getWriter();
             printWriter.write("Welcome back " + user);
            /*if (cookies != null) {
                for (Cookie cookie : cookies) {
                    printWriter.write("["+cookie.getName() + "]\n[" + cookie.getValue()+ "]\n");
                }
            }*/
        }else{
            req.getSession().setAttribute("user",user);
            req.getSession().setAttribute("session",req.getSession());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/loginFail.jsp");
            requestDispatcher.forward(req, resp);
        }

    }


}
