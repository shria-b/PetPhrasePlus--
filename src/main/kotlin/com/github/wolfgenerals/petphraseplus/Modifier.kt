package com.github.wolfgenerals.petphraseplus

import com.github.wolfgenerals.petphraseplus.config.config

private fun String.endInner(endInner: String): String {
    val index = indexOfLast { it !in punctuations }
    return "${substring(0,index+1)}$endInner${substring(index+1)}"
}

fun String.replace(list:List<Replace>): String {
    var s = this
    list.forEach {
        s = this.replace(it.old,it.new)
    }
    return s
}


fun needModify(s: String): Boolean = config.enabled and !s.startsWith('/') and s.isNotBlank() or s.startsWith("/me")

fun modifyMessage(message: String): String =
    "${config.start}${message.map { if (it.isWhitespace() || it == ',' || it == '.' || it == '!' || it == '?') it else '喵' }.joinToString("")}${config.endInner}${config.endOuter}"

private val punctuations = listOf(
    '!', '?', '.', ';', ':', '(', ')', '~', '"', '\'', '[', ']', '{', '}',
    '！', '？', '。', '；', '：', '（', '）', '～', '“', '”', '‘', '’', '【', '】', '｛', '｝',
    '¡', '¿', '「', '」', '『', '』'
)
