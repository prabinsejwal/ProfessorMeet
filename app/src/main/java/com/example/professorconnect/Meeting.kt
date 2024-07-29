package com.example.professorconnect

import android.os.Parcel
import android.os.Parcelable

data class Meeting(
    val subject: String,
    val description: String,
    val timeSlot: String,
    val type: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(subject)
        parcel.writeString(description)
        parcel.writeString(timeSlot)
        parcel.writeString(type)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Meeting> {
        override fun createFromParcel(parcel: Parcel): Meeting {
            return Meeting(parcel)
        }

        override fun newArray(size: Int): Array<Meeting?> {
            return arrayOfNulls(size)
        }
    }
}
