package HomeWorks.controller;

import HomeWorks.exception.NotFoundException;
import HomeWorks.model.Post;
import HomeWorks.service.PostService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;
    private final Gson gson;

    public PostController(PostService service) {
        this.service = service;
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping().create();
    }
    @GetMapping(produces = APPLICATION_JSON)
    public String all() throws IOException {
        List<Post> posts = service.all();
        return gson.toJson(posts);
    }
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON)
    public String getById(@PathVariable long id) throws IOException {
        try {
            Post post = service.getById(id);
            return gson.toJson(post);
        } catch (NotFoundException e) {
            return gson.toJson(new ErrorResponse(e.getMessage()));
        }
    }
    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public void save(@RequestBody String json) throws IOException {
        Post post = gson.fromJson(json, Post.class);
        Post saved = service.save(post);
        System.out.println(saved);
    }
    @DeleteMapping(value = "/{id}", produces = APPLICATION_JSON)
    public String removeById(@PathVariable long id) throws IOException {
        try {
            service.removeById(id);
            return gson.toJson("Удаление прошло успешно");
        } catch (NotFoundException e) {
            return gson.toJson(new ErrorResponse(e.getMessage()));
        }
    }

    private static class ErrorResponse {
        private final String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }
    }
}
