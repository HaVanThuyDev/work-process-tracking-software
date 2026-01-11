package com.example.vn2_ht_student.service.Impl;

import com.example.vn2_ht_student.model.dto.reponse.MenuResponseDto;
import com.example.vn2_ht_student.model.entity.Menu;
import com.example.vn2_ht_student.repository.MenuRepository;
import com.example.vn2_ht_student.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public List<MenuResponseDto> getMenusByCurrentUser() {
        return menuRepository.findRootMenus().stream().map(this::toDto).toList();
    }
    private MenuResponseDto toDto(Menu menu) {
        MenuResponseDto dto = new MenuResponseDto();
        dto.setKey(menu.getMenuKey());
        dto.setLabel(menu.getLabel());
        dto.setHref(menu.getHref());
        dto.setParentKey(menu.getParent() != null ? menu.getParent().getMenuKey() : "");
        if (menu.getChildren() != null && !menu.getChildren().isEmpty()) {
            dto.setHasSubMenu(true);
            dto.setSubMenus(menu.getChildren().stream().filter(Menu::getActive).sorted(Comparator.comparing(Menu::getDisplayOrder)).map(this::toDto).toList());
        } else {
            dto.setHasSubMenu(false);
            dto.setSubMenus(List.of());
        }
        return dto;
    }
}
