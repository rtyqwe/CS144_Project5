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
import javax.servlet.RequestDispatcher;

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
        
        SearchResult[] results = AuctionSearchClient.basicSearch(query, 0, 0);
        Integer lengthAllResults = results.length;
        
        //When number of results < numResultsToReturn
        numResultsToReturn = results.length < numResultsToReturn ? results.length : numResultsToReturn;
        if(numResultsToReturn + numResultsToSkip > results.length) 
        	numResultsToReturn = results.length - numResultsToSkip;
        
        out.println("<ul>");
        SearchResult[] resultsToForward = Arrays.copyOfRange(results, numResultsToSkip, numResultsToSkip+numResultsToReturn);
        request.setAttribute("results", resultsToForward);
      
        //Pagination
        Integer nextSkipStart = numResultsToSkip + origNumResultsToReturn;
        Integer prevSkipStart = numResultsToSkip - origNumResultsToReturn;
       	Integer currentPageNum = numResultsToSkip/origNumResultsToReturn+1;
       	Integer iterPageNum = 1;
       	
       	Boolean setPrevious = false;
       	//Previous Button
       	if(iterPageNum < currentPageNum)
       	{
       		setPrevious = true;
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
       	
        Boolean setNext = false;
       	//Next Button
        if(results.length > numResultsToSkip + origNumResultsToReturn){
        	setNext = true;
        }
        
        request.setAttribute("setPrevious", setPrevious);
        request.setAttribute("setNext", setNext);
       	request.setAttribute("query", query);
       	request.setAttribute("prevSkipStart", prevSkipStart);
       	request.setAttribute("nextSkipStart", nextSkipStart);
       	request.setAttribute("origNumResultsToReturn", origNumResultsToReturn);
       	request.setAttribute("currentPageNum", currentPageNum);
       	request.setAttribute("lengthAllResults", lengthAllResults);
        String nextJSP = "/results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
        
    }
}
