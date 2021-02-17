package servlets;

import account.AccountService;
import account.AccountServiceImp;
import dbService.dataSets.UsersDataSet;
import templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SessionsServlet extends HttpServlet {
    private final AccountService accountService;

    public SessionsServlet(AccountServiceImp accountService) {
        this.accountService = accountService;
    }


    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login == null || pass == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UsersDataSet profile = accountService.getUserByLogin(login);

        if (profile == null && !profile.getPassword().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            System.out.println("Status code (401)");
            response.getWriter().println("Unauthorized");
            return;
        }
        Map<String, Object> data = new HashMap<>();
        data.put("login", login);
        response.getWriter().println(PageGenerator.instance().getPage("chat.html", data));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
