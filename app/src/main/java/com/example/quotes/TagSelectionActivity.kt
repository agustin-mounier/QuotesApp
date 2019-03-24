package com.example.quotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.TextView
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

    override fun createPresenter(): TagSelectionPresenter {
        return TagSelectionPresenter()
    }

    override fun addSelectedTag(tag: String) {
        val textView = TextView(this)
        textView.text = tag
        selected_tags.addView(textView)
        apply_button.visibility = View.VISIBLE
    }

    override fun returnToFeedWithTags(tags: ArrayList<String>) {
        val returnIntent = Intent()
        returnIntent.putStringArrayListExtra(TAGS_EXTRA, tags)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        (suggested_tags.adapter as ArrayAdapter<*>).filter.filter(newText)
        return true
    }

    private fun init() {
        tag_selection_search.setOnQueryTextListener(this)
        val arrayAdapter = ArrayAdapter<String>(this, R.layout.suggested_tag_item, tags)
        suggested_tags.adapter = arrayAdapter
        suggested_tags.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selectedTag = arrayAdapter.getItem(position)
            mPresenter.tagSelected(selectedTag)
        }
        apply_button.setOnClickListener { mPresenter.applyTags() }
    }
}