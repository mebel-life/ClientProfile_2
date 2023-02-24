package org.client.controllers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.client.entity.AvatarDto;
import org.client.service.IndividualService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@EnableKafka
@Component
public class AvatarController {

    private IndividualService individualService;
    Logger logger = LoggerFactory.getLogger(IndividualRESTController.class);

    @Autowired
    public void setIndividualService(IndividualService individualService) {
        this.individualService = individualService;
    }

    //сохраняем поле avatarDto для individualService
    @KafkaListener(topics = "avatarDto")
    public void saveAvatar(ConsumerRecord<String, AvatarDto> record) {
        logger.info("Saving avatar");
        individualService.saveAvatarDto(record.value(), record.key());

    }
}
