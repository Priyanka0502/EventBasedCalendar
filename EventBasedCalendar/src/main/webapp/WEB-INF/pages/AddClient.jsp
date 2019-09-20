<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
    
    <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
      


<link href="<c:url value="/resources/css/style1.css" />" rel="stylesheet">
<form:form action="${pageContext.request.contextPath}/submitOnDb" method="post"  modelAttribute="cObj" >
            <c:if test="${msg ne null }">
			   <h4 align="center" style="color:#66b3ff;">${msg}</h4>
			</c:if>
            <div class="row">
               <div class="col-25">
                  <label for="fname">Client Name</label>
                 </div>
                 <div class="col-75">
                    <form:input path="clientName" type="text" id="cname" name="clientname" placeholder="client name.."/><br>
                    <form:errors path="clientName" style="color:red"></form:errors>
                  </div>
              </div>
              <div class="row">
                <div class="col-25">
                   <label for="lname">Project Name</label>
                 </div>
                    <div class="col-75">
                       <form:input path="projectName" type="text" id="pname" name="projectname" placeholder="project name.."/><br>
                       <form:errors path="projectName" style="color:red"></form:errors>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                       <label >Arrival date</label>
                    </div>
                    <div class="col-75">
                       <form:input path="arrivalDate" type="date" id="startdate"/><br>
                       <form:errors path="arrivalDate" style="color:red"></form:errors>
                    </div>
                 </div>
                <div class="row">
                   <div class="col-25">
                      <label >End date</label>
                   </div>
                   <div class="col-75">
                      <form:input path="deptDate" type="date" id="enddate"/><br>
                      <form:errors path="deptDate" style="color:red"></form:errors>
                   </div>
                </div>
                <div class="row">
                  <div class="col-25">
                     <label for="agenda">Agenda</label>
                  </div>
                  <div class="col-75">
                     <form:input path="agenda" type="text" id="agenda" name="agenda" placeholder="Enter Agenda" onclick="DateCheck()"/><br>
                     <form:errors path="agenda" style="color:red"></form:errors>
                  </div>
                </div>
                 <div class="row">
                  <div class="col-25">
                     <label for="agenda">Account</label>
                  </div>
                  <div class="col-75">
                  
                  <form:select path="account" >
            	       <form:option value="CITI" >CITI</form:option>
                	   <form:option value="Bank of Montreal">Bank of Montreal</form:option>
                  		<form:option value="Bank of Canada">Bank of Canada</form:option>
                  		<form:option value="HDFC">HDFC</form:option>
                  		<form:option value="Others">Others</form:option>
                   </form:select>
                  
                  </div>
                </div>
				<hr></hr>
                <div class="row">
                   <div class="col-25">
                       <label >Event</label>
                    </div> 
                    <!-- Our div for dropdown box starts here -->
                    <div class="col-75">
                       <div class="dropdown">
    					  <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"><p id="id1">Select</p>
    					  <span class="caret"></span> <p id="id2"></p> </button>
    			   <ul class="dropdown-menu">
   							 <li class="dropdown-submenu">
        						<a class="test" tabindex="-1" href="#">Meal <span class="caret"></span></a>
        						<ul class="dropdown-menu">
         							 <li><a class="test2" tabindex="-1" href="#">Unit 1 Cafeteria</a></li>
          							<li><a class="test2" tabindex="-1" href="#">Unit 2 Cafeteria</a></li>
       							 </ul>
     						</li>
     						<li class="dropdown-submenu">
        						<a class="test" tabindex="-1" href="#">Hotel <span class="caret"></span></a>
        						<ul class="dropdown-menu">
         							 <li><a class="test2"  tabindex="-1" href="#">FabSunshine Greater Noida</a></li>
          							<li><a class="test2"  tabindex="-1" href="#">Treebo Near Advant Park</a></li>
          							<li><a class="test2" tabindex="-1" href="#">Other</a></li>
       							 </ul>
     						</li>
      						<li class="dropdown-submenu">
        						<a class="test" tabindex="-1" href="#">Cab <span class="caret"></span></a>
        						<ul class="dropdown-menu">
         							 <li><a class="test2" tabindex="-1" href="#">Airport to Hotel</a></li>
          							<li><a class="test2"  tabindex="-1" href="#">Hotel to Airport</a></li>
         							 <li><a class="test2"  tabindex="-1" href="#">Hotel to Office</a></li>
         							 <li><a class="test2" tabindex="-1" href="#">Office to Hotel</a></li>
       							 </ul>
     						</li>
      						<li class="dropdown-submenu">
        						<a class="test" tabindex="-1" href="#">Meeting <span class="caret"></span></a>
        							<ul class="dropdown-menu">
          									<li><a class="test2"  tabindex="-1" href="#">Conclave</a></li>
         									 <li><a class="test2" tabindex="-1" href="#">Focus</a></li>
          									<li><a class="test2" tabindex="-1" href="#">Aspire</a></li>
        							</ul>
     						 </li>
                     </ul>
  				   </div>             	
                 </div>
               </div>
                  <div class="row">
                      <div class="col-25">
                         <label>Event start date And time</label>
                       </div>
                      <div class="col-75">
                         <input type="datetime-local" name="starttime" id="starttime" >
                      </div>
                  </div>
                  <div class="row">
                     <div class="col-25">
                        <label>Event end  date And time</label>
                     </div>
                     <div class="col-75">
                        <input type="datetime-local" name="endtime" id="endtime" >
                     </div>
                  </div>
                  <br>
                  <div class="row" align="center">
                    <input type="submit" value="Add Event" id="event" >
                  </div>
                  <hr></hr>
                  <div class="col-25">
                    <label>View Events</label>
                  </div >
             
                   <form:hidden  id="textarea" rows="4" cols="50" path="eventData" />
                   
			       <table id="tab" border="1">
			       </table>
				   <br></br>
				   <br></br>
                  <div class="row" align="center">
                    <input type="submit" value="Submit">
                  </div>
          </form:form>     
