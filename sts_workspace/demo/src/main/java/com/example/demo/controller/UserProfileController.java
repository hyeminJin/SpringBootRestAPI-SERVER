package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserProfile;

@RestController
public class UserProfileController {
	
	private Map<String, UserProfile> userMap;
	
	@PostConstruct //클래스 만들면 제일먼저 실행됨
	public void init() {
		userMap = new HashMap<String, UserProfile>();
		userMap.put("1", new UserProfile("1", "홍길동", "111-1111", "서울시 강남구 대치1동"));
		userMap.put("2", new UserProfile("2", "홍길동", "111-1112", "서울시 강남구 대치2동"));
		userMap.put("3", new UserProfile("3", "홍길동", "111-1113", "서울시 강남구 대치3동"));
	}
	
	//get 조회
	@GetMapping("/user/{id}")
	public UserProfile getUserProfile(@PathVariable("id") String id) {
		return userMap.get(id);
	}
	
	//전체조회
	@GetMapping("/user/all")
	public List<UserProfile> getUserProfileList() {
		return new ArrayList<UserProfile>(userMap.values());
	}
	
	//put 생성
	@PutMapping("/user/{id}")
	public void putUserProfile(
			@PathVariable("id") String id,
			@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			@RequestParam("address") String address) {
		UserProfile userProfile = new UserProfile(id, name, phone, address);
		userMap.put(id, userProfile);
	}
	
	//post 수정
	@PostMapping("/user/{id}")
	public void postUserProfile(
			@PathVariable("id") String id,
			@RequestParam("name") String name,
			@RequestParam("phone") String phone,
			@RequestParam("address") String address) {
		UserProfile userProfile = userMap.get(id);
		userProfile.setName(name);
		userProfile.setPhone(phone);
		userProfile.setAddress(address);		
	}
	
	//delete 삭제
	@DeleteMapping("/user/{id}")
	public void deleteUserProfile(
			@PathVariable("id") String id) {
		userMap.remove(id);

	}
}
