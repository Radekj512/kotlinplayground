package com.radek.kafkakotlin

import com.radek.kafkakotlin.commons.messages.KafkaMessage
import com.radek.kafkakotlin.model.World

class WorldPersistenceEvent(val reciver:String, val payload: World, val worldName: String): KafkaMessage, PersistenceEvent {
    override fun getName(): String {
        return worldName
    }

}
