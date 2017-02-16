package controller;

import data.TrainingMaterial;
import org.apache.commons.io.IOUtils;
import processing.MaterialConstructor;
import users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class BookProcessor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greeting", "Mumble mumble!");
        req.getRequestDispatcher("/WEB-INF/processBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greeting", "Mumble mumble!");
        String userName = (String) req.getAttribute("userName");
        String fileContent = IOUtils.toString(req.getPart("fileupload").getInputStream(), StandardCharsets.UTF_8);

        MaterialConstructor constructor = new MaterialConstructor(User.DEFAULT_USER);

        TrainingMaterial result = constructor.createTextWitnNewWords(fileContent);

        String text = result.getText();
        String hints = result.getDictionary().entrySet().stream()
                .map(e -> e.getKey() + " - " + e.getValue())
                .collect(Collectors.joining("<br>"));

        req.setAttribute("result", text + "<br><br>" + hints);
        req.getRequestDispatcher("/WEB-INF/processBook.jsp").forward(req, resp);
    }


}
