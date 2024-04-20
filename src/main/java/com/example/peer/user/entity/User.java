package com.example.peer.user.entity;

import com.example.peer.common.domain.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	private String name;

	private String email;

	private Role role;

	private String phoneNumber;

	private String profileImage;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mentor_detail_id")
	private MentorDetail mentorDetail;

	@Builder
	public User(String name, String email, Role role, String phoneNumber, String profileImage) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.profileImage = profileImage;
	}
}