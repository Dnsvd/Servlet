package HomeWorks.repository;

import HomeWorks.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    private final AtomicLong counter = new AtomicLong(1);
    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Post getById(long id) {
        return posts.get(id);
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = counter.getAndIncrement();
            Post newPost = new Post(newId, post.getContent());
            posts.put(newId, newPost);
            return newPost;
        } else {
            return posts.compute(post.getId(), (id, existingPost) -> {
                if (existingPost == null) {
                    return post;
                }
                return new Post(existingPost.getId(), post.getContent());
            });
        }
    }

    public void removeById(long id) {
        posts.remove(id);
    }
}
