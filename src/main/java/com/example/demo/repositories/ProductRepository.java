package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ترجع قائمة منتجات لأن التصنيف فيه بزاف ديال المنتجات
    List<Product> findByCategoryName(String category);

    // ترجع قائمة منتجات لأن الماركة الواحدة عندها بزاف المنتجات
    List<Product> findByBrand(String brand);

    // ترجع قائمة بالمنتجات حسب التصنيف والماركة
    List<Product> findByCategoryNameAndBrand(String category, String brand);

    // ترجع قائمة المنتجات
    List<Product> findByBrandAndName(String brand, String name);

    // إذا كان اسم المنتج فريد (Unique)، تقدر تخليها Product أو تعيد List
    List<Product> findByName(String name);

    // حساب عدد المنتجات فـ تصنيف معين
    Long countByCategoryName(String category); // من الأفضل استخدام Long للعد بدل int
}