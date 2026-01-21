package com.example.vn2_ht_student.service;

import com.example.vn2_ht_student.model.dto.reponse.MenuResponseDto;
import java.util.List;

public interface MenuService {
    List<MenuResponseDto> getMenusByCurrentUser();
}