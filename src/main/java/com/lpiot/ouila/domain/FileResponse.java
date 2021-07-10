package com.lpiot.ouila.domain;

import lombok.Data;

@Data
public class FileResponse {

    public FileResponse(String name, String uri, String type, long size) {
        this.name = name;
        this.uri = uri;
        this.type = type;
        this.size = size;
    }

    private String name;
    private String uri;
    private String type;
    private long size;
}