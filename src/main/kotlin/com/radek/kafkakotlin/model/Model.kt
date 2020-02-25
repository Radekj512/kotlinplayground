package com.radek.kafkakotlin.model

import javax.persistence.*


@Entity
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long? = null,
        val firstName: String,
        val lastName:String,
        val age: Int)

@Entity
data class Flat(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long? = null,
        val area: Int,
        @OneToMany
        @JoinColumn(name = "FlatId")
        val people: List<Person>)

@Entity
data class City(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long? = null,
        val name: String,
        @OneToMany
        @JoinColumn(name="CityId")
        val flats: List<Flat>)

@Entity
data class Country(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long? = null,
        val name: String,
        @OneToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name="capital_id", nullable=false)
        val capital: City,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @JoinColumn(name="CountryId")
        val cities: List<City>)

@Entity
data class World(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id:Long? = null,
        @OneToMany
        @JoinColumn(name = "WorldId")
        val countries: List<Country>)