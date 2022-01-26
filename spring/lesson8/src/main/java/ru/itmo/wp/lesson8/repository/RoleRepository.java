package ru.itmo.wp.lesson8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.wp.lesson8.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    int countByName(Role.Name name);
}
