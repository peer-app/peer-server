package com.example.peer.domain.user.entity;

import com.example.peer.domain.schedule.entity.ScheduleRule;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MentorDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mentor_detail_id")
	private Long id;

	private String nickname;

	private String position;

	private String introduction;

	private Boolean isAccepted;

	private String openTalkLink;

	@Enumerated(EnumType.STRING)
	@ElementCollection
	@CollectionTable(name = "mentor_keywords",joinColumns = @JoinColumn(name = "mentor_detail_id"))
	private List<Keyword> keywords = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "schedule_create_rule_id")
	private ScheduleRule scheduleRule;

	@Builder
	public MentorDetail(String nickname, String position, String introduction, String openTalkLink, List<Keyword> keywords) {
		this.nickname = nickname;
		this.position = position;
		this.introduction = introduction;
		this.openTalkLink = openTalkLink;
		this.keywords = keywords;
		this.isAccepted = Boolean.TRUE;
	}

	public void updateIsAccepted(Boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public void updateScheduleRule(ScheduleRule scheduleRule) {
		this.scheduleRule = scheduleRule;
	}

	public void updateMentorDetail(String nickname, String position, String introduction, String openTalkLink, List<Keyword> keywords) {
		this.nickname = nickname;
		this.position = position;
		this.introduction = introduction;
		this.openTalkLink = openTalkLink;
		this.keywords = keywords;
	}
}
