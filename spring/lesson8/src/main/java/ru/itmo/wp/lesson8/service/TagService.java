package ru.itmo.wp.lesson8.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.lesson8.domain.Post;
import ru.itmo.wp.lesson8.domain.Tag;
import ru.itmo.wp.lesson8.repository.TagRepository;

import java.util.ArrayList;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag MakeTag(Post post, String name) {
        Tag tag = new Tag();
        tag.setPosts(new ArrayList<Post>());
        tag.setName(name);
        tag.getPosts().add(post);
        tagRepository.save(tag);
        return tag;
    }
}
