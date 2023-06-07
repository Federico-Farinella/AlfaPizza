package com.alfaproject.alfapizza.model

import android.content.Context
import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
class User(@IgnoredOnParcel var context: Context? = null): Parcelable {

}