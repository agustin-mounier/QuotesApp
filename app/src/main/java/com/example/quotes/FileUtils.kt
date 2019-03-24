package com.example.quotes

import android.content.Context
import com.example.quotes.models.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

class FileUtils {

    companion object {
        const val FILE_NAME = "/favorite_quotes"


        fun saveQuotes(context: Context, quotes: MutableSet<Quote>) {
            val absolutePath = context.filesDir.absolutePath
            val quotesFile = File(absolutePath + FILE_NAME)
            if (quotesFile.exists()) {
                quotesFile.delete()
            }

            val fos = FileOutputStream(absolutePath + FILE_NAME, false)
            fos.write(transformToJsonByteArray(quotes))
            fos.close()
        }

        fun loadFavoriteQuotes(context: Context): MutableSet<Quote> {
            val absolutePath = context.filesDir.absolutePath
            val fis: FileInputStream
            try {
                fis = FileInputStream(absolutePath + FILE_NAME)
            } catch (e: FileNotFoundException) {
                return mutableSetOf()
            }

            val favoriteQuotesJson = String(fis.readBytes())
            fis.close()

            return transformToSet(favoriteQuotesJson)
        }

        private fun transformToJsonByteArray(quotes: MutableSet<Quote>): ByteArray {
            val gson = Gson()
            return gson.toJson(quotes.toList()).toByteArray()
        }

        private fun transformToSet(json: String): MutableSet<Quote> {
            val gson = Gson()
            val listType = object : TypeToken<MutableSet<Quote>>() {}.type
            return gson.fromJson(json, listType) as MutableSet<Quote>
        }
    }
}