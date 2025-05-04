package homeWorks.servlet;

import homeWorks.model.Post;
import homeWorks.repository.PostRepository;
import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static final String APPLICATION_JSON = "application/json";
    private static final String UTF_8 = "UTF-8";
    private static final String ID_PATH = "/api/posts/\\d+";
    private static final String ROOT_PATH = "/api/posts";

    private final PostRepository repository;

    public MainServlet() {
        this.repository = new PostRepository();
    }
    public MainServlet(PostRepository repository) {
        this.repository = repository;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var path = req.getRequestURI();

        if (path.matches(ID_PATH)) {
            final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
            final var post = repository.getById(id);
            if (post == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            sendResponse(resp, post);
            return;
        }

        if (path.equals(ROOT_PATH)) {
            final var posts = repository.all();
            sendResponse(resp, posts);
            return;
        }

        resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final var path = req.getRequestURI();

        if (!path.equals(ROOT_PATH)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final var post = readRequest(req);
        final var saved = repository.save(post);
        sendResponse(resp, saved);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final var path = req.getRequestURI();

        if (!path.matches(ID_PATH)) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        final var id = Long.parseLong(path.substring(path.lastIndexOf("/") + 1));
        repository.removeById(id);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    private Post readRequest(HttpServletRequest req) throws IOException {
        final var body = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        return Post.fromJson(body);
    }

    private void sendResponse(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType(APPLICATION_JSON);
        resp.setCharacterEncoding(UTF_8);
        final var gson = new Gson();
        resp.getWriter().write(gson.toJson(data));
    }
}
