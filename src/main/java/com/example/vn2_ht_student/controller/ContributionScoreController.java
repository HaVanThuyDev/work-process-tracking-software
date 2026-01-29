package com.example.vn2_ht_student.controller;

import com.example.vn2_ht_student.Utils.Constants;
import com.example.vn2_ht_student.model.dto.reponse.ResponseDTO;
import com.example.vn2_ht_student.model.entity.ContributionScore;
import com.example.vn2_ht_student.service.ContributionScoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contribution-scores")
@RequiredArgsConstructor
public class ContributionScoreController {

    private final ContributionScoreService contributionScoreService;

    @PostMapping("/calculate/{groupId}")
    public ResponseEntity<ResponseDTO<?>> calculateContribution(@PathVariable Long groupId) {
        try{
            contributionScoreService.calculateContribution(groupId);
            return ResponseEntity.ok(ResponseDTO.builder()
                    .status("ok")
                    .code(Constants.HTTP_STATUS.SUCCESS)
                    .message("tính điểm thành công")
                    .build());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResponseDTO.builder()
                            .status("error")
                            .code(Constants.HTTP_STATUS.INTERNAL_SERVER_ERROR)
                            .data(null)
                            .message(e.getMessage())
                            .build());
        }
    }
    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<ContributionScore>> getContributionScores(@PathVariable Long groupId) {
        return ResponseEntity.ok(contributionScoreService.getContributionScoresByGroup(groupId));
    }
    @GetMapping("/ranking/{groupId}")

    public ResponseEntity<?> getContributionRanking(@PathVariable Long groupId) {
        return ResponseEntity.ok(contributionScoreService.getContributionRanking(groupId));
    }
}
