<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: silverwing
  Date: 10.07.19
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <link rel="stylesheet" href="example.css"/>
  </head>
  <body>
    <form>
      <input type="text" name="query" value="<%=request.getAttribute("query") != null ? request.getAttribute("query") : ""%>"/>
      <input type="submit"/>
    </form>
    <%
      JSONObject data = (JSONObject) request.getAttribute("data");
      if (data != null) {
        JSONArray items = data.getJSONArray("items");

        for (Object item: items) {
          JSONObject i = (JSONObject) item;

          Date date = new Date(i.getLong("creation_date")*1000);

          out.print("<div>");
          out.print("<a href=\"" + i.getString("link") + "\">" + i.getString("title") + "</a>");
          out.print("<span class=\"author\">" + i.getJSONObject("owner").getString("display_name") + "</span>");
          out.print("<span class=\"date\">" + date.toString() + "</span>");
          out.print("</div>");
        }
      }
    %>


  </body>
</html>
