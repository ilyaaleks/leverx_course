package org.bstu.fit.repository;

import org.bstu.fit.model.Link;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LinkRepository extends CrudRepository<Link, Long> {
    @Query(value = "select * from link where status=0", nativeQuery = true)
    Page<Link> findAll(Pageable pageable);
    @Query(value = "select * from link where status=0 or status=2", nativeQuery = true)
    Page<Link> findByStatus(long userId,Pageable page);
    Page<Link> findByUser_Username(String username,Pageable page);
    Link findById(long id);
    @Query(value = "select * from link links join tag_table tag on tag.link_id=links.id where (links.status=0 or links.status=2) and tag.tag_id=?1", nativeQuery = true)
    Page<Link> findByTags(long tagId, Pageable pageable);
    @Query(value = "select * from link where (status=0 or status=2) and name=?1", nativeQuery = true)
    Page<Link> findByName(String name, Pageable pageable);
    @Query(value = "select * from link where (status=0 or status=2) and user_id=?1", nativeQuery = true)
    Page<Link> findPublicUserLink(long userId, Pageable pageable);
}
