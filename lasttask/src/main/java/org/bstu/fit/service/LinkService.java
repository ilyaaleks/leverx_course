package org.bstu.fit.service;

import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.LinkPageDto;
import org.bstu.fit.model.Link;
import org.bstu.fit.model.Tag;
import org.springframework.data.domain.Pageable;


public interface LinkService {
    Link save(LinkDto linkDto);
    LinkPageDto getAllPublicLinks(Pageable pageable);
    LinkPageDto getAllLinksForAuthUser(long userId,Pageable pageable);
    LinkPageDto getAllUserLinks(Pageable pageable, String username);
    Link update(LinkDto linkDto);
    void delete(long linkId);
    LinkPageDto findLinksByTag(long tagId, Pageable pageable);
    LinkPageDto findLinksByName(String name, Pageable pageable);
    LinkPageDto getPublicUserLink(long userId, Pageable pageable);
}
