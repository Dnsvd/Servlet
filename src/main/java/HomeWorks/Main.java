package HomeWorks;

import HomeWorks.controller.PostController;
import HomeWorks.repository.PostRepository;
import HomeWorks.service.PostService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
//        final var context = new AnnotationConfigApplicationContext("HomeWorks");
//        final var repository = context.getBean(PostRepository.class);
//        final var controller = context.getBean(PostController.class);
//        final var service = context.getBean(PostService.class);

    }
}
