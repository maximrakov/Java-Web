package ru.itmo.wp.lesson8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.wp.lesson8.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
