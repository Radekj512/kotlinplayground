package com.radek.kafkakotlin.generator

import com.github.javafaker.Faker
import com.radek.kafkakotlin.*
import com.radek.kafkakotlin.model.*
import java.util.stream.IntStream
import kotlin.random.Random
import kotlin.streams.toList

fun <T> getObj(obj: T): T {
    return obj
}

fun getRandomCountryList(): List<Country> =
        IntStream.rangeClosed(0, MAX_COUNTRIES!!)
                .mapToObj { CountryGenerator().getRandomCountry() }
                .toList()


val rnd = Random
val gen: Faker = Faker()

class PersonGenerator {

    fun getPerson(): Person = getObj(
            Person(
                    generateFirstName(),
                    generateLastName(),
                    generateAge()
            )
    )

    private fun generateFirstName() = gen.name().firstName()
    private fun generateLastName() = gen.name().lastName()
    private fun generateAge() = rnd.nextInt(0, 100)
}

class FlatGenerator {
    fun getFlat(): Flat = Flat(getRandomArea(), getRandomPeopleList())

    private fun getRandomPeopleList(): List<Person> =
            IntStream.range(1, rnd.nextInt(MAX_PEOPLE_IN_FLAT!!) + 1)
                    .mapToObj { PersonGenerator().getPerson() }
                    .toList()

    private fun getRandomArea(): Int = rnd.nextInt(MIN_FLAT_AREA!!, MAX_FLAT_AREA!!)

}

class CityGenerator {


    fun getRandomCity(capital: String?) =
            City(capital ?: getCityName(), getListOfFlats())

    private fun getListOfFlats(): List<Flat> =
            IntStream.range(0, rnd.nextInt(MAX_FLATS_NUMBER!!) + MIN_FLATS_NUMBER!!)
                    .mapToObj { FlatGenerator().getFlat() }
                    .toList()


    private fun getCityName(): String = gen.address().city()
}

class CountryGenerator {

    private val country = gen.country()

    fun getRandomCountry(): Country = Country(country.name(), getCapital(country.capital()), getCities())

    private fun getCities(): List<City> =
            IntStream.range(0, rnd.nextInt(MAX_CITIES_NUMBER!!) + MIN_CITIES_NUMBER!!)
                    .mapToObj { CityGenerator().getRandomCity(null) }
                    .toList()

    private fun getCapital(name: String?): City =
            CityGenerator().getRandomCity(name)
}

class WorldGenerator {
    fun generateWorld() = getRandomCountryList()

}