package com.example.secHandShop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private int reviewId;

	@Column(name = "content")
	private String content;

	@Column(name = "rating")
	private int rating;

	@Column(name = "review_date")
	private LocalDateTime reviewDate;

	@Column(name = "user_id")
	private int userId;

	@Column(name = "product_id")
	private int productId;

	public Review() {
		super();
	}

	public Review(int reviewId, String content, int rating, LocalDateTime reviewDate, int userId, int productId) {
		super();
		this.reviewId = reviewId;
		this.content = content;
		this.rating = rating;
		this.reviewDate = reviewDate;
		this.userId = userId;
		this.productId = productId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public LocalDateTime getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDateTime reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
