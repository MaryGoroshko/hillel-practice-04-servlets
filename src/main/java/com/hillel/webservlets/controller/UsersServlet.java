package com.hillel.webservlets.controller;

import com.hillel.webservlets.UserDAO.UsersDB;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

//@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private UsersDB db;

    @Override
    public void init(ServletConfig config) throws ServletException {
        db = UsersDB.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = (String) req.getSession().getAttribute("login");
        Optional<Role> userRole = db.getRole(login);

        if (userRole.isPresent() && userRole.get().equals(Role.ROLE_ADMIN)) {
            req.getSession().setAttribute("user_role", Role.ROLE_ADMIN);
        }
        if (userRole.isPresent() && userRole.get().equals(Role.ROLE_SUPPORT)) {
            req.getSession().setAttribute("user_role", Role.ROLE_SUPPORT);
        }
        req.setAttribute("access_admin", Role.ROLE_ADMIN);
        req.setAttribute("access_support", Role.ROLE_SUPPORT);

        req.setAttribute("users", db.getInMemoryDB());
        req.getRequestDispatcher("/users.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter writer = resp.getWriter();

        String userLogin = (String) req.getSession().getAttribute("login");
        Optional<Role> userRole = db.getRole(userLogin);

        String deleteUser = req.getParameter("delete-user");
        Optional<Role> deleteUserRole = db.getRole(deleteUser);

        if (userRole.isPresent() && userRole.get().equals(Role.ROLE_ADMIN)) {
            req.setAttribute("access_admin", Role.ROLE_ADMIN);
            req.setAttribute("users", db.getInMemoryDB());
            if (deleteUserRole.isPresent() && deleteUserRole.get().equals(Role.ROLE_SUPPORT)
                    || deleteUserRole.isPresent() && deleteUserRole.get().equals(Role.ROLE_USER)) {
                db.removeUser(deleteUser);
                writer.print("<div style=\"color:gray;\" text-align: center;\">" +
                        "User: <span style=\"color:red;\">" + deleteUser + "</span> has been deleted</div>");
            } else {
                writer.print("<div style=\"color:Tomato;\" text-align: center;\">" +
                        "You can't delete user with this role</div>");
            }
            req.getRequestDispatcher("users.jsp").include(req, resp);

        } else if (userRole.isPresent() && userRole.get().equals(Role.ROLE_SUPPORT)) {
            req.setAttribute("access_support", Role.ROLE_SUPPORT);
            req.setAttribute("users", db.getInMemoryDB());
            if (deleteUserRole.isPresent() && deleteUserRole.get().equals(Role.ROLE_USER)) {
                db.removeUser(deleteUser);
                writer.print("<div style=\"color:gray;\" text-align: center;\">" +
                        "User: <span style=\"color:red;\">" + deleteUser + "</span> has been deleted</div>");
            } else {
                writer.print("<div style=\"color:Tomato;\" text-align: center;\">" +
                        "You can't delete user with this role</div>");
            }
            req.getRequestDispatcher("users.jsp").include(req, resp);
        }
    }
}
