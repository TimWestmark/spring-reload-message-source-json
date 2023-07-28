package de.westmark.springJsonReloadableMessageSource

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.*

@Service
class LocalizationService(private val messageSource: MessageSource) {

    fun getContent(key: String, locale: Locale): String =
        getMessage(key, locale)


    private fun getMessage(
        code: String,
        locale: Locale,
        args: Array<Any>? = null,
        fallback: String? = null
    ): String =
        messageSource.getMessage(code, args, fallback ?: code.fallback(), locale)!!
}


private fun String.fallback(): String =
    "content_missing_$this"