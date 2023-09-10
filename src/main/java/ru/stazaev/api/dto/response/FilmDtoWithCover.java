package ru.stazaev.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.stazaev.store.entitys.PictureType;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FilmDtoWithCover {
    @Schema(example = "Начало")
    private String title;
    @JsonProperty("release_year")
    @Schema(example = "2010")
    private int releaseYear;
    @Schema(example = "США")
    private String country;
    @Schema(example = "Кристофер Нолан")
    private String director;
    @Schema(example = "292 576 195")
    private Long fees;
    @Schema(example = "Кобб – талантливый вор, лучший из лучших в опасном искусстве извлечения...")
    private String plot;
    @Schema(example = "JPG")
    @JsonProperty("picture_type")
    private PictureType pictureType;
    @Schema(example = "/9j/4AAQSkZJRgABAQAAAQAB...j4cj4cj4/wD4LcuH/wAP/9k=")
    private byte[] data;
}
