package edu.ucla.cs.cs144;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProxyServlet extends HttpServlet implements Servlet {
       
    public ProxyServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String query = request.getParameter("q");
        HttpURLConnection connection = null;
        URL serverAddress = null;
        BufferedReader rd = null;
        StringBuilder sb = null;
        String line = null;
        
        response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        
        try{
        	serverAddress = new URL("http://google.com/complete/search?output=toolbar&q="+query);
        	connection = (HttpURLConnection) serverAddress.openConnection();
        	connection.setRequestMethod("GET");
        	connection.setDoOutput(true);
        	connection.setReadTimeout(10000);
        	connection.connect();
        	
        	rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        	sb = new StringBuilder();
        	while((line = rd.readLine()) != null){
        		sb.append(line + '\n');
        	}
        	
        	out.println(sb.toString());
        	
        } catch (MalformedURLException e){
        	e.printStackTrace();
        } catch (ProtocolException e){
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
        finally
        {
        	connection.disconnect();
        	rd = null;
        	sb = null;
        	connection = null;
        }
        
    }
}
