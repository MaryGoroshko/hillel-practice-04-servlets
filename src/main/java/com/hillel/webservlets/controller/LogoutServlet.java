package com.hillel.webservlets.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        session.setAttribute("login", null);

        req.getSession().invalidate();

        req.getRequestDispatcher(req.getContextPath() + "index.jsp").include(req, resp);//после того как заменила путь на "/" - теперь не работает

        out.print("<div style=\"color:Tomato;\" text-align: center;\">" +
                "You are successfully logged out!</div>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(req.getContextPath() + "/").forward(req, resp);
    }
}
