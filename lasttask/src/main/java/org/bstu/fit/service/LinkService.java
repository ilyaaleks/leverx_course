package org.bstu.fit.service;

import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.LinkPageDto;
import org.bstu.fit.model.Link;
import org.springframework.data.domain.Pageable;


public interface LinkService {
    Link save(LinkDto linkDto);
    LinkPageDto getAllPublicLinks(Pageable pageable);
    LinkPageDto getAllLinksForAuthUser(long userId,Pageable pageable);
    LinkPageDto getAllUserLinks(Pageable pageable);
    Link update(LinkDto linkDto);
    void delete(long linkId);
}
