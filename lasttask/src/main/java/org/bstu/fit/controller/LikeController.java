package org.bstu.fit.controller;

import org.bstu.fit.service.LikeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rating")
public class LikeController {
    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping("/like/{linkId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void setLike(@PathVariable long linkId)
    {
        likeService.setLike(linkId);
    }
    @GetMapping("/dislike/{linkId}")
    @ResponseStatus(code = HttpStatus.OK)
    public void setDislike(@PathVariable long linkId)
    {
        likeService.setDislike(linkId);
    }

}
