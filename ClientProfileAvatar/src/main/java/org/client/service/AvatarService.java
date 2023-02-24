package org.client.service;

import org.client.entity.AvatarDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AvatarService {

    AvatarDto uploadAvatar(String uuid,MultipartFile file) throws IOException;

    byte[] getAvatar (String uuid);
    AvatarDto getInfoByUuid(String uuid);
}
