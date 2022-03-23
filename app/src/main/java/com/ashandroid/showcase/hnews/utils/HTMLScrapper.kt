package com.ashandroid.showcase.hnews.utils

import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import java.lang.Exception

class HTMLScrapper {

    fun parseContent(url: String, callback: (String?) -> Unit, totalWords: Int = 50) {

        try {
            val doc = Jsoup.connect(url).get()
            val body = doc.body()
            var wcount = 0
            var lineNo = 0
            var content = ""

            val strBuilder = StringBuilder()
            body.allElements.forEach {
                val paragraph = it.getElementsByTag("p").text()
                //skip the first paragraph
                if (lineNo > 0) {
                    strBuilder.appendLine(paragraph)
                    wcount += countWords(paragraph)
                } else {
                    val firstCount = countWords(paragraph)
                    if (firstCount > 20) {
                        strBuilder.appendLine(paragraph)
                        wcount += countWords(paragraph)
                    }
                }

                if (wcount >= totalWords) {
                    return@forEach
                }
                lineNo++
            }
            callback(firstNWords(strBuilder.toString(), totalWords))
        }catch (e: HttpStatusException){
            callback("")
        }catch (e: Exception){
            callback("")
        }

    }

    private fun firstNWords(content: String, totalWords: Int): String? {
        val wordsList = content.split(" ")
        val possibleWords = if (wordsList.size < totalWords) wordsList.size else totalWords
        val neededWordsList = wordsList.subList(0, possibleWords)
        return neededWordsList.joinToString(" ").capitalize()
    }

    fun countWords(paragraph: String): Int {
        return paragraph.split(" ").size
    }
}