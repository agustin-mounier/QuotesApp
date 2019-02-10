package com.example.quotes.presenters

import com.example.quotes.mvp.BasePresenter
import com.example.quotes.views.TagSelectionView

class TagSelectionPresenter: BasePresenter<TagSelectionView>() {

    private val selectedTags: MutableList<String> = mutableListOf()

    fun tagSelected(selectedTag: String) {
        selectedTags.add(selectedTag)
        mView!!.addSelectedTag(selectedTag)
    }

    fun applyTags() {
        mView!!.returnToFeedWithTags(selectedTags as ArrayList<String>)
    }
}