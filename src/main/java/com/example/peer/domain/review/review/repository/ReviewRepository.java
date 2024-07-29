package com.example.peer.domain.review.review.repository;

import com.example.peer.domain.review.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
