package com.radek.kafkakotlin.controller

import com.radek.kafkakotlin.WorldService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
class WorldController @Autowired constructor(val worldService: WorldService) {

    @GetMapping("/newWorld")
    fun createNewWorld(){
        worldService.createNew()
    }

    @GetMapping("/getAllWorlds")
    fun getAllWorlds(model: ModelAndView): ModelAndView {
        model.addObject("worlds", worldService.getAll())
        model.viewName = "allWorlds"
        return model
    }

}