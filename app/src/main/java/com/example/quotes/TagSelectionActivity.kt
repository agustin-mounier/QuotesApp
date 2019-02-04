package com.example.quotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.widget.ArrayAdapter
import com.example.quotes.mvp.BaseActivity
import com.example.quotes.presenters.TagSelectionPresenter
import com.example.quotes.views.TagSelectionView
import kotlinx.android.synthetic.main.tag_selection_layout.*


class TagSelectionActivity: BaseActivity<TagSelectionView, TagSelectionPresenter>(), TagSelectionView, SearchView.OnQueryTextListener {

    companion object {
        const val TAGS_EXTRA = "tags"
    }

    private lateinit var tags: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tag_selection_layout)
        tags = intent.getStringArrayListExtra("TAG_LIST")
        init()
    }

    override fun instantiatePresenter() {
        mPresenter = TagSelectionPresenter()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        TODO()
    }

    private fun init() {
        tag_selection_search.setOnQueryTextListener(this)
        suggested_tags.adapter = ArrayAdapter<String>(this, R.layout.suggested_tag_item, tags)
    }

    private fun returnToFeedWithTags(tags: ArrayList<String>) {
        val returnIntent = Intent()
        returnIntent.putStringArrayListExtra(TAGS_EXTRA, tags)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}