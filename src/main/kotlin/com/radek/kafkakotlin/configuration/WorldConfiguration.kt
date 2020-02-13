package com.radek.kafkakotlin.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class WorldConfiguration {

    @Value("\${max.country.count}")
    val MAX_COUNTRIES: Int? = null

    @Value("\${min.flat.area}")
    val MIN_FLAT_AREA: Int? = null

    @Value("\${max.flat.area}")
    val MAX_FLAT_AREA: Int? = null

    @Value("\${max.people.in.flat}")
    val MAX_PEOPLE_IN_FLAT: Int? = null

    @Value("\${min.flats.in.city}")
    val MIN_FLATS_NUMBER: Int? = null

    @Value("\${max.flats.in.city}")
    val MAX_FLATS_NUMBER: Int? = null

    @Value("\${min.cities.number}")
    val MIN_CITIES_NUMBER: Int? = null

    @Value("\${max.cities.number}")
    val MAX_CITIES_NUMBER: Int? = null

    @Value("\${countries.count}")
    val NUMBER_OF_COUNTRIES: Int? = null


}