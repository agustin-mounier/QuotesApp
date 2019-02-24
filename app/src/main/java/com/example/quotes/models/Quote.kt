package com.example.quotes.models

import android.os.Parcel
import android.os.Parcelable

data class Quote(val tags: List<String>, val quote: String, val lang: String, val author: String) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringList(tags)
        parcel.writeString(quote)
        parcel.writeString(lang)
        parcel.writeString(author)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Quote> {
        override fun createFromParcel(parcel: Parcel): Quote {
            return Quote(parcel)
        }

        override fun newArray(size: Int): Array<Quote?> {
            return arrayOfNulls(size)
        }
    }

}