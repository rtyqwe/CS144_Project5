<% String query = (String) request.getAttribute("query"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"charset>
    <%@ page language="java" contentType="text/html" import="edu.ucla.cs.cs144.*, java.util.List, java.util.ArrayList, java.util.Iterator" %>
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="item.css" />
    <link rel="stylesheet" href="ebayStyle.css" type="text/css"></link>
    <script type="text/javascript" src="autosuggest.js"></script>
    <script type="text/javascript" src="provider.js"></script>
    <script type="text/javascript">
            window.onload = function () {
                var oTextbox = new AutoSuggestControl(document.getElementById("txt1"), new SuggestionProvider()); 
            }
        </script>
        <title><%=query%> - Keyword Search</title>
</head>
<body>
    <div class="item-search navbar navbar-default" role="navigation">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      
    <form class="navbar-form navbar-left" role="search" name="query" action="search" method="get">
        <label>Search for keyword: </label>
        <div class="form-group">
            <input type="text" class="form-horizontal" name="q" id="txt1"></input>
        </div>
        <input type="hidden" name="numResultsToSkip" value="0"></input>
        <input type="hidden" name="numResultsToReturn" value="10"></input>
        <input type="submit" value="Search" class="btn btn-default"></input>
        </form>
  </div>
  </div>
   <br />
    <ul>
        <%
        SearchResult[] results = (SearchResult[]) request.getAttribute("results");
        String tmpItemID = null;
        String tmpItemName = null;
        if(results.length == 0) {
            %>No results.<%
        }   
        else {
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
        }
        %>
    </ul>
    <br />
    <div style="width:800px; margin:0 auto;">
        <%
        Boolean setPrevious = (Boolean) request.getAttribute("setPrevious");
        Boolean setNext = (Boolean) request.getAttribute("setNext");
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