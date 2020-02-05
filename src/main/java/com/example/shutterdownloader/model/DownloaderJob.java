package com.example.shutterdownloader.model;

import java.util.Objects;

public class DownloaderJob {
    private final String url;

    public DownloaderJob(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "DownloaderJob{" +
                "url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownloaderJob that = (DownloaderJob) o;
        return Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
