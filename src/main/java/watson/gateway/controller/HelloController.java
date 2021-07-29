package watson.gateway.controller;


import watson.gateway.client.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HelloController {

    @PostMapping("/hello")
    public void hello(@RequestBody User user){
        System.out.println(user.getName());
//        return "Done";
    }
}
