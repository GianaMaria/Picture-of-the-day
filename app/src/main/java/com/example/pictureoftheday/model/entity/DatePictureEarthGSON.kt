package com.example.pictureoftheday.model.entity
import com.google.gson.annotations.Expose

data class DatePictureEarthGSON (

	@Expose val identifier : Long,
	@Expose val caption : String,
	@Expose val image : String,
	@Expose val version : Int,
	@Expose val centroidCoordinates : CentroidCoordinates,
	@Expose val dscovrPosition : DscovrPosition,
	@Expose val lunarPosition : LunarPosition,
	@Expose val sunPosition : SunPosition,
	@Expose val attitudeQuaternions : AttitudeQuaternions,
	@Expose val date : String,
	@Expose val coords : Coords
)