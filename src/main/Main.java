package main;

import account.AccountServiceImp;
import chat.WebSocketChatServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SessionsServlet;
import servlets.UsersServlet;


public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(4084);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        AccountServiceImp accountServiceImp = new AccountServiceImp();

        context.addServlet(new ServletHolder(new WebSocketChatServlet()), "/chat");
        context.addServlet(new ServletHolder(new UsersServlet(accountServiceImp)), "/signup");
        context.addServlet(new ServletHolder(new SessionsServlet(accountServiceImp)), "/signin");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
