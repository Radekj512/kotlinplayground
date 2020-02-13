package com.radek.kafkakotlin.model

data class Person(val firstName: String, val lastName:String, val age: Int)

data class Flat(val area: Int, val people: List<Person>)

data class City(val name: String, val flats: List<Flat>)

data class Country(val nameAndCapital: Pair<String, City>, val cities: List<City>)

data class World(val countries: List<Country>)