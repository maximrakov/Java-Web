package ru.itmo.webmail.model.service;

import ru.itmo.webmail.model.repository.impl.EmailConfirmationRepositoryImpl;

public class EmailConfirmationService {
    private static long count = 0;
    EmailConfirmationRepositoryImpl emailConfirmationRepository = new EmailConfirmationRepositoryImpl();

    public EmailConfirmationService() {
    }
    public int ContainsSecret(String secret){
        return emailConfirmationRepository.ContainsSecret(secret);
    }
    public void addSecretKey(int userId, String secret) {
        emailConfirmationRepository.addSecretKey(count, userId, secret);
        count++;
    }
}
