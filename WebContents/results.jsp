<html>
<head>
    <%@ page language="java" contentType="text/html" import="edu.ucla.cs.cs144.*, java.util.List, java.util.ArrayList, java.util.Iterator" %>
    <link rel="stylesheet" href="ebayStyle.css" type="text/css"></link>
</head>
<body>
    <div class="searchEngine">
        <form name="query" action="search" method="get">
            Search for keyword: 
            <input type="text" name="q" class="textbox"></input>
            <input type="hidden" name="numResultsToSkip" value="0"></input>
            <input type="hidden" name="numResultsToReturn" value="10"></input>
            <input type="submit" value="Search" class="search"></input>
        </form>
    </div>
    <ul>
        <%
        SearchResult[] results = (SearchResult[]) request.getAttribute("results");
        String tmpItemID = null;
        String tmpItemName = null;
        for(int i = 0; i < results.length; i++)
        {
            tmpItemID = results[i].getItemId();
            tmpItemName = results[i].getName();
            %> 
            <li><a href="item?id=<%=tmpItemID%>"><%=tmpItemID%></a>:    
                <%=tmpItemName%>
            <br /></li>
        <%
        }
        %>
    </ul>
    <br />
    <div style="width:800px; margin:0 auto;">
        <%
        Boolean setPrevious = (Boolean) request.getAttribute("setPrevious");
        Boolean setNext = (Boolean) request.getAttribute("setNext");
        String query = (String) request.getAttribute("query");
        Integer prevSkipStart = (Integer) request.getAttribute("prevSkipStart");
        Integer nextSkipStart = (Integer) request.getAttribute("nextSkipStart");
        Integer origNumResultsToReturn = (Integer) request.getAttribute("origNumResultsToReturn");
        Integer currentPageNum = (Integer) request.getAttribute("currentPageNum");
        
        //Previous Button
        if(setPrevious){
            %>
            <a href="search?q=<%=query%>&numResultsToReturn=<%=origNumResultsToReturn.toString()%>&numResultsToSkip=<%=prevSkipStart.toString()%>">Previous</a>
        <%
        }

        //Page skipping
        Integer IterPageNum = 1;
        Integer lengthAllResults = (Integer) request.getAttribute("lengthAllResults");
        for(int i = 0; i < lengthAllResults; i = i+origNumResultsToReturn, IterPageNum++){
            if(IterPageNum == currentPageNum){
            %>
            <%=currentPageNum%> 
            <%
            }
            else {
            %>
            <a href="search?q=<%=query%>&numResultsToSkip=<%=i%>&numResultsToReturn=<%=origNumResultsToReturn%>"><%=IterPageNum.toString()%></a>
            <%
            }
        }

        //Next button
        if(setNext){
            %>
            <a href="search?q=<%=query%>&numResultsToReturn=<%=origNumResultsToReturn.toString()%>&numResultsToSkip=<%=nextSkipStart.toString()%>">Next</a>
        <%
        }
        %>
    </div>
</body>
</html>