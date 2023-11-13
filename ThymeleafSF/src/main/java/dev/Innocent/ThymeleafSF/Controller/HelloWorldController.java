package dev.Innocent.ThymeleafSF.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // Need a controller method to show initial HTML form
    @RequestMapping("/showForm")
    public String showForm(){
        return "helloworld-form";
    }

    // Need a controller method to process the HTML form
    @RequestMapping("/processForm")
    public String processForm(){
        return "helloworld";
    }

    // Need a controller method to read form data and  add data to the model
    @RequestMapping("/processFormVersionTwo")
    public String letsShoutDude(HttpServletRequest request, Model model){

        // Read the request parameter from the HTML form
        String theName = request.getParameter("studentName");
        // Convert the data to all caps
        theName = theName.toUpperCase();
        // Create the message
        String result = "Yo! " + theName;
        // Add message to the model
        model.addAttribute("message", result);
        return "helloworld";
    }

    @RequestMapping("/processFormVersionThree")
    public String processFormVersionThree(@RequestParam("studentName") String theName, Model model){
        // Convert the data to all caps
        theName = theName.toUpperCase();
        // create the message
        String result = "Hey My Friend from v3! " + theName;
        // Add message to the model
        model.addAttribute("message", result);

        return "helloworld";
    }
}
