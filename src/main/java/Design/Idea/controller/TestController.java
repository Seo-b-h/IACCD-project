package Design.Idea.controller;

import Design.Idea.model.Test;
import Design.Idea.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService)
    {
        this.testService = testService;
    }

    @GetMapping(value = "/test")
    public Test test() throws Exception
    {
        return testService.test();
    }
    @GetMapping("/map")
    public String map()
    {
        return "map";
    }
}