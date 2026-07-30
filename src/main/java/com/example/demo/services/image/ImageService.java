package com.example.demo.services.image;

import com.example.demo.dto.ImageDto;
import com.example.demo.entities.Image;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ImageRepository;
import com.example.demo.services.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService implements IImageService{

    @Autowired
    private ImageRepository imageRepository ;
    @Autowired
    private IProductService productService ;


    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Image not found with id :" + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete , ()->{
            throw new RuntimeException("image not found with this id " + id);
        });
    }

    @Override
    public List<ImageDto> saveImage(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDto> savedImagesDtos = new ArrayList<>();
        for (MultipartFile file : files){
            try {
                Image image = new Image();
                image.setFileName(file.getOriginalFilename());
                image.setFileType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api//v1/images/image/download/" ;
                String downloadUrl = buildDownloadUrl + image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl + savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                imageDto.setFileName(savedImage.getFileName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                savedImagesDtos.add(imageDto);

            }catch( SQLException | IOException e){
                throw new RuntimeException(e.getMessage());
            }

        }
        return savedImagesDtos;
    }

    @Override
    public void updateImage(MultipartFile file, Long idImage) {
        Image image = getImageById(idImage);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(file.getContentType());
            image.setImage(new SerialBlob(file.getBytes()));

        } catch(IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
