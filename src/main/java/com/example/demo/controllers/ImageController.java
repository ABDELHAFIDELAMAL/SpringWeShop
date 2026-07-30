package com.example.demo.controllers;

import com.example.demo.dto.ImageDto;
import com.example.demo.entities.Image;
import com.example.demo.response.ApiResponse;
import com.example.demo.services.category.ICategoryService;
import com.example.demo.services.image.IImageService;
import com.example.demo.services.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@RestController
@RequestMapping(path = "${api.prefix}/images")
public class ImageController {
    private static final int INTERNAT_SERVER_ERROR = 401;
    @Autowired
    private IImageService imageService ;
    @Autowired
    private IProductService productService;
    @Autowired
    private ICategoryService categoryService;

    @PostMapping(path = "/upload")
    public ResponseEntity<ApiResponse> saveImages(@RequestParam List<MultipartFile> files , @RequestParam Long productid) {
        try {
            List<ImageDto> imageDtos = imageService.saveImage(files , productid);
            return ResponseEntity.ok(new ApiResponse("Upload seccess!" , imageDtos));
        } catch (IOException | SQLException e) {
            return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Upload faild!" , e.getMessage()));
        }
    }

    @GetMapping(path = "/image/download/{imageId}")
    public ResponseEntity<Resource> downloadUrl(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1 , (int) image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment ; filename = \"" + image.getFileType() + "\"")
                .body(resource);
    }

    @PutMapping(path = "/image/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId , @RequestBody MultipartFile file){
        try{
            Image image = imageService.getImageById(imageId);
            if(image != null){
                imageService.updateImage(file , imageId);
                return ResponseEntity.ok().body(new ApiResponse("Update Seccess!" , null));
            }
        }catch (RuntimeException e){
            int NOT_FOUND = 403 ;
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("Upload faild!" ,INTERNAT_SERVER_ERROR));
    }


    @DeleteMapping(path = "/image/{imageId}/delete")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId ){
        try{
            Image image = imageService.getImageById(imageId);
            if(image != null){
                imageService.deleteImageById(imageId);
                return ResponseEntity.ok().body(new ApiResponse("delete Seccess!" , null));
            }
        }catch (RuntimeException e){
            int NOT_FOUND = 403 ;
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
        return ResponseEntity.status(INTERNAT_SERVER_ERROR).body(new ApiResponse("delete faild!" ,INTERNAT_SERVER_ERROR));
    }

}
