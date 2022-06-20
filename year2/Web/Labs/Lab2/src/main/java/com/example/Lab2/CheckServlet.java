package com.example.Lab2;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CheckServlet extends ControllerServlet{
    Model model = new Model();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer x = null;
        Double y = null;
        Double r = null;
        try {
            x = Integer.parseInt(request.getParameter("X").trim());
            System.out.println("parsed x = " + x);
        } catch (NumberFormatException e) {
            System.out.println("Failed while parsing x.\n Redirected to controller without x-parameter");
            response.sendRedirect("/index");
        }
        try {
            y = Double.parseDouble(request.getParameter("Y").trim().replaceAll(",", "."));
            System.out.println("parsed y = " + y);
        } catch (NumberFormatException e) {
            System.out.println("Failed while parsing y.\n Redirected to controller without y-parameter");
            response.sendRedirect("/index");
        }
        try {
            r = Double.parseDouble(request.getParameter("R").trim().replaceAll(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Failed while parsing r.\n Redirected to controller without r-parameter");
            response.sendRedirect("/index");
        }
        try (PrintWriter out = response.getWriter()) {
            if (x != null && y != null && r != null) {
                Coordinates coordinates = model.getCoordinates(x,y,r);
                out.println(JsonPharser.toJSON(coordinates));
            }
        }
    }
}
