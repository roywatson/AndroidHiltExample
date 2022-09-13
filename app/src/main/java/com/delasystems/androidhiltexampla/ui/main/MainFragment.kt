package com.delasystems.androidhiltexampla.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.delasystems.androidhiltexampla.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val directRequest = context?.filesDir

        val injectedRequest = viewModel.getFilesPath()

        val message = if(injectedRequest.absolutePath.equals(directRequest?.absolutePath)) {
            "SUCCESS:"
        } else {
            "FAILED:"
        }
        view?.findViewById<TextView>(R.id.message)?.text = message
        view?.findViewById<TextView>(R.id.message1)?.text = "deep=$injectedRequest"
        view?.findViewById<TextView>(R.id.message2)?.text = "frag's=$directRequest"

    }

}