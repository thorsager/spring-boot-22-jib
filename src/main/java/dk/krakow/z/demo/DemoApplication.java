package dk.krakow.z.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    @Controller
    @RequestMapping("/")
    static class RootController {
        @GetMapping
        public ResponseEntity root() {
            URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/welcome/joe").build().toUri();
            return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).location(location).build();
        }

        @GetMapping("status")
        public ResponseEntity<String> status() {
            return ResponseEntity.ok("All is good!");
        }
    }

    @RestController
    @RequestMapping("/welcome")
    static class GreetingController {

        @GetMapping("/{name}")
        public String sayHello(@PathVariable("name") String name) {
            return String.format("Hello, %s! and welcome.", name);
        }
    }
}

