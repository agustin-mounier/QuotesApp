package com.example.quotes

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.*
import android.view.Menu.NONE
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.quotes.BitmapUtils.Companion.addTextToBitmap
import com.example.quotes.models.Quote
import com.example.quotes.mvp.BaseActivity
import com.example.quotes.presenters.EditQuotesPresenter
import com.example.quotes.views.EditQuotesView
import kotlinx.android.synthetic.main.edit_quotes_layout.*
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.widget.SeekBar
import android.widget.Toast

class EditQuotesActivity : BaseActivity<EditQuotesView, EditQuotesPresenter>(), EditQuotesView {

    companion object {
        const val SAVE_QUOTE_ID = 10
    }

    override fun createPresenter(): EditQuotesPresenter {
        return EditQuotesPresenter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_quotes_layout)
        val quote = intent.getParcelableExtra<Quote>(QuotesFeedActivity.QUOTE_SELECTED_EXTRA)
        mPresenter.initActivty(quote)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menu!!.add(NONE, SAVE_QUOTE_ID, NONE, "Save Quote")
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)
        when (item!!.itemId) {
            SAVE_QUOTE_ID -> {
                testSaveImage()
            }
        }
        return true
    }

    override fun setUp(quote: Quote) {
        quote_text.isDrawingCacheEnabled = true
        quote_text.text = quote.quote
        quote_text.tag = "sans-serif"
        quote_author.isDrawingCacheEnabled = true
        quote_author.text = quote.author

        font_button.setOnClickListener {
            mPresenter.showFonts()
        }

        background_button.setOnClickListener {
            mPresenter.showBackgrounds()
        }

        text_button.setOnClickListener {
            mPresenter.showTextOptions()
        }

        color_button.setOnClickListener {
            mPresenter.showColorOptions()
        }

        size_button.setOnClickListener {
            mPresenter.showSizeOptions()
        }

        font_button.isChecked = true
        mPresenter.showFonts()
    }

    override fun removeCurrentOptions() {
        radio_group_options_container.removeAllViews()
    }

    override fun setUpFontOptions(fonts: Array<String>) {

        val layoutInflater = LayoutInflater.from(this)
        val radioButtonGroup = layoutInflater.inflate(
            R.layout.radio_button_group_layout,
            radio_group_options_container,
            false
        ) as RadioGroup
        for (font in fonts) {
            val fontOption =
                layoutInflater.inflate(R.layout.radio_button_option, radioButtonGroup, false) as RadioButton
            val typeFace = Typeface.create(font, Typeface.NORMAL)
            fontOption.typeface = typeFace
            fontOption.setOnClickListener {
                quote_text.typeface = typeFace
                quote_text.tag = font
                quote_author.typeface = typeFace
            }
            fontOption.id = fonts.indexOf(font)
            radioButtonGroup.addView(fontOption)
        }
        radioButtonGroup.check(0)
        radio_group_options_container.addView(radioButtonGroup)
    }

    @SuppressLint("ResourceType")
    override fun setUpTextOptions(textStyles: Map<String, Int>, textAlignments: Map<String, Int>) {
        val groupsContainer = LinearLayout(this)
        groupsContainer.orientation = LinearLayout.HORIZONTAL

        val layoutInflater = LayoutInflater.from(this)
        val textStylesGroup =
            layoutInflater.inflate(R.layout.radio_button_group_layout, groupsContainer, false) as RadioGroup
        for (style in textStyles) {
            val quoteOption =
                LayoutInflater.from(this).inflate(R.layout.radio_button_option, textStylesGroup, false) as RadioButton
            val typeFace = Typeface.create(quote_text.tag as String, style.value)
            quoteOption.text = style.key
            quoteOption.typeface = typeFace
            quoteOption.id = textStyles.keys.indexOf(style.key)
            quoteOption.setOnClickListener {
                quote_text.typeface = typeFace
                quote_author.typeface = typeFace
            }
            textStylesGroup.addView(quoteOption)
        }
        textStylesGroup.check(0)
        groupsContainer.addView(textStylesGroup)

        val textAlignmentsGroup =
            layoutInflater.inflate(R.layout.radio_button_group_layout, groupsContainer, false) as RadioGroup
        for (textAlignment in textAlignments) {
            val quoteOption =
                LayoutInflater.from(this).inflate(
                    R.layout.radio_button_option,
                    textAlignmentsGroup,
                    false
                ) as RadioButton
            quoteOption.text = textAlignment.key
            quoteOption.setOnClickListener {
                quote_text.gravity = textAlignment.value
            }
            quoteOption.id = textAlignments.keys.indexOf(textAlignment.key)
            textAlignmentsGroup.addView(quoteOption)
        }
        textAlignmentsGroup.check(0)
        groupsContainer.addView(textAlignmentsGroup)

        radio_group_options_container.addView(groupsContainer)
    }

    override fun setUpBackgroundOptions(backgrounds: Array<String>) {
        val layoutInflater = LayoutInflater.from(this)
        val radioButtonGroup = layoutInflater.inflate(
            R.layout.radio_button_group_layout,
            radio_group_options_container,
            false
        ) as RadioGroup
        for (bg in backgrounds) {
            val backgroundOption =
                layoutInflater.inflate(R.layout.radio_button_image_option, radioButtonGroup, false) as RadioButton
            backgroundOption.setBackgroundResource(R.drawable.quote_background_1_option)
            backgroundOption.setOnClickListener {
                quote_background.setImageDrawable(getDrawable(this, bg))
            }
            backgroundOption.id = backgrounds.indexOf(bg)
            radioButtonGroup.addView(backgroundOption)
        }
        radioButtonGroup.check(0)
        radio_group_options_container.addView(radioButtonGroup)
    }

    override fun setUpColorOptions(colors: Array<String>) {
        val layoutInflater = LayoutInflater.from(this)
        val radioButtonGroup = layoutInflater.inflate(
            R.layout.radio_button_group_layout,
            radio_group_options_container,
            false
        ) as RadioGroup
        for (color in colors) {
            val colorOption =
                layoutInflater.inflate(R.layout.radio_button_option, radioButtonGroup, false) as RadioButton
            val colorInt = Color.parseColor(color)
            colorOption.setBackgroundColor(colorInt)
            colorOption.text = ""
            colorOption.setOnClickListener {
                quote_text.setTextColor(colorInt)
                quote_author.setTextColor(colorInt)
            }
            colorOption.id = colors.indexOf(color)
            radioButtonGroup.addView(colorOption)
        }
        radioButtonGroup.check(0)
        radio_group_options_container.addView(radioButtonGroup)
    }

    override fun setUpSizeOptions() {
        val minValue = 10
        text_size_seek_bar.visibility = View.VISIBLE
        text_size_seek_bar.progress = 14
        text_size_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress >= 10) {
                    quote_text.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress.toFloat())
                    quote_author.setTextSize(TypedValue.COMPLEX_UNIT_SP, progress.toFloat())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) { }

            override fun onStopTrackingTouch(seekBar: SeekBar) { }
        })


    }

    private fun testSaveImage() {
        val bgBmp = (quote_background.drawable as BitmapDrawable).bitmap
        val combinedBmp = addTextToBitmap(bgBmp, quote_background, quote_text, quote_author)

        quote_background.setImageBitmap(combinedBmp)


        quote_text.text = ""
        quote_author.text = ""
    }

    fun getDrawable(c: Context, ImageName: String): Drawable {
        val resources = c.resources
        return resources.getDrawable(resources.getIdentifier(ImageName, "drawable", c.packageName))
    }
}