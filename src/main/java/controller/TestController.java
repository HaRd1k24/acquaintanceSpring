package controller;

import model.HelloResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class TestController {

    @GetMapping("/")
    public String helloWorld() {
        return "Hi World".toUpperCase(Locale.ROOT);
    }

    /*
    example post : curl -X 'POST' -d {"lev"} \http://localhost:8080/post
    - d(содержимое запроса) {"String name"}
     */
    @PostMapping("/post")
    public HelloResponse hello(@RequestBody String name) {
        HelloResponse hl = new HelloResponse();
        hl.setMessage("Hello " + name);
        return hl;
    }

    /*
     curl -X 'GET' \http://localhost:8080/get?v=2;
     v- it's version = 0,1,2 + return getMessage;

     */
    @RequestMapping(value = "/{get}", method = RequestMethod.GET)
    public HelloResponse Version(@PathVariable String get, @RequestParam(value = "v", required = false) String version) {
        HelloResponse response = new HelloResponse();
        if (version.isEmpty() || Integer.parseInt(version) <= 0) {
            response.setMessage("Please make sure your request is correct");
            return response;
        }else {
            switch (version) {
                case "1":
                    response.setMessage("version : " + version);
                    break;
                case "2":
                    response.setMessage("Hello version : " + version);
                    break;
                case "3":
                    response.setMessage(" Hi, nice to meet you, you version application: " + version);
                    break;
                default:
                    response.setMessage("Oops, sorry, your version is not unknown");
            }

        }
        return response;
    }
}
