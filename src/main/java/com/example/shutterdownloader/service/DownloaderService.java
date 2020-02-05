package com.example.shutterdownloader.service;

import com.example.shutterdownloader.client.ShutterstockClient;
import com.example.shutterdownloader.model.DownloaderJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class DownloaderService {
    private static final Logger logger = LoggerFactory.getLogger(DownloaderService.class);
    private static final int DOWNLOAD_INTERVAL = 15_000;

    private final AtomicBoolean isDownloadingInProgress;
    private final ShutterstockClient shutterstockClient;
    private final List<DownloaderJob> jobQueue;

    public DownloaderService(ShutterstockClient shutterstockClient) {
        this.shutterstockClient = shutterstockClient;
        this.jobQueue = new CopyOnWriteArrayList<>();
        isDownloadingInProgress = new AtomicBoolean(false);
    }

    public void addToQueue(String url) {
        DownloaderJob job = new DownloaderJob(url);
        if (!jobQueue.contains(job)) {
            jobQueue.add(job);
            logger.info("Adding to queue: {}", url);
        }

    }

    @Scheduled(fixedRate = DOWNLOAD_INTERVAL)
    public void startDownloading() {
        if (isDownloadingInProgress.get()) {
            return;
        }
        isDownloadingInProgress.set(true);

        List<String> imageUrls = jobQueue.stream().map(DownloaderJob::getUrl).collect(Collectors.toList());
        jobQueue.clear();

        if (!imageUrls.isEmpty()) {
            logger.info("Initializing downloading {} images", imageUrls.size());
            shutterstockClient.downloadImages(imageUrls);
        }

        isDownloadingInProgress.set(false);
    }
}
