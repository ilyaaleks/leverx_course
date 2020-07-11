package org.bstu.fit.service.impl;

import org.bstu.fit.converter.LinkMapper;
import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.LinkPageDto;
import org.bstu.fit.model.Link;
import org.bstu.fit.model.Tag;
import org.bstu.fit.repository.LinkRepository;
import org.bstu.fit.repository.TagRepository;
import org.bstu.fit.repository.UserRepository;
import org.bstu.fit.service.LinkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LinkServiceImpl implements LinkService {
    private LinkRepository linkRepository;
    private TagRepository tagRepository;
    private UserRepository userRepository;

    public LinkServiceImpl(LinkRepository linkRepository, TagRepository tagRepository, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.tagRepository = tagRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Link save(LinkDto linkDto) {
        Link link=LinkMapper.INSTANCE.fromDTO(linkDto);
        Set<Tag> tagOfCurrentLink=new HashSet<>();
        iterateTags(link,tagOfCurrentLink);
        link.setTags(tagOfCurrentLink);
        link.setUser(userRepository.findByUsername(getUsernameOfCurrentUser()));
        link.setDateOfCreation(new Date());
        return linkRepository.save(link);
    }

    @Override
    public LinkPageDto getAllPublicLinks(Pageable pageable) {
        Page<Link> linksPage=linkRepository.findAll(pageable);
        return getLinkPageDto(linksPage,pageable);
    }

    @Override
    public LinkPageDto getAllLinksForAuthUser(long userId,Pageable pageable) {
        Page<Link> linksPage=linkRepository.findByStatus(userId,pageable);
        return getLinkPageDto(linksPage,pageable);
    }

    @Override
    public LinkPageDto getAllUserLinks(Pageable pageable) {
        Page<Link> linksPage=linkRepository.findByUser_Username(getUsernameOfCurrentUser(),pageable);
        return getLinkPageDto(linksPage,pageable);
    }

    @Override
    public Link update(LinkDto linkDto) {
        Link link=linkRepository.findById(linkDto.getId());
        if(link==null || !link.getUser().getUsername().equals(getUsernameOfCurrentUser()))
        {
            throw new IllegalArgumentException("Link not found or you dont't have access");
        }
        Link updatedLink=LinkMapper.INSTANCE.fromDTO(linkDto);
        Set<Tag> tagOfCurrentLink=new HashSet<>();
        iterateTags(updatedLink,tagOfCurrentLink);
        link.setTags(tagOfCurrentLink);
        link.setDateOfChange(new Date());
        link.setName(linkDto.getName());
        link.setUrl(linkDto.getUrl());
        return linkRepository.save(link);
    }

    @Override
    public void delete(long linkId) {
        Link link=linkRepository.findById(linkId);
        if(link!=null && link.getUser().getUsername().equals(getUsernameOfCurrentUser()))
        {
            linkRepository.deleteById(linkId);
        }
        else
        {
            throw new IllegalArgumentException("Link not found or you dont't have access");
        }

    }
    private LinkPageDto getLinkPageDto(Page<Link> linksPage, Pageable pageable)
    {
        List<LinkDto> linkDtos=new ArrayList<>();
        for(Link link:linksPage.getContent())
        {
            LinkDto linkDto=LinkMapper.INSTANCE.toDTO(link);
            linkDtos.add(linkDto);
        }
        LinkPageDto linkPageDto=new LinkPageDto(linkDtos,pageable.getPageNumber(),linksPage.getTotalPages());
        return linkPageDto;
    }
    private void iterateTags(Link link,Set<Tag> tagOfCurrentLink)
    {
        for(Tag tag: link.getTags())
        {
            Tag currentTag=tagRepository.findByName(tag.getName());
            if(currentTag==null)
            {
                currentTag=tagRepository.save(tag);
            }
            tagOfCurrentLink.add(currentTag);
        }
    }
    private String getUsernameOfCurrentUser()
    {
        String username;
        Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }
}
