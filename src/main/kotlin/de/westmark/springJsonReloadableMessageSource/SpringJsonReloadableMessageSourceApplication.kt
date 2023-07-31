package de.westmark.springJsonReloadableMessageSource

import de.westmark.springjsonrelodableMessageSource.JsonPropertiesPersister
import de.westmark.springjsonrelodableMessageSource.JsonReloadableResourceBundleMessageSource
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringJsonReloadableMessageSourceApplication {
	@Bean
	fun messageSource(): MessageSource {
//	Either this for xml
//		val messageSource = ReloadableResourceBundleMessageSource()


//	Or this for Json
		val messageSource = JsonReloadableResourceBundleMessageSource()

//	This is for all
		messageSource.setBasenames("file:data/i18n-json/content")
		messageSource.setCacheSeconds(30)
		return messageSource
	}

}


fun main(args: Array<String>) {
	runApplication<SpringJsonReloadableMessageSourceApplication>(*args)
}
