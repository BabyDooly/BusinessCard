package com.sungmun.NoticeBoard.domain.member;

import java.util.List;

import javax.persistence.*;

import com.sungmun.NoticeBoard.domain.BaseTimeEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @Column(name = "member_id", nullable = false, length = 20)
    private String id;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_num", nullable = false)
    private int num;
    @Column(name = "member_password", nullable = false)
    private String password;
    @Column(name = "member_Name", length = 15, nullable = false)
    private String Name;
    @Column(name = "member_phone", length = 30, nullable = false)
    private String phone;
    @Column(name = "member_email", length = 50, nullable = false)
    private String email;
    @Column(name = "member_image", length = 150)
    private String image;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<MemberRole> roles;

}
