package com.advance.java.server.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by rAYMOND on 4/22/2016.
 */
@Entity
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private Category parentCategory;
    private List<Product> productByCategory;

    @OneToMany(mappedBy = "category")
    public List<Product> getProductByCategory() {
        return productByCategory;
    }

    public void setProductByCategory(List<Product> productByCategory) {
        this.productByCategory = productByCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId", nullable = false)
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "CategoryName", nullable = false, length = 255)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "CategoryDescription", nullable = true, length = 255)
    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    @ManyToOne
    @JoinColumn(name = "ParentCategory", nullable = true)
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryId != category.categoryId) return false;
        if (categoryName != null ? !categoryName.equals(category.categoryName) : category.categoryName != null)
            return false;
        if (categoryDescription != null ? !categoryDescription.equals(category.categoryDescription) : category.categoryDescription != null)
            return false;
        if (parentCategory != null ? !parentCategory.equals(category.parentCategory) : category.parentCategory != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = categoryId;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (categoryDescription != null ? categoryDescription.hashCode() : 0);
        result = 31 * result + (parentCategory != null ? parentCategory.hashCode() : 0);
        return result;
    }
}
