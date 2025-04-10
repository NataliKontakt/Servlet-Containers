package ru.netology.controller;

import com.google.gson.Gson;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    private static final String APPLICATION_JSON = "application/json";
    private static final String CONTENT_TYPE_HEADER = "Content-Type";

    private final PostService service;
    private final Gson gson;

    public PostController(PostService service) {
        this.service = service;
        this.gson = new Gson();
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON);
        final var data = service.all();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        response.setHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON);
        final var data = service.getById(id);
        response.getWriter().print(gson.toJson(data));
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON);
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) {
        service.removeById(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
