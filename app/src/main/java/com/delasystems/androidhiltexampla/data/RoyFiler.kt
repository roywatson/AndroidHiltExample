package com.delasystems.androidhiltexample.data

import android.content.Context
import java.io.File
import javax.inject.Inject

class RoyFiler @Inject constructor(val context: Context) {

    var myFile: File = context.filesDir

    fun getFile(): File {
        return myFile
    }

}