package com.example.vn2_ht_student.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DATA_SOURCE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataSource {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DATA_SOURCE_SEQ")
    @SequenceGenerator(name = "DATA_SOURCE_SEQ", sequenceName = "DATA_SOURCE_SEQ", allocationSize = 1)
    private Long id;
    @Lob
    @Column(name = "DATA")
    private byte[] data;

    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @PostPersist
    private void onPostPersist() {
        if (this.imageUrl == null) {
            this.imageUrl = "/api/main/public/image/" + this.id;
        }
    }
}