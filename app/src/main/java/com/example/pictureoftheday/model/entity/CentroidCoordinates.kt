package com.example.pictureoftheday.model.entity
import com.google.gson.annotations.Expose

data class CentroidCoordinates (

	@Expose val lat : Double,
	@Expose val lon : Double
)