package com.radek.kafkakotlin.controller

import com.radek.kafkakotlin.WorldService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class WorldController @Autowired constructor(val worldService: WorldService) {

    @GetMapping("/newWorld")
    fun createNewWorld(){
        worldService.createNew()
    }

}