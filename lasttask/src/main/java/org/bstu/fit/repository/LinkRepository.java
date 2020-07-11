package org.bstu.fit.repository;

import org.bstu.fit.model.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link, Long> {
    Page<Link> findAll(Pageable pageable);
    @Query(value = "select * from link where status=1 or status=2 and user_id=?1", nativeQuery = true)
    Page<Link> findByStatus(long userId,Pageable page);
    Page<Link> findByUser_Username(String username,Pageable page);
    Link findById(long id);
}
