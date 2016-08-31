package by.htp4.bitreight.library.controller;

import by.htp4.bitreight.library.command.Command;
import by.htp4.bitreight.library.command.CommandHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LibraryController extends HttpServlet {

    private final CommandHelper commandHelper = CommandHelper.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getServletPath().replace("/", " ").trim().replace(" ", "_").toUpperCase();//method

        Command command = commandHelper.getCommand(commandName);

        command.execute(request, response);


//        System.out.println(commandName);
//        System.out.println(request.getServletPath() + "\n-------------------");
//        System.out.println(request.getPathInfo());
    }
}
