<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    
    <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
      
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
 <%--the below two link is used to connect javasciprt and css file in the spring mvc flow and also above include taglib core--%>
 <%--Within the webapp creates resources folder which contain two folder one is css for .css file and js for javascript files --%>
<link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">
 <script src="<c:url value="/resources/js/HomeFunctions.js" />"></script>
</head>
<body>
	
<button class="tablink" onclick="openPage('Add',this,'#66b3ff')" id="defaultOpen">Client Visit Itinerary</button>
<button class="tablink" onclick="openPage('View',this,'#66b3ff')" id="tablink2">View Client Itinerary</button>

<div id="Add" class="tabcontent">
   <div class="container">
   		<jsp:include page="AddClient.jsp"/>	
    </div>         
</div>
<!-- View Client Iternary UI -->

<jsp:include page="ViewClientIternary.jsp"/>
 <script type="text/javascript"> 
	function preventBack(){window.history.forward();} 
	setTimeout("preventBack()",0); 
	window.onunload=function(){null}; 
</script>
<script src="<c:url value="/resources/js/TabEvents.js" />"></script>


</body>
</html> 