package assignment4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Controller
public class Assignment4Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment4Application.class, args);
        System.out.println("--- Server running on port 8080 ---");
    }

    @GetMapping("/")
    public String index() {
        return "forward:/index.html";
    }
}