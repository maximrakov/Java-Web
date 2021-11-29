package ru.itmo.webmail.model.repository;

public interface EmailConfirmationrRepository {
    int ContainsSecret(String secret);
    void addSecretKey(int id, int userId, String secret);
}
