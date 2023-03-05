package ecommerce.com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ecommerce.com.ecommerce.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
