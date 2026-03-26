package com.digitalskies.demo.login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {

    Logger logger= LoggerFactory.getLogger(getClass());


    private final Authenticator authenticator;

    public LoginController(Authenticator authenticator) {
        this.authenticator = authenticator;
    }



    @RequestMapping(value="login",method = RequestMethod.GET)
    public String login(){
//        modelMap.put("name",name);
//        logger.debug("Name is {}",name);
        return "login";
    }

    @RequestMapping(value="login",method = RequestMethod.POST)
    public String welcome(@RequestParam String name, @RequestParam String password, ModelMap modelMap ){

        if(authenticator.isValidCredentials(name,password)){
            modelMap.put("name",name);
            return "welcome";
        }
        modelMap.put("errorMessage","Invalid credentials");
        return "login";


    }
}
