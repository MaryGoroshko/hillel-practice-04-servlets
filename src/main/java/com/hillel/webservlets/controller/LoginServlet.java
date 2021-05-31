package com.hillel.webservlets.controller;

import com.hillel.webservlets.UserDAO.UsersDB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsersDB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        db = UsersDB.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        final HttpSession session = req.getSession();

        PrintWriter writer = resp.getWriter();
        String colorText = "<div style=\"color:Tomato;\" text-align: center;\">";

        if (login.isEmpty()) {
            writer.print(colorText + "Login is empty</div>");
            req.getRequestDispatcher(req.getContextPath() + "index.jsp").include(req, resp);
        } else if (password.isEmpty()) {
            writer.print(colorText + "Password is empty</div>");
            req.getRequestDispatcher(req.getContextPath() + "index.jsp").include(req, resp);
        } else if (db.getUserByCredentials(login, password).isEmpty()) {
            writer.print(colorText + "Login or password incorrect. Try again.</div>");
            req.getRequestDispatcher(req.getContextPath() + "index.jsp").include(req, resp);
        } else if (db.getUserByCredentials(login, password).isPresent()) {

            session.setAttribute("login", login);
            req.setAttribute("users", db.getInMemoryDB());
            resp.sendRedirect(req.getContextPath() + "/users");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("login") == null || session.getAttribute("login") == "") {
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } else if (session.getAttribute("login") != null) {
            resp.sendRedirect(req.getContextPath() + "/users");
        }
    }
}
