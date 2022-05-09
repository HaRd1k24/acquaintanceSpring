package controller;

import model.HelloResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class TestController {

    @GetMapping("/")
    public String helloWorld(String name){
        return "Hi world".toUpperCase(Locale.ROOT);
    }

/*
example post : curl -X 'POST' -d {"lev"} \http://localhost:8080/post
- d(содержимое запроса) {"String name"}
 */
    @PostMapping("/post")
    public HelloResponse hello(@RequestBody String name){
        HelloResponse hl = new HelloResponse();
        hl.setMessage("Hello " + name);
        return hl;
    }

    /*
     curl -X 'GET' \http://localhost:8080/lev?v=2;
     v- it's version = 0,1,2 + return getMessage;

     */
     @RequestMapping(value = "/{name}",method = RequestMethod.GET)
    public HelloResponse Version(@PathVariable String name, @RequestParam(value = "v",required = false)Integer version){
        HelloResponse response = new HelloResponse();
        switch (version){
            case 0:  response.setMessage("version 0");
            break;
            case 1: response.setMessage(name + " Hello version : 1");
            break;
            case 2: response.setMessage(" Hi, nice to meet you " + name + ", you version application 2");
            break;
            default: response.setMessage("Oops, sorry, your version is not unknown");
        }
        return response;
     }
}
