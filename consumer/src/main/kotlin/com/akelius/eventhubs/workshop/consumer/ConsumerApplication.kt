package com.akelius.eventhubs.workshop.consumer

import mu.KLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Component
import java.util.*

@SpringBootApplication
class ConsumerApplication

fun main(args: Array<String>) {
    runApplication<ConsumerApplication>(*args)
}

@Component
class NewInvestmentSink {
    companion object: KLogging()

    @KafkaListener(topics = ["new-investments"])
    fun newInvestment(newInvestmentEvent: String) {
        logger.info { "There is a new investment: $newInvestmentEvent" }
    }
}

data class NewInvestmentEvent(
        val eventId: UUID,
        val type: String
)
