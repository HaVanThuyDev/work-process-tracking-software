package com.example.vn2_ht_student.repository.custome;

import com.example.vn2_ht_student.model.entity.Menu;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuCustomRepositoryImpl implements MenuCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Menu> findRootMenus() {
        return entityManager.createQuery("""
            SELECT db
            FROM Menu db
            WHERE db.active = true
              AND db.parent IS NULL
            ORDER BY db.displayOrder
        """, Menu.class).getResultList();
    }
}
