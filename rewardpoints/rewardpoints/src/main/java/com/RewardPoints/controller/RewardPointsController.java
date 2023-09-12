package com.RewardPoints.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RewardPoints.Response.ApiResponse;
import com.RewardPoints.model.RewardPoints;
import com.RewardPoints.services.RewardPointsServices;

import jakarta.validation.Valid;
@RestController
public class RewardPointsController {

	@Autowired
	private RewardPointsServices rewardPointsServices;
	
//	get all user
	@GetMapping("/getRewardPointsById/{id}")
	public ResponseEntity<ApiResponse> getRewardPointsById(@Valid @PathVariable Integer id){
		this.rewardPointsServices.getRewardPointsById(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User Id Found Successfully", true),HttpStatus.OK);	}
	
	@PostMapping("/addRewardPointsById/{id}/{points}")
	public ResponseEntity<ApiResponse> addRewardPointById(@PathVariable Integer id,@PathVariable Integer points ) {
		return new ResponseEntity<ApiResponse>(new ApiResponse("Reward point updated successfully", true),HttpStatus.OK);	
	}

	
}
