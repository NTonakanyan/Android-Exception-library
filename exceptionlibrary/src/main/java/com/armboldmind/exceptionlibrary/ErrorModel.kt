package com.armboldmind.exceptionlibrary

import android.os.Parcel
import android.os.Parcelable

class ErrorModel() : Parcelable {
    var key: String? = null
    var text: String? = null
    var className: String? = null
    var crashLine: Int = 0
    var manufacture: String? =null
    var deviceModel: String? =null

    constructor(parcel: Parcel) : this() {
        key = parcel.readString()
        text = parcel.readString()
        className = parcel.readString()
        manufacture = parcel.readString()
        deviceModel = parcel.readString()
        crashLine = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(key)
        parcel.writeString(text)
        parcel.writeString(className)
        parcel.writeString(manufacture)
        parcel.writeString(deviceModel)
        parcel.writeInt(crashLine)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ErrorModel> {
        override fun createFromParcel(parcel: Parcel): ErrorModel {
            return ErrorModel(parcel)
        }

        override fun newArray(size: Int): Array<ErrorModel?> {
            return arrayOfNulls(size)
        }
    }
}