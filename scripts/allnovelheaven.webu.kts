fun getChapter(): String {
    val document = getDocument(query)
    val documentText = document.selectFirst("div#chapter-content")?.select("p")
    return documentText?.toString() ?: "ERROR"
}

val result: Any? = when(type) {
    "search" -> {
        say("search $query")
        ""
    }
    "chapter" -> {
        say("chapter $query")
        getChapter()
    }
    "novel" -> {
        say("novel $query")
        ""
    }
    else -> {
        say("ight imma head out")
        null
    }
}
result