package com.example.vn2_ht_student.model.dto;

import com.example.vn2_ht_student.model.enums.Action;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class MenuResponseDto {
    private String key;
    private String label;
    private String href;
    private String icon;
    private boolean hasSubMenu;
    private String parentKey;
    private List<MenuResponseDto> subMenus;
    private List<Action> requiredActions;
}
