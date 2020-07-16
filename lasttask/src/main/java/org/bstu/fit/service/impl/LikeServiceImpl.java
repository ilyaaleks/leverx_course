package org.bstu.fit.service.impl;

import org.bstu.fit.model.Link;
import org.bstu.fit.repository.LinkRepository;
import org.bstu.fit.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LikeServiceImpl implements LikeService {
    private LinkRepository linkRepository;

    public LikeServiceImpl(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    @Override
    public void setLike(long linkId) {
        Link link=findLink(linkId);
        link.setCountOfLikes(link.getCountOfLikes()+1);
        linkRepository.save(link);
    }

    @Override
    public void setDislike(long linkId) {
        Link link=findLink(linkId);
        link.setCountOfDislikes(link.getCountOfDislikes()+1);
        linkRepository.save(link);
    }
    private Link findLink(long linkId)
    {
        Link link=linkRepository.findById(linkId);
        if(link==null)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Link not found");
        }
        return link;
    }
}
