package HomeWorks.service;

import HomeWorks.exception.NotFoundException;
import HomeWorks.model.Post;
import HomeWorks.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository repository;

    public PostService(PostRepository repository) {
        this.repository = repository;
    }

    public List<Post> all() {
        return repository.all();
    }

    public Post getById(long id) {
        Post post = repository.getById(id);
        if (post == null) {
            throw new NotFoundException("Post with id " + id + " not found");
        }
        return post;
    }

    public Post save(Post post) {
        return repository.save(post);
    }

    public void removeById(long id) {
        if (repository.getById(id) == null) {
            throw new NotFoundException("Post with id " + id + " not found");
        }
        repository.removeById(id);
    }
}
