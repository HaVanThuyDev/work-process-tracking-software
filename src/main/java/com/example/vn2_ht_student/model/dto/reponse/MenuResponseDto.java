package com.example.vn2_ht_student.model.dto.reponse;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class MenuResponseDto {

    private String key;
    private String label;
    private String href;
    private boolean hasSubMenu;
    private String parentKey;
    private List<MenuResponseDto> subMenus;
}
