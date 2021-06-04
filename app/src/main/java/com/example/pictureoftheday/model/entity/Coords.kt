package com.example.pictureoftheday.model.entity
import com.google.gson.annotations.Expose

data class Coords (

	@Expose val centroid_coordinates : CentroidCoordinates,
	@Expose val dscovr_position : DscovrPosition,
	@Expose val lunar_position : LunarPosition,
	@Expose val sun_j2000_position : SunPosition,
	@Expose val attitude_quaternions : AttitudeQuaternions
)