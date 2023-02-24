package org.client.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Schema(description = "Модель, описывающая аватар пользователя")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="avatarData")
public class AvatarDto {

    @Id // uuid передаю из сервиса, чтобы uuid Avatar соответсвовал uuid Individual
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @JsonProperty(Fields.UUID)
    @Column(name = "uuid", unique = true, length = 50)
    private String uuid;

    private String name;

    private String md5;
    private Long fileSize;
    @Lob
    @Column
    private byte [] byteSize;

    @OneToOne(mappedBy = "avatarDto", cascade = CascadeType.ALL)
    private Individual individual;

    public static class Fields {

        public static final String UUID = "uuid";

    }

}

