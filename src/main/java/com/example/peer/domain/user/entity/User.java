package com.example.peer.domain.user.entity;

import com.example.peer.common.domain.BaseTimeEntity;

import jakarta.persistence.*;
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

	@Enumerated(EnumType.STRING)
	private Role role;

	private String phoneNumber;

	private String profileImageUrl;

	private String socialId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mentor_detail_id")
	private MentorDetail mentorDetail;

	@Builder
	public User(String name, String email, Role role, String phoneNumber, String profileImageUrl, String socialId) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.profileImageUrl = profileImageUrl;
		this.socialId = socialId;
	}

	public void updateMentorDetail(MentorDetail mentorDetail) {
		this.mentorDetail = mentorDetail;
	}

	public void updatePhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void updateRole(Role role) {
		this.role = role;
	}

	public void updateSocialId(String socialId) {
		this.socialId = socialId;
	}

	public void updateProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
}
