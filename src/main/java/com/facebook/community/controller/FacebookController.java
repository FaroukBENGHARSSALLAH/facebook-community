package com.facebook.community.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.facebook.community.model.Supporter;
import com.facebook.community.service.SupporterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

@Controller
@RequestMapping(value="/")
public class FacebookController extends ConnectController {

	
    private Facebook facebook;
    private SupporterService supporterService;
    
    private ConnectionRepository connectionRepository;

    public FacebookController(SupporterService supporterService, Facebook facebook, 
    		ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
        super(connectionFactoryLocator, connectionRepository);
        this.supporterService = supporterService;
        this.facebook = facebook;
        super.setViewPath("/connect");
        
           // set redirect URL
        super.setApplicationUrl("https://facebook-community.herokuapp.com/facebook");
    }


    @RequestMapping(value="/connect/facebook")
    public String redirect(Model model)  {
		 String [] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email",
	       		"favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", 
	       		"install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name",
	       		"name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", 
	       		"significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", 
	       		"viewer_can_send_gift", "website", "work"};
    	            
                     // fetch recently logged user
               User user = facebook.fetchObject("me", User.class, fields);
               Supporter supporter = supporterService.getSupporter(user.getId());
     
                 // if user is not added in database
               if(supporter == null)
		              return "/connect/facebookConnected";
                   // skip message page
               else {
            	    model.addAttribute("supporters", supporterService.getSupporters());
		            return "community";
                  }
           }
    
    
    @RequestMapping(value="/connect")
    public String connect(Model model)  {
		            return "/connect/facebookConnect";
           }
    
    @RequestMapping(value="/connected")
    public String connected(Model model)  {
		            return "/connect/facebookConnected";
           }
    
    @RequestMapping(value="/continue")
    public String continuePage(Model model)  {
		            return "message";
           }
    
    @RequestMapping(value="/community")
    public String communityPage(Model model)  {
    	                 // fetch supporters from database
    	            model.addAttribute("supporters", supporterService.getSupporters());
		            return "community";
           }
    
    
    
    @RequestMapping(value="/facebook/facebook")
    public String redirect(Model model, @RequestParam("code") String code, @RequestParam("state") String state)  {
		            return "redirect:/facebook?code=" + code + "&state=" + state;
           }
    
    
     
    @RequestMapping(value="/community", method=RequestMethod.POST)
    public String community(Model model, @RequestParam("message") String message) throws Exception  {
    	String [] fields = { "id", "about", "age_range", "birthday", "context", "cover", "currency", "devices", "education", "email",
	       		"favorite_athletes", "favorite_teams", "first_name", "gender", "hometown", "inspirational_people", "installed", 
	       		"install_type", "is_verified", "languages", "last_name", "link", "locale", "location", "meeting_for", "middle_name",
	       		"name", "name_format", "political", "quotes", "payment_pricepoints", "relationship_status", "religion", "security_settings", 
	       		"significant_other", "sports", "test_group", "timezone", "third_party_id", "updated_time", "verified", "video_upload_limits", 
	       		"viewer_can_send_gift", "website", "work"};
    	            
    	               // fetch recently logged user
	       User user = facebook.fetchObject("me", User.class, fields);
	       Supporter supporter = supporterService.getSupporter(user.getId());
	       
	                   // if user is not added in database
	       if(supporter == null){
	           supporter = new Supporter();
	           supporter.setId(user.getId());
		       supporter.setName(user.getName());
		       supporter.setGender(user.getGender());
		       supporter.setEmail(user.getEmail());
		       supporter.setAge(calculateAge(user.getBirthday()));
		       supporter.setMessage(message);
		                    // some user doesn't set hometwon field
		       if(user.getHometown() != null){
		                   String[] latLong = getGeoLatLong(user.getHometown().getName());
		                   if(latLong != null){
					                    	supporter.setLatitude(latLong[0]);
					                    	supporter.setLongitude(latLong[1]);
		                                     }
		                     }
		       supporterService.addSupporter(supporter);
	          }
	       model.addAttribute("supporters", supporterService.getSupporters());
			       return "community";
           }
    
    
    @Override
    protected String connectedView(String providerId) {
        return "redirect:/connected";
    }
    
    @Override
    protected String connectView() {
    	return "redirect:/connect";	
	}
    
    @Override
    protected String connectView(String providerId) {
    	return "redirect:/connect";		
	}
    
   
                // calculate Lat/Long given logged user 's address, used in Google Map
    private String[] getGeoLatLong(String adress){
					        GeoApiContext context = new GeoApiContext.Builder()
					        		.apiKey("AIzaSyDg-fcT4ZY_nMXcYhFSpeMMPv1Xh6tiEzY").build();
							GeocodingResult[] results;
							try {
								results = GeocodingApi.geocode(context, adress).await();
								Gson gson = new GsonBuilder().setPrettyPrinting().create();
								return new String[]{gson.toJson(results[0].geometry.location.lat), gson.toJson(results[0].geometry.location.lng)};
							} catch (Exception e) {}
							return null;
               }
    
                  // calculate age given logged user 's birthday
    private String calculateAge(String birthDatevalue) throws Exception {
    	LocalDate startDate = LocalDate.parse(birthDatevalue, DateTimeFormatter.ofPattern("MM/dd/yyyy",
        		Locale.ENGLISH));
        LocalDate endDate = LocalDate.now();
        int age = Period.between(startDate, endDate).getYears();
        return ""+ age;
        
    }
    
    

}
