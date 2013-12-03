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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(true);
    	String itemId = request.getParameter("id");
    	String buy = request.getParameter("buy");
    	String itemXmlResponse = AuctionSearchClient.getXMLDataForItemId(itemId);
    	HashMap<String, Item> itemMap = new HashMap<String, Item>();
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
            request.setAttribute("buy", buy);
            itemMap = (HashMap<String, Item>) session.getAttribute("itemMap");
            if (itemMap == null) {
            	itemMap = new HashMap<String, Item>();
            }
        	itemMap.put(itemId, item);
        	session.setAttribute("itemMap", itemMap);
        	
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/item.jsp");
            dispatcher.forward(request, response);
    	}
    }
}
