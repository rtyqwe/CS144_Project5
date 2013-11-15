package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String query = request.getParameter("q");
    	int numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip"));
    	int numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn"));
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>" +
        		"<head></head>"+
        		"<body>");
        
        SearchResult[] results = AuctionSearchClient.basicSearch(query, numResultsToSkip, numResultsToReturn);
        
        String tmpItemID = null;
        String tmpItemName = null;
        for(int i = 0; i < results.length; i++){
        	tmpItemID = results[i].getItemId();
        	tmpItemName = results[i].getName();
        	out.println("<a href=\"item?id=" + tmpItemID + "\">" + tmpItemID + "</a>    " + tmpItemName+"<br>");
        }
        out.println("</body></html>");
    }
}
