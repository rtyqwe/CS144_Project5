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
    	int origNumResultsToReturn = numResultsToReturn;
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html>" +
        		"<head></head>"+
        		"<body>");
        
        SearchResult[] results = AuctionSearchClient.basicSearch(query, 0, 0);
        
        //When number of results < numResultsToReturn
        numResultsToReturn = results.length < numResultsToReturn ? results.length : numResultsToReturn;
        if(numResultsToReturn + numResultsToSkip > results.length) 
        	numResultsToReturn = results.length - numResultsToSkip;
        
        String tmpItemID = null;
        String tmpItemName = null;
        for(int i = numResultsToSkip; i < numResultsToReturn + numResultsToSkip; i++){
        	tmpItemID = results[i].getItemId();
        	tmpItemName = results[i].getName();
        	out.println("<a href=\"item?id=" + tmpItemID + "\">" + tmpItemID + "</a>:    " + tmpItemName+"<br>");
        }
        out.println("<br>");
        
        //Pagination
        Integer nextSkipStart = numResultsToSkip + origNumResultsToReturn;
        Integer prevSkipStart = numResultsToSkip - origNumResultsToReturn;
       	int currentPageNum = numResultsToSkip/origNumResultsToReturn+1;
       	int iterPageNum = 1;
       	
       	//Previous Button
       	if(iterPageNum < currentPageNum)
       	{
       		out.println("<a href=\"search?q=" + query + "&numResultsToSkip=" + prevSkipStart.toString() +
        			"&numResultsToReturn=" + origNumResultsToReturn + "\">Previous</a>    ");
       	}
       	
       	//Page skipping
       	for(int i = 0; i < results.length; i = i+origNumResultsToReturn, iterPageNum++){
       		if(iterPageNum == currentPageNum){
       			out.println(currentPageNum + " ");
       		}
       		else {
       			out.println("<a href=\"search?q=" + query + "&numResultsToSkip=" + i +
           			"&numResultsToReturn=" + origNumResultsToReturn + "\">" + iterPageNum + 
            		"</a> ");
            }
        }
       	//Next Button
        if(results.length > numResultsToSkip + origNumResultsToReturn){
        	out.println("   <a href=\"search?q=" + query + "&numResultsToSkip=" + nextSkipStart.toString() +
        			"&numResultsToReturn=" + origNumResultsToReturn + "\">Next</a><br>");
        }
        
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
