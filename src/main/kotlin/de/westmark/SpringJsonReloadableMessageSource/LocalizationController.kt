package de.westmark.springJsonReloadableMessageSource

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class LocalizationController(val localizationService: LocalizationService) {

    @GetMapping("/content/{key}")
    fun getText(@PathVariable key: String, @RequestParam(required = false) locale: Locale?): String =
        localizationService.getContent(key, locale?: Locale.GERMAN)

}