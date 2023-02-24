package org.client.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    private KafkaTemplate<String, MultipartFile> kafkaTemplate;
    @Autowired
    public AvatarController(KafkaTemplate<String, MultipartFile> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //получили файл от клиента и отправили его для загрузки в ClientProfileAvatar
    @PostMapping
    public ResponseEntity<?> uploadAvatar(@PathVariable String icp, @RequestParam("avatar") MultipartFile file) {
        kafkaTemplate.send("avatar", icp, file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
