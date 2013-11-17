package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	PrintWriter out = response.getWriter();
    	String itemId = request.getParameter("itemId");
    	response.setContentType("text/html");
    	out.println(itemId);
    	String itemXmlResponse = AuctionSearchClient.echo(itemId);
    	
    	out.println(itemXmlResponse);
    }
}
