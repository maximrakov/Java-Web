package ru.itmo.wp.lesson8.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.lesson8.domain.*;
import ru.itmo.wp.lesson8.form.PersonCredentials;
import ru.itmo.wp.lesson8.repository.PersonRepository;
import ru.itmo.wp.lesson8.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;

        prepare();
    }

    private void prepare() {
        for (Role.Name name: Role.Name.values()) {
            if (roleRepository.countByName(name) == 0) {
                Role role = new Role();
                role.setName(name);
                roleRepository.save(role);
            }
        }
    }

    public Person register(PersonCredentials registerForm) {
        Person person = new Person();
        person.setAccess("enable");
        person.setLogin(registerForm.getLogin());
        personRepository.save(person);
        personRepository.updatePassword(person.getId(), registerForm.getPassword());
        return person;
    }

    public Person enter(PersonCredentials enterForm) {
        Person person = findByLogin(enterForm.getLogin());
        if(person.getAccess().equals("disable")){
            return null;
        }else{
            return person;
        }
    }

    public Person findById(long userId) {
        return personRepository.findById(userId).orElse(null);
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public boolean isLoginVacant(String login) {
        return personRepository.countByLogin(login) == 0;
    }

    public Person findByLogin(String login){
        return personRepository.findByLogin(login);
    }
    public void changeAccess(long userId) {
        Person person = findById(userId);
        if(person.getAccess().equals("enable")){
            personRepository.updateAccess(userId,"disable");
        }else{
            personRepository.updateAccess(userId, "enable");
        }
    }

    public void writePost(Person person, Post post) {
        post.setPerson(person);
        post.setTags(new ArrayList<Tag>());
        person.getPosts().add(post);
        personRepository.save(person);
    }

    public void writeComment(Person person, Comment comment, Post post) {
        comment.setPerson(person);
        comment.setPost(post);
        person.getComments().add(comment);
        personRepository.save(person);
    }
}
