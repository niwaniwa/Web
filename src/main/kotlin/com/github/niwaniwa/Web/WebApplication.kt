package com.github.niwaniwa.Web

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@SpringBootApplication
class WebApplication

	fun main(args: Array<String>) {
		runApplication<WebApplication>(*args)
	}




