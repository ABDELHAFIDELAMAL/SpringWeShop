package com.example.demo.services.image;

import com.example.demo.dto.ImageDto;
import com.example.demo.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDto> saveImage(List<MultipartFile> files , Long productId) throws SQLException, IOException;
    void updateImage(MultipartFile file , Long idImage);
}
