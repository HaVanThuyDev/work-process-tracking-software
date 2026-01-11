package com.example.vn2_ht_student.repository.custome;

import com.example.vn2_ht_student.model.entity.Menu;
import java.util.List;

public interface  MenuCustomRepository {
    List<Menu> findRootMenus();
}
