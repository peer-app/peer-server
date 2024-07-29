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

	private String memberKey;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "mentor_detail_id")
	private MentorDetail mentorDetail;

	@Builder
	public User(String name, String email, Role role, String phoneNumber, String profileImageUrl, String memberKey) {
		this.name = name;
		this.email = email;
		this.role = role;
		this.phoneNumber = phoneNumber;
		this.profileImageUrl = profileImageUrl;
		this.memberKey = memberKey;
	}

	public void UpdateMentorDetail(MentorDetail mentorDetail) {
		this.mentorDetail = mentorDetail;
	}

	public void UpdatePhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void UpdateRole(Role role) {
		this.role = role;
	}

	public void UpdateProfileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
	}
}
