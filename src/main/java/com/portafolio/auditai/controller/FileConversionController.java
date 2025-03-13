package com.portafolio.auditai.controller;

import com.portafolio.auditai.service.FileConversionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileConversionController {

    @Autowired
    private FileConversionService fileConversionService;

    @PostMapping(
            value = "/convertToJson",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> convert(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type) {
        String json = fileConversionService.convertFileToJson(file, type);
        return ResponseEntity.ok(json);
    }

}
