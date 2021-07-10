package com.lpiot.ouila.resources;

import com.lpiot.ouila.domain.FileResponse;
import com.lpiot.ouila.services.StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DocumentUploadResource {
    @Autowired
    private StorageService storageService;

    @GetMapping("/documents")
    public ResponseEntity<List<String>> listAllFiles(Model model) {
        List<String> files = storageService.loadAll().map(path -> ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/document/").path(path.getFileName().toString()).toUriString()).collect(Collectors.toList());

        return ResponseEntity.ok(files);
    }

    @GetMapping("/document/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {

        Resource resource = storageService.loadAsResource(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PostMapping("/documents/upload")
    public FileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String name = storageService.store(file);

        String uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/documents/").path(name).toUriString();

        return new FileResponse(name, uri, file.getContentType(), file.getSize());
    }

}
