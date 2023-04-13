package com.graytsar.definition.dto

data class NovelDTO(
    var title: String,
    var coverUrl: String,
    var description: String,
    var author: String,
    var genres: String,
    var tags: String,
    var status: String,
    var chapters: List<ChapterDTO>
)