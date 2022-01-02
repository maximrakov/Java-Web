package ru.itmo.wp.lesson8.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.itmo.wp.lesson8.form.PersonCredentials;
import ru.itmo.wp.lesson8.service.PersonService;

@Component
public class PersonCredentialsRegisterValidator implements Validator {
    private final PersonService personService;

    public PersonCredentialsRegisterValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return PersonCredentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            PersonCredentials personCredentials = (PersonCredentials) target;
            if (!personService.isLoginVacant(personCredentials.getLogin())){
                errors.rejectValue("login", "login.not-vacant","Login is already in use");
            }
        }
    }
}
