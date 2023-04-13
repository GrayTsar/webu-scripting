package com.graytsar.definition

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.graytsar.definition.dto.ChapterDTO
import com.graytsar.definition.dto.NovelDTO
import com.graytsar.definition.dto.SearchDTO
import kotlinx.coroutines.delay
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.apache.commons.text.StringEscapeUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import kotlin.random.Random

interface Library {
    fun say(value: String)
    fun getDocument(url: String): Document
    fun postDocument(url: String): Document
    fun executeRequest(request: Request, url: String): Document
    fun parseHtml(text: String, url: String): Document
    fun createNovel(
        title: String,
        coverUrl: String,
        description: String,
        author: String,
        genres: String,
        tags: String,
        status: String,
        chapters: List<ChapterDTO>
    ): NovelDTO
    fun createChapters(): MutableList<ChapterDTO>
    fun addWebsiteChaptersToList(
        list: ArrayList<ChapterDTO>,
        link: String,
        title: String,
        text: String
    )
    fun createSearchResult(): MutableList<SearchDTO>
    fun addWebsiteSearchToList(
        list: ArrayList<SearchDTO>,
        link: String,
        title: String,
        imgSrc: String
    )
    fun reverseList(list: MutableList<ChapterDTO>)
    fun toJsonTree(text: String): JsonObject
    fun elementAsArray(element: JsonElement): JsonArray
    fun elementAsObject(element: JsonElement): JsonObject
    fun getFromObject(obj: JsonObject, name: String): JsonElement
    fun getFromObjectAsArray(element: JsonElement, index: Int): JsonElement
    fun getFromElementAsObject(element: JsonElement, name: String): JsonElement
    fun replaceString(text: String, pattern: String, value: String): String
    fun getFormBuilder(): MultipartBody.Builder
    fun getRequestBuilder(): Request.Builder
    fun decodeUnicode(text: String): String
    suspend fun delay(from: Long, until: Long)
}

class LibraryImpl(val client: OkHttpClient): Library {
    override fun say(value: String) {
        println(value)
    }

    override fun getDocument(url: String): Document {
        val request: Request = Request.Builder()
            .url(url)
            .build()

        val res = client.newCall(request).execute()
        return Jsoup.parse(res.body.string(), url)
    }

    override fun postDocument(url: String): Document {
        val body = "".toRequestBody(null)

        val request: Request = Request.Builder()
            .url(url)
            .method("POST", body)
            .build()

        val res = client.newCall(request).execute()
        return Jsoup.parse(res.body.string(), url)
    }

    override fun executeRequest(request: Request, url: String): Document {
        val res = client.newCall(request).execute()
        return Jsoup.parse(res.body.string(), url)
    }

    override fun parseHtml(text: String, url: String): Document {
        return Jsoup.parse(text, url)
    }

    override fun createNovel(
        title: String,
        coverUrl: String,
        description: String,
        author: String,
        genres: String,
        tags: String,
        status: String,
        chapters: List<ChapterDTO>
    ): NovelDTO = NovelDTO(
        title = title,
        coverUrl = coverUrl,
        description = description,
        author = author,
        genres = genres,
        tags = tags,
        status = status,
        chapters = chapters
    )

    override fun createChapters(): MutableList<ChapterDTO> {
        return ArrayList<ChapterDTO>()
    }

    override fun addWebsiteChaptersToList(
        list: ArrayList<ChapterDTO>,
        link: String,
        title: String,
        text: String
    ) {
        list.add(
            ChapterDTO(
                link = link,
                title = title,
                text = text
            )
        )
    }

    override fun createSearchResult(): MutableList<SearchDTO> {
        return ArrayList<SearchDTO>()
    }

    override fun addWebsiteSearchToList(
        list: ArrayList<SearchDTO>,
        link: String,
        title: String,
        imgSrc: String
    ) {
        list.add(
            SearchDTO(
                link = link,
                title = title,
                coverUrl = imgSrc
            )
        )
    }

    override fun reverseList(list: MutableList<ChapterDTO>) = list.reverse()

    override fun toJsonTree(text: String): JsonObject {
        return JsonParser.parseString(text).asJsonObject
    }

    override fun elementAsArray(element: JsonElement): JsonArray {
        return element.asJsonArray
    }

    override fun elementAsObject(element: JsonElement): JsonObject {
        return element.asJsonObject
    }

    override fun getFromObject(obj: JsonObject, name: String): JsonElement {
        return obj.get(name)
    }

    override fun getFromObjectAsArray(element: JsonElement, index: Int): JsonElement {
        return element.asJsonArray[index]
    }

    override fun getFromElementAsObject(element: JsonElement, name: String): JsonElement {
        return element.asJsonObject.get(name)
    }

    override fun replaceString(text: String, pattern: String, value: String): String {
        return text.replace(pattern, value)
    }

    override fun getFormBuilder(): MultipartBody.Builder {
        return MultipartBody.Builder().setType(MultipartBody.FORM)
    }

    override fun getRequestBuilder(): Request.Builder {
        return Request.Builder()
    }

    override fun decodeUnicode(text: String): String {
        return StringEscapeUtils.unescapeJava(text)
    }

    override suspend fun delay(from: Long, until: Long) {
        delay(Random.nextLong(from, until))
    }
}