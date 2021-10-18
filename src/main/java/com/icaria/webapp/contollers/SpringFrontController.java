package com.icaria.webapp.contollers;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  

@Controller  
public class SpringFrontController {
	
@RequestMapping("/hello")  
    public String redirect()  
    {  
        return "viewpage";  
    }     

@RequestMapping("/helloagain")  
	public String display()  
	{  
	    return "final";  
	}  
   
}  

