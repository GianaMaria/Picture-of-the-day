package com.example.pictureoftheday.model.entity

import com.google.gson.annotations.Expose

data class AttitudeQuaternions(

	@Expose val q0: Double,
	@Expose val q1: Double,
	@Expose val q2: Double,
	@Expose val q3: Double
)