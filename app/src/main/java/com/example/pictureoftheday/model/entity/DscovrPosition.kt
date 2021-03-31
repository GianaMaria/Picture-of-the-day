package com.example.pictureoftheday.model.entity
import com.google.gson.annotations.Expose

data class DscovrPosition (

	@Expose val x : Double,
	@Expose val y : Double,
	@Expose val z : Double
)