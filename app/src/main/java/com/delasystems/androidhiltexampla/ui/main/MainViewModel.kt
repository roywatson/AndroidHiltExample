package com.delasystems.androidhiltexampla.ui.main

import androidx.lifecycle.ViewModel
import com.delasystems.androidhiltexample.data.RoysRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(val repo: RoysRepository): ViewModel() {

    fun getFilesPath() : File {
        return repo.getFileDirectory()
    }
}