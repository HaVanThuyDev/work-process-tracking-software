package com.example.vn2_ht_student.controller;

import com.example.vn2_ht_student.model.dto.reponse.MenuResponseDto;
import com.example.vn2_ht_student.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/getlist")
    public ResponseEntity<List<MenuResponseDto>> getMenusByCurrentUser() {
        List<MenuResponseDto> menus = menuService.getMenusByCurrentUser();
        return ResponseEntity.ok(menus);
    }
}
