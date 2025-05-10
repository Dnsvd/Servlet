package HomeWorks;

import HomeWorks.controller.PostController;
import HomeWorks.repository.PostRepository;
import HomeWorks.service.PostService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {
    @Bean
    public PostController postController(PostService postService) {
        return new PostController(postService);
    }
    @Bean
    public PostService postService(PostRepository postRepository) {
        return new PostService(postRepository);
    }
    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }
}
