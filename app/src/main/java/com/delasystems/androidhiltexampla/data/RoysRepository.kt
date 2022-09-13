package com.delasystems.androidhiltexample.data

import java.io.File
import javax.inject.Inject

class RoysRepository @Inject constructor(val filer: RoyFiler) {

    fun getFileDirectory(): File {
        return filer.getFile()
    }
}