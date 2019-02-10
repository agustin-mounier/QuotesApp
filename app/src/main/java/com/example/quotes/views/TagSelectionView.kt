package com.example.quotes.views

import com.example.quotes.mvp.BaseViewInterface

interface TagSelectionView: BaseViewInterface {

    fun addSelectedTag(tag: String)

    fun returnToFeedWithTags(tags: ArrayList<String>)
}