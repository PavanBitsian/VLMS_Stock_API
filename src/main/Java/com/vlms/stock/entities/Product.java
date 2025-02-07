package com.vlms.stock.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "productName")
})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size (min=3, max = 120)
    private String productName;

    @NotBlank
    @Size(min=3, max = 20)
    private String brandName;

    @NotBlank
    @Size(min=3, max = 20)
    private String modelName;

    @NotBlank
    @Size(min=3, max = 20)
    private String material;

    @NotBlank
    @Size(min=3, max = 20)
    private String category;

    @NotNull
    private int size;

    @NotBlank
    @Size(min=2, max = 20)
    private String measuringUnit;

    @NotNull
    private int availableQuantity;
    @Size(min=0, max = 20)
    private String comments;

    public Product(String productName,String brandName, String modelName,String material,String category, int size, String measuringUnit, int availableQuantity) {
        this.productName = productName;
        this.brandName = brandName;
        this.modelName = modelName;
        this.material = material;
        this.category = category;
        this.size = size;
        this.measuringUnit = measuringUnit;
        this.availableQuantity = availableQuantity;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public void setMeasuringUnit(String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", brandName='" + brandName + '\'' +
                ", modelName='" + modelName + '\'' +
                ", material='" + material + '\'' +
                ", category='" + category + '\'' +
                ", size=" + size +
                ", measuringUnit='" + measuringUnit + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", comments='" + comments + '\'' +
                '}';
    }
}