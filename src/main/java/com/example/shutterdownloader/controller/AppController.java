package com.example.shutterdownloader.controller;

import com.example.shutterdownloader.service.DownloaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    private final Logger logger = LoggerFactory.getLogger(AppController.class);
    private final DownloaderService downloaderService;

    public AppController(DownloaderService downloaderService) {
        this.downloaderService = downloaderService;
    }

    @GetMapping("/download")
    public String greeting(@RequestParam(name = "url") String url) {
        downloaderService.addToQueue(url);
        return "Url " + url + " added to download queue";
    }

}
