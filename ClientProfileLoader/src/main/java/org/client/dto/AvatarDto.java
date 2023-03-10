package org.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "Модель, описывающая аватар пользователя")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvatarDto {

    @JsonProperty(Fields.UUID)
    private String uuid;

    private String name;

    private String md5;

    private Long fileSize;

    private byte [] byteSize;

    public static class Fields {

        public static final String UUID = "uuid";

    }

}

