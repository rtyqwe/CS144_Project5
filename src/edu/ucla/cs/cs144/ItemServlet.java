package edu.ucla.cs.cs144;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	String itemId = request.getParameter("id");
    	String itemXmlResponse = AuctionSearchClient.getXMLDataForItemId(itemId);
    	
    	Item item = null;
    	try {
    		JAXBContext jc = JAXBContext.newInstance(Item.class);
    		Unmarshaller unmarshaller = jc.createUnmarshaller();
    		InputStream input = new ByteArrayInputStream(itemXmlResponse.getBytes("UTF-8"));
    		item = (Item) unmarshaller.unmarshal(input);

    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
    	

    	if (item == null) {
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    	}
    	else {
            request.setAttribute("item", item);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/item.jsp");
            dispatcher.forward(request, response);
    	}
    }
}
