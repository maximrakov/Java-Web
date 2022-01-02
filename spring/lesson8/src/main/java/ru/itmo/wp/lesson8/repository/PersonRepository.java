package ru.itmo.wp.lesson8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itmo.wp.lesson8.domain.Person;

import javax.transaction.Transactional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE person SET passwordSha=md5(?2) WHERE id=?1", nativeQuery = true)
    void updatePassword(long id, String password);

    @Modifying
    @Transactional
    @Query(value = "UPDATE person SET access=?2 WHERE id=?1", nativeQuery = true)
    void updateAccess(long id, String newValue);

    Person findByLogin(String login);

    int countByLogin(String login);


}
