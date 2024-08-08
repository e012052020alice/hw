package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class MemberLogin {
	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@GeneratedValue(strategy = GenerationType.IDENTITY)//會需要一筆一筆插入
	@Getter
	@Setter
	private Integer memberId;//使用string，沒有Generator
	@Getter
	@Setter
	private String account;
	@Getter
	@Setter
	private String password;
		
}
