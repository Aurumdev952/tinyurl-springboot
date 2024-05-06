package com.tinyurl.tinyurl.controllers;

import com.tinyurl.tinyurl.dtos.UrlReqDto;
import com.tinyurl.tinyurl.exceptions.CustomRequestException;
import com.tinyurl.tinyurl.models.Url;
import com.tinyurl.tinyurl.services.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/")
    public Url createUrl(@RequestBody UrlReqDto data) throws CustomRequestException {
        return urlService.create(data);
    }

    @DeleteMapping("/{urlId}")
    public Url deleteUrl(@PathVariable("urlId") String urlId) throws CustomRequestException {
        return urlService.delete(urlId);
    }

    @GetMapping("/all")
    public List<Url> getAllUrls() throws CustomRequestException {
        return urlService.getAll();
    }

    @GetMapping("/{urlId}")
    public void redirectUrl(HttpServletResponse response, @PathVariable("urlId") String urlId) throws CustomRequestException, IOException {
        Url url = urlService.get(urlId);
        response.sendRedirect(url.getOriginalUrl());
    }
}
