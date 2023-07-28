package de.westmark.SpringJsonReloadableMessageSource

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringJsonReloadableMessageSourceApplication {
	@Bean
	fun messageSource(): MessageSource {
		val messageSource = JsonReloadableMessageSource()
		messageSource.setPropertiesPersister(JsonPropertiesPersister())
		messageSource.setBasenames("file:data/i18n-json/content")
		messageSource.setCacheSeconds(30)
		return messageSource
	}

}


fun main(args: Array<String>) {
	runApplication<SpringJsonReloadableMessageSourceApplication>(*args)
}
