package com.example.vn2_ht_student.helper;

import com.example.vn2_ht_student.model.entity.DataSource;
import com.example.vn2_ht_student.repository.DataSourceRepository;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MediaStorageService {
    private final DataSourceRepository dataSourceRepository;

    /**
     * Chuyển Base64 ảnh → lưu DB → trả về IMAGE_URL
     */
    public String saveBase64Image(String base64Image) {

        // 1. Bỏ header nếu có: data:image/png;base64,...
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }

        // 2. Decode base64 → byte[]
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);

        // 3. Lưu DATA_SOURCE
        DataSource dataSource = new DataSource();
        dataSource.setData(imageBytes);
        dataSource = dataSourceRepository.save(dataSource);

        // 4. Sinh URL theo ID
        String imageUrl = "/api/main/public/image/" + dataSource.getId();
        dataSource.setImageUrl(imageUrl);

        // 5. Update lại DB
        dataSourceRepository.save(dataSource);

        return imageUrl;
    }
}
