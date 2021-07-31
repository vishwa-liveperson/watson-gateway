package watson.gateway.controller;


import org.springframework.http.HttpHeaders;
import watson.gateway.util.client.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class HelloController {

    @PostMapping("/hello")
    public void hello(@RequestHeader HttpHeaders headers, @RequestBody User user){
        System.out.println(user.getName());
        System.out.println(user);
    }
}
