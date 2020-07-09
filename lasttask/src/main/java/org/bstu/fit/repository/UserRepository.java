package org.bstu.fit.repository;

import org.bstu.fit.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    Page<User> findAll(Pageable pageable);
    User findByActivationCode(String code);
    User findById(long id);
}
