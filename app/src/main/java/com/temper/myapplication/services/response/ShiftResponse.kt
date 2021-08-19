package com.temper.myapplication.services.response

import com.temper.myapplication.utils.CurrencyUtil

class ShiftResponse {
    var data : ArrayList<JobDto> = ArrayList()
}

data class JobDto(
    var earnings_per_hour : EarningsPerHour? = null,
    var job : Job? = null,
    var starts_at : String?,
    var ends_at : String?,
    var distance : String?,
    var hourlyRate : String? = "${earnings_per_hour?.currency?.let {
        CurrencyUtil.getCurrencySymbol(
            it
        )
    }} ${earnings_per_hour?.amount?.let { it}}"
)

data class EarningsPerHour(
    var currency : String?,
    var amount : Double?
)

data class Job(
    var title : String?,
    var slug : String?,
    var links : Links,
    var report_at_address : Address,
    var category : Category
)

data class Category(
    var slug : String?
)

data class Links(
    var hero_380_image : String?
)

data class Address(
    var geo : Geo
)

data class Geo(
    var lat : Double,
    var lon : Double
)

