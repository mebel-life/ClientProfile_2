package org.client.service;

import lombok.AllArgsConstructor;
import org.client.entity.AvatarDto;
import org.client.repo.AvatarRepo;
import org.client.util.AvatarUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class AvatarServiceImp implements AvatarService {

    private final AvatarRepo avatarRepo;

    @Transactional // изменил Возвращание AvatarDtо
    public AvatarDto uploadAvatar(String uuid, MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new NullPointerException("File is empty. Please try again");
        }
        AvatarDto avatarDto = avatarRepo.save(AvatarDto.builder()
                .uuid(uuid)
                .name(file.getOriginalFilename())
                .md5(DigestUtils.md5Hex(file.getName()))
                .fileSize(file.getSize())
                .byteSize(AvatarUtils.compressImage(file.getBytes())).build());
        if (avatarDto != null) {
            return avatarDto;
        }
        return null;
    }


    public byte[] getAvatar(String uuid) {
        Optional<AvatarDto> avatarData = avatarRepo.findByUuid(uuid);
        byte[] avatar = AvatarUtils.decompressImage(avatarData.get().getByteSize());
        return avatar;
    }

    public AvatarDto getInfoByUuid(String name) {
        Optional<AvatarDto> avatarDto = avatarRepo.findByUuid(name);

        return AvatarDto.builder()
                .uuid(avatarDto.get().getUuid())
                .name(avatarDto.get().getName())
                .md5(avatarDto.get().getMd5())
                .fileSize(avatarDto.get().getFileSize())
                .byteSize(AvatarUtils.decompressImage(avatarDto.get().getByteSize())).build();

    }


}
