package controller;

import org.apache.commons.io.IOUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static data.OutputConstractor.makeTextWithDictionary;

public class BookProcessor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("greeting", "Mumble mumble!");

        req.getRequestDispatcher("/WEB-INF/processBook.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("greeting", "Mumble mumble!");

        String fileContent = IOUtils.toString(req.getPart("fileupload").getInputStream(), StandardCharsets.UTF_8);
        String dictionary = "";

        String result = makeTextWithDictionary(fileContent, dictionary);

        req.setAttribute("result", fileContent);

        req.getRequestDispatcher("/WEB-INF/processBook.jsp").forward(req, resp);
    }


}
