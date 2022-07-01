package com.tave_app_1.senapool.plant_diary.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class PlantDiaryInfoDto {

    private long id;
    private String title;
    private String content;
//    private LocalDateTime createdate;
    private String picture;
    private Boolean publish;

}