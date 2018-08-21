package com.akelius.eventhubs.workshop.producer

import mu.KLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@SpringBootApplication
@EnableScheduling
class ProducerApplication

fun main(args: Array<String>) {
    runApplication<ProducerApplication>(*args)
}

@Component
class NewInvestmentSource {
    companion object : KLogging()

    @Scheduled(fixedDelay = 30000)
    fun sendNewInvestmentEvent() {
        logger.info { "Sending new investment event" }
    }
}