$("#defaultOpen").click();

$('.dropdown-submenu a.test').on("click", function(e){
		var event1=($(this).text());
		 sessionStorage.setItem("event1",$(this).text());
	    $(this).next('ul').toggle();
	    e.stopPropagation();
	  });
	$('.dropdown-menu a.test2').on("click", function(e){
		   sessionStorage.setItem("event1",sessionStorage.getItem("event1")+" : "+($(this).text()));
		   document.getElementById("id2").innerHTML=sessionStorage.getItem("event1");
		   document.getElementById("id1").innerHTML="";
		  
	});
	
	$("#tablink2").click(function(){
	    $.ajax({ //starts here
	        url : 'view',
	        success : function(data) {
	        	var clientList="";
	        	 $.each(data, function(index, obj) {
	        		 //A common use of JSON is to exchange data to/from a web server.
	        		 //When sending data to a web server, the data has to be a string.
	        		 //Convert a JavaScript object into a string with JSON.stringify().	        		 
	        		 var myJSON = JSON.stringify(obj.arrivalDate);
	                 clientList=clientList+"<tr>";
	                 clientList=clientList+"<td>"+obj.clientName +"</td>";
	                 clientList=clientList+"<td>"+ obj.projectName +"</td>";
	                 clientList=clientList+"<td>"+ obj.stringarrive +" </td>";
	                 clientList=clientList+"<td> "+ obj.stringdepart +"</td>";
	                 clientList=clientList+"<td>"+ obj.agenda +"</td>";
	                 clientList=clientList+"<td>"+ obj.account +"</td>";
	                 clientList=clientList+"<td><a  href='http://localhost:2998/EventBasedCalendar/viewClientEvent/"+obj.clientId+"'>View Events</a></td>";
	                 clientList=clientList+"</tr>";
	                sessionStorage.clear();
	                
	             }); 
	        	  
	        	 $('#clientTab tbody').html(clientList);
	        }
	    });//end starts
	});
	
	
	function fetchEvents(cId){
	    alert(cId);
	    
	}
	
	$('#starttime').focusout(function(e){
		 var str1 = $('#starttime').val();
		 //console.log("str1 = "+str1);
		 sessionStorage.setItem("starttime",str1); 
		 console.log(starttime);
	 });
	 
	 $('#endtime').focusout(function(e){
		 var str2 = $('#endtime').val();
		 //console.log("str2 = "+str2);
		 sessionStorage.setItem("endtime",str2);
		 console.log(endtime);
	 });
	 $("#event").click(function(){
		 var arrivalDate=$('#startdate').val();
		 
		 var eventStartDate=$('#starttime').val();
		 
// 		 var now=$('#enddate').val();
// 		 now.setDate(now.getDate()+30);
// 		 alert(now);

		 
		 var valid1=arrivalDate<=eventStartDate;
		 console.log("Arrival compare "+valid1);
		
		 var DepartureDate=$('#enddate').val();
		 var eventEndDate=$('#endtime').val();
		 
		 var deptDate=new Date(DepartureDate);
		 var eventDate=new Date(eventEndDate);
		 
		 var valid2=deptDate>=eventDate;
		 console.log("departute  compare "+valid2);
		 
		var flag1=eventStartDate<eventEndDate;
		console.log("flag1 "+flag1);
		
		if(valid1)
		{
			if(valid2)
				{
					if(flag1)
					{
			
			var previousData=sessionStorage.getItem("text");
			var newData;
			var newData2;
			
			
			if(previousData=="" || previousData==null){
				newData="<tr><td>"+sessionStorage.getItem("event1") +"</td><td>"+sessionStorage.getItem("starttime") +" </td><td>"+
				sessionStorage.getItem("endtime")+"</td></tr>";
				
				newData2=sessionStorage.getItem("event1") +","+sessionStorage.getItem("starttime") +" ,"+
				sessionStorage.getItem("endtime")+"\n";
			}
			else{
				newData=sessionStorage.getItem("tabdata")+"<tr><td>"+sessionStorage.getItem("event1") +"</td><td>"+sessionStorage.getItem("starttime") +" </td><td>"+
				sessionStorage.getItem("endtime")+"</td></tr>";
				
				newData2=sessionStorage.getItem("text")+sessionStorage.getItem("event1") +","+sessionStorage.getItem("starttime") +","+
				sessionStorage.getItem("endtime")+"\n";
			}
			
			
			$("#tab").html(newData);
			$("#textarea").val(newData2);
			
			ClearFields();
			}
		else
			alert("Start Event date must be less than End Event Date");
				}
			else
				alert("Event end Date Should be less than or Equal to Departure Date");
			}
		else
			alert("Event Start Date Should be greater than or Equal to Arrival Date");
		
			return false;
			
		});
	
		function ClearFields() {
			if(($("#textarea").val())!=null)
				{
				var textdataForField=$("#textarea").val();
				var tabdata=$("#tab").html();
				sessionStorage.setItem("text",textdataForField);
				sessionStorage.setItem("tabdata",tabdata);
				
				}
		    $("#starttime").val("");
		    $("#endtime").val(""); 
		    
		}
function DateCheck()
{
	var StartDate=document.getElementById('startdate').value;
	var EndDate=document.getElementById('enddate').value;
	var eDate=new Date(EndDate);
	var sDate=new Date(StartDate);
	if(StartDate!='' && StartDate!='' && sDate>eDate)
		{
			alert("Please ensure arrival date is before departure date");
			return false;
			
		}
}
