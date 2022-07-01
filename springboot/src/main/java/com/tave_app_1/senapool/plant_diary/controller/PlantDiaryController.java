package com.tave_app_1.senapool.plant_diary.controller;

import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryInfoDto;
import com.tave_app_1.senapool.plant_diary.dto.PlantDiaryUploadDto;
import com.tave_app_1.senapool.plant_diary.handler.ex.CustomValidationException;
import com.tave_app_1.senapool.plant_diary.service.PlantDiaryService;
import eu.freme.common.persistence.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.beanvalidation.CustomValidatorBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
public class PlantDiaryController {

    private final PlantDiaryService plantDiaryService;

    @GetMapping("/myplant-diary/{userPK}/{plantPK}")
    public String upload(){
        return "/myplant-diary/{userPK}/{plantPK}";
    }

    @PostMapping("/myplant-diary/{userPK}/{plantPK}")
    public String uploadPlantDiary(PlantDiaryUploadDto plantDiaryUploadDto, @RequestParam("uploadImgUrl")MultipartFile multipartFile,
                                   RedirectAttributes redirectAttributes, User user){
        if(multipartFile.isEmpty()){
            throw new CustomValidationException("이미지가 첨부되지 않았습니다.");
        }
        plantDiaryService.save(plantDiaryUploadDto,multipartFile);
        redirectAttributes.addAttribute("id",user.getTokens());
        return "redirect:/myplant-list/{userPK}";
    }

}