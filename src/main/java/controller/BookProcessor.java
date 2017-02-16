package controller;

import data.TrainingMaterial;
import org.apache.commons.io.IOUtils;
import processing.MaterialConstructor;
import processing.TranslatorYandex;
import users.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class BookProcessor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greeting", "Mumble mumble!");
        req.getRequestDispatcher("/WEB-INF/processBook.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = (String) req.getAttribute("userName");
        String fileContent = IOUtils.toString(req.getPart("fileupload").getInputStream(), StandardCharsets.UTF_8);

        MaterialConstructor constructor = new MaterialConstructor(User.DEFAULT_USER, new TranslatorYandex());

        TrainingMaterial result = constructor.createTextWitnNewWords(fileContent);

        String text = result.getText();
        LinkedHashMap<String, String> hints = result.getDictionary();

        req.setAttribute("resultText", text);
        req.setAttribute("resultHints", hints);
        req.getRequestDispatcher("/WEB-INF/processBook.jsp").forward(req, resp);
    }


}
