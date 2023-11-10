package ru.umom.smolathonbackend.photos.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tika.mime.MimeTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.umom.smolathonbackend.photos.dto.PhotoDto;
import ru.umom.smolathonbackend.photos.services.PhotoService;

import java.io.IOException;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/photos")
public class PhotoController {
    PhotoService photoService;


    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@ModelAttribute PhotoDto.Request.Upload dto) throws MimeTypeException, IOException {
        return photoService.upload(dto);

    }

    @GetMapping(value = "/download")
    public ResponseEntity<?> download(@ModelAttribute PhotoDto.Request.Download dto) throws IOException {
        return photoService.download(dto);
    }
}
