package homeWorks.service;

import homeWorks.exception.NotFoundException;
import homeWorks.model.Post;
import homeWorks.repository.PostRepository;

import java.util.List;

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
