package com.graytsar.definition

interface Request {
    val type: String
    val query: String
}

data class RequestImpl(
    override val type: String,
    override val query: String
): Request
