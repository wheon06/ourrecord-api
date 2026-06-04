package com.wheon.ourrecord

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@ConfigurationPropertiesScan
@SpringBootApplication
class OurrecordApiApplication

fun main(args: Array<String>) {
    runApplication<OurrecordApiApplication>(*args)
}
