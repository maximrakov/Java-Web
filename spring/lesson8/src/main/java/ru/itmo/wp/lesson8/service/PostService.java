package ru.itmo.wp.lesson8.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.lesson8.domain.Comment;
import ru.itmo.wp.lesson8.domain.Person;
import ru.itmo.wp.lesson8.domain.Post;
import ru.itmo.wp.lesson8.domain.Tag;
import ru.itmo.wp.lesson8.form.PersonCredentials;
import ru.itmo.wp.lesson8.repository.PersonRepository;
import ru.itmo.wp.lesson8.repository.PostRepository;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository personRepository) {
        this.postRepository = personRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post findById(long id) {
        return postRepository.findById(id);
    }

    public Post findByTitle(String title){return postRepository.findByTitle(title);}
    public void writeComment(Post post, Comment comment) {
        comment.setPost(post);
        post.getComments().add(comment);
        postRepository.save(post);
    }

    public void addTag(Post post, Tag tag) {
        post.getTags().add(tag);
        postRepository.save(post);
    }
}
