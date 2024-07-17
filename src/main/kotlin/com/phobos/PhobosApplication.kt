package com.phobos

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PhobosApplication

fun main(args: Array<String>) {
	runApplication<PhobosApplication>(*args)
}
