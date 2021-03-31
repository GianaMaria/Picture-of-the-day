package com.example.pictureoftheday.model.entity
import com.google.gson.annotations.Expose

data class SunPosition (

	@Expose val x : Double,
	@Expose val y : Double,
	@Expose val z : Double
)