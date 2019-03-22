package pl.smog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.smog.entity.TestEntity;
import pl.smog.repository.TestRepository;

import java.util.List;

@RestController
@RequestMapping("/")
public class TestController {

    @Autowired
    TestRepository testRepository;

    @RequestMapping("/test")
    public String test() {
        return "This is test";
    }

    @GetMapping("/getTest/{id}")
    public List<TestEntity> getTestById(@PathVariable Long id) {
        return testRepository.findAllById(id);
    }

    @GetMapping("/addTest/{f1}/{f2}")
    public String  addTest(@PathVariable String f1, @PathVariable String f2) {
        testRepository.save(new TestEntity(f1, f2));
        return"saved : "+f1+", "+f2;
    }




}
