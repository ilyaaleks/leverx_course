package org.bstu.fit.controller;

import com.sun.mail.iap.Response;
import org.bstu.fit.converter.LinkMapper;
import org.bstu.fit.dto.LinkDto;
import org.bstu.fit.dto.LinkPageDto;
import org.bstu.fit.model.Link;
import org.bstu.fit.service.LinkService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/link")
public class LinkController {
    private LinkService linkService;

    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }

    @GetMapping("/all")
    public ResponseEntity<LinkPageDto> getAllPublicLinks(Pageable pageable)
    {
        return ResponseEntity.ok(linkService.getAllPublicLinks(pageable));
    }
    @GetMapping("/protected")
    public ResponseEntity<LinkPageDto> getAllLinksForAuthUser(Pageable pageable)
    {
        return ResponseEntity.ok(linkService.getAllLinksForAuthUser(pageable));
    }
    @GetMapping
    public ResponseEntity<LinkPageDto> getAllUserLinks(Pageable pageable)
    {
        return ResponseEntity.ok(linkService.getAllUserLinks(pageable));
    }
    @PutMapping
    public ResponseEntity<LinkDto> update(LinkDto linkDto)
    {
        Link link=linkService.update(linkDto);
        return ResponseEntity.ok(LinkMapper.INSTANCE.toDTO(link));
    }
    @DeleteMapping("/{linkId}")
    public ResponseEntity<String> delete(@PathVariable long linkId)
    {
        linkService.delete(linkId);
        return ResponseEntity.ok("Delete successfully");
    }
    @PostMapping()
    public ResponseEntity<LinkDto> save(@RequestBody LinkDto linkDto)
    {
        Link link=linkService.save(linkDto);
        return ResponseEntity.ok(LinkMapper.INSTANCE.toDTO(link));
    }

}
