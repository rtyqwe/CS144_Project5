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
import java.util.Date;
import javax.servlet.RequestDispatcher;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.HashMap;

public class PurchaseServlet extends HttpServlet implements Servlet {
       
    public PurchaseServlet() {}
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(true);
    	String id = request.getParameter("id");
    	
    	HashMap<String, Item> itemMap = (HashMap<String, Item>) session.getAttribute("itemMap");
    	Item previousItem = itemMap.get(id);
    	
    	if (previousItem == null) {
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    		return;
    	}
    	request.setAttribute("item", previousItem);
    	request.setAttribute("buy", "true");
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/item.jsp");
        dispatcher.forward(request, response);
        return;
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	HttpSession session = request.getSession(true);

    	HashMap<String, Item> itemMap = (HashMap<String, Item>) session.getAttribute("itemMap");
    	String id = request.getParameter("id");
    	Item previousItem = itemMap.get(id);
    	if (previousItem == null) {
    		request.getRequestDispatcher("/error.jsp").forward(request, response);
    		return;
    	}
    	
    	String ccn = request.getParameter("ccn");
    	long purchaseTime = session.getLastAccessedTime();
    	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy 'at' HH:mm:ss z");
    	String time = formatter.format(purchaseTime);
    	
        request.setAttribute("item", previousItem);
        request.setAttribute("ccn", ccn);
        request.setAttribute("purchaseTime", time);
        itemMap.remove(id);
        session.setAttribute("itemMap", itemMap);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/confirm.jsp");
        dispatcher.forward(request, response);
        return;
    }
}
