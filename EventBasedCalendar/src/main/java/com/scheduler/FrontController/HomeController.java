package com.scheduler.FrontController;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.daos.ClientDao;
import com.daos.EventDao;
import com.entities.Client;
import com.entities.Events;



@Controller
public class HomeController {
	@Autowired
	ClientDao clientDao;
	
	@Autowired
	EventDao eventDao;
	
	@RequestMapping(value="/" , method=RequestMethod.GET)
	public ModelAndView Print(ModelMap map){
		System.out.println("I m in Preform");
		
		ModelAndView mv=new ModelAndView("HomePage");
		Client cObj=new Client();
		mv.addObject("cObj",cObj);   
		return mv;
	}
	
	@RequestMapping(value="/submitOnDb" , method=RequestMethod.POST)
	public ModelAndView submitClient(@Valid @ModelAttribute ("cObj")Client cObj,BindingResult result){
	
		ModelAndView mv;
		if(result.hasErrors())
		{
			mv=new ModelAndView("HomePage");
			mv.addObject("msg","There is some problem..Please fill the Details");
			return mv;
		}
		try{
			//taking reference variable of clients..
			Client clientNew=null;
			int flag=0;
			List<Client>  clientList=clientDao.getAllClient();
			for(Client c:clientList)
			{
				if((c.getClientName().equals(cObj.getClientName()))/*&&(c.getArrivalDate().equals(cObj.getArrivalDate()))&&(c.getDeptDate().equals(cObj.getDeptDate()))&&(c.getAgenda().equals(cObj.getAgenda()))&&(c.getAccount().equals(cObj.getAccount()))*/)
				{	
						int cIdPrevious=c.getClientId();
						clientNew=clientDao.getClientById(cIdPrevious);
						flag=1;
						break;
				
				}
				
			}
			
			if(flag==0)
		 clientDao.addClient(cObj);	
			
		String eventData=cObj.getEventData();
		System.out.println(eventData);
		String events[]=eventData.split("\n");
		for(String event:events){
			String events2[]=event.split(",");
			Events eObj=new Events();
			if(flag==1)
			eObj.setCli(clientNew);
			else
				eObj.setCli(cObj);
				
			eObj.setTitle(events2[0]);
		
			events2[1]=events2[1].replace("T"," ");
			events2[2]=events2[2].replace("T"," ");
			
			System.out.println("start Date and Time"+events2[1]);
			System.out.println("end Date and Time"+events2[2]);
			
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm");
			Date startDateTime =sdf.parse(events2[1]);
			Date endDateTime =sdf.parse(events2[2]);
			
			eObj.setStart(startDateTime);
			
			
			eObj.setEnd(endDateTime);

			eventDao.addEvent(eObj);
			
		}
		}catch(Exception e){
			e.printStackTrace();

		}
		mv=new ModelAndView("HomePage");
		mv.addObject("msg","Client Itinerary Successfully");
		return mv;
		
	}
	//@RequestMapping annotation is used for mapping web requests to particular handler classes or handler methods.
	//@ResponseBody is a Spring annotation which binds a method return value to the web response body.
		//It is not interpreted as a view name. It uses HTTP Message converters 
		//to convert the return value to HTTP response body, based on the content-type in the request HTTP header.
	@RequestMapping(value="/view" , method=RequestMethod.GET)
	public @ResponseBody List<Client> viewClient(ModelMap map){
			List<Client>  clientList=clientDao.getAllClient();
			for(Client client:clientList)
			{
				Date arrvdt=client.getArrivalDate();
				Date deptdt=client.getDeptDate();
				DateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd");
				String strDate1=dateformat.format(arrvdt);
				String strDate2=dateformat.format(deptdt);
				client.setStringarrive(strDate1);
				client.setStringdepart(strDate2);
			
				
			}
			map.addAttribute("clients",clientList);
			return clientList;
		
	}

@RequestMapping(value="/deleteEvent/{eventId}/{clientId}",method=RequestMethod.GET)
	public ModelAndView deleteEvent(@PathVariable int eventId,ModelMap map,@PathVariable int clientId)
	{
		eventDao.deleteEvent(eventId);
		Client c=clientDao.viewClient(clientId);
		ModelAndView mv=new ModelAndView("EventView");
		mv.addObject("clientName",c.getClientName());
		List<Events> eventList=eventDao.viewAllEventsById(clientId);
		mv.addObject("EventsList", eventList);
		
		return mv;
	}

	@Autowired
	HttpSession session;

	@RequestMapping(value="/viewClientEvent/{clientId}" , method=RequestMethod.GET)
	public String viewClientEvent(@PathVariable int clientId){
		System.out.println("Hello 1"+clientId);
		session.setAttribute("clientId",clientId);
		
		return "cal";
	}
	
	@RequestMapping(value="/viewAllEvents" , method=RequestMethod.GET)
	public @ResponseBody List<Events> viewAllEvents(){
		
		int clientId=(Integer)session.getAttribute("clientId");
		List<Events> eventList=eventDao.viewAllEventsById(clientId);
	
		return eventList;
	}
	
}
