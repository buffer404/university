package com.example.Lab2;

import javax.json.Json;
import javax.json.JsonString;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;

public class ControllerServlet extends HttpServlet {
    static long startTime = System.currentTimeMillis();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        startTime =System.currentTimeMillis();
        String x = req.getParameter("X");
        String y = req.getParameter("Y");
        String r = req.getParameter("R");
        if (x == null || y == null || r == null) {
            req.getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            System.out.println("hello world");
            req.getServletContext().getRequestDispatcher("/check").forward(req, resp);
        }
    }
}
