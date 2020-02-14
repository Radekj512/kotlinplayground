package com.radek.kafkakotlin.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@Entity
data class Person(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long? = null,
        val firstName: String,
        val lastName:String,
        val age: Int)

@Entity
data class Flat(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long? = null,
        val area: Int,
        val people: List<Person>)

@Entity
data class City(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long? = null,
        val name: String,
        val flats: List<Flat>)

@Entity
data class Country(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long? = null,
        val name: String,
        val capital: City,
        val cities: List<City>)

@Entity
data class World(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id:Long? = null,
        val countries: List<Country>)