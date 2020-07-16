package org.bstu.fit.controller;

import org.bstu.fit.converter.LinkMapper;
import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.LinkPageDto;
import org.bstu.fit.model.Link;
import org.bstu.fit.service.LinkService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("api/link")
public class LinkController {
    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/all")
    public ResponseEntity<LinkPageDto> getAllPublicLinks(Pageable pageable) {
        return ResponseEntity.ok(linkService.getAllPublicLinks(pageable));
    }

    @GetMapping("/protected/{userId}")
    public ResponseEntity<LinkPageDto> getAllLinksForAuthUser(@PathVariable long userId, Pageable pageable) {
        return ResponseEntity.ok(linkService.getAllLinksForAuthUser(userId, pageable));
    }
    @GetMapping("/public/{userId}")
    public ResponseEntity<LinkPageDto> getPublicUserLink(@PathVariable long userId, Pageable pageable){
        return ResponseEntity.ok(linkService.getPublicUserLink(userId,pageable));
    }
    @GetMapping("/{username}")
    public ResponseEntity<LinkPageDto> getAllUserLinks(@PathVariable String username, Pageable pageable) {
        return ResponseEntity.ok(linkService.getAllUserLinks(pageable, username));
    }
    @GetMapping("/name/{linkName}")
    public ResponseEntity<LinkPageDto> getLinksByName(@PathVariable String linkName,Pageable pageable)
    {
        return ResponseEntity.ok(linkService.findLinksByName(linkName, pageable));
    }
    @PutMapping
    public ResponseEntity<LinkDto> update(@Valid @RequestBody LinkDto linkDto) {
        Link link = linkService.update(linkDto);
        return ResponseEntity.ok(LinkMapper.INSTANCE.toDTO(link));
    }

    @DeleteMapping("/{linkId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable long linkId) {
        linkService.delete(linkId);
    }

    @PostMapping()
    public ResponseEntity<LinkDto> save(@Valid @RequestBody LinkDto linkDto) {
        Link link = linkService.save(linkDto);
        return ResponseEntity.ok(LinkMapper.INSTANCE.toDTO(link));
    }
    @GetMapping("/tag/{tagId}")
    public ResponseEntity<LinkPageDto> getLinksByTag(@PathVariable long tagId, Pageable pageable)
    {
        return ResponseEntity.ok(linkService.findLinksByTag(tagId,pageable));
    }

}
