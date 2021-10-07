package ru.itmo.webmail.model.service;

import com.google.common.hash.Hashing;
import ru.itmo.webmail.model.domain.User;
import ru.itmo.webmail.model.exception.ValidationException;
import ru.itmo.webmail.model.repository.UserRepository;
import ru.itmo.webmail.model.repository.impl.UserRepositoryImpl;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private static final String USER_PASSWORD_SALT = "dc3475f2b301851b";
    private static int userId = 1;
    private static UserRepository userRepository = new UserRepositoryImpl();

    public void validateRegistration(User user, String password, String passwordConfirm, String email) throws ValidationException {
        if(!password.equals(passwordConfirm)){
            throw new ValidationException("Password not equals");
        }
        boolean has = false;
        for(int i = 0;i < email.length();i++){
            if(email.charAt(i) == '@'){
                has = true;
            }
        }
        if(!userRepository.IsEmailVacant(email)){
            throw new ValidationException("Email is already in use");
        }
        if(!has){
            throw new ValidationException("Email not valid");
        }
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new ValidationException("Login is required");
        }
        if (!user.getLogin().matches("[a-z]+")) {
            throw new ValidationException("Login can contain only lowercase Latin letters");
        }
        if (user.getLogin().length() > 8) {
            throw new ValidationException("Login can't be longer than 8");
        }
        if (userRepository.findByLogin(user.getLogin()) != null) {
            throw new ValidationException("Login is already in use");
        }

        if (password == null || password.isEmpty()) {
            throw new ValidationException("Password is required");
        }
        if (password.length() < 4) {
            throw new ValidationException("Password can't be shorter than 4");
        }
        if (password.length() > 32) {
            throw new ValidationException("Password can't be longer than 32");
        }
    }

    public void validateEnter(User user, String password) throws ValidationException {
        String curSalt = Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString();
        if(!curSalt.equals(user.getPasswordSha1())){
            throw new ValidationException("Password is not correct");
        }
    }
    public void register(User user, String password, String email) {
        user.setPasswordSha1(Hashing.sha256().hashString(USER_PASSWORD_SALT + password,
                StandardCharsets.UTF_8).toString());
        user.setId(userId++);
        user.setEmail(email);
        userRepository.save(user);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public int findCount(){return userRepository.findCount();}

    public User findByLogin(String login){return (User) findAll().stream().filter(c -> c.getLogin().equals(login)).collect(Collectors.toList()).get(0);}

    public String getNameById(int id){
        return (findAll().stream().filter(c -> (c.getId() == id)).collect(Collectors.toList()).get(0)).getLogin();
    }
}
