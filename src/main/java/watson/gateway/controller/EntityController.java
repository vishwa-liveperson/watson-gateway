package watson.gateway.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import watson.gateway.dto.EntityPayload;

import java.util.List;

@RestController
@RequestMapping
public class EntityController {

    @PostMapping("/createEntities")
    public String createEntities(@RequestBody List<EntityPayload> entities) throws Exception {
        System.out.println(entities);
//        createIntent(intent);
        return "Valid Entity configured !!";
    }
}
