package ru.itmo.wp.lesson8.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table
public class Tag {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
