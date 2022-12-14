package com.menaces.fate2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.*
import androidx.lifecycle.ViewModelProvider

class ContinueFragment : Fragment() {

    // continue button for next screen (updated narration/img)
    lateinit var continueBtn: Button

    // create object of SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_continue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
//        Log.d("COUNTER AT LOAD", model.returnCounter().toString())

        // button functionality
        continueBtn = view.findViewById(R.id.continue_button)
        continueBtn.setOnClickListener {
            if (model.returnCounter() != 0) {
                // update narration information
                model.updateScreen()

                // switch buttons if necessary
                if (model.isChoiceScreen()) {
                    switchScreens()
                }
            } else {
                model.updateScreen()
            }
            // increment value of continue always 1
            model.incrementCounter(1)
        }
    }

    private fun switchScreens () {
        activity?.supportFragmentManager?.commit {
            val choicesFragment = ChoicesFragment()
            replace(R.id.fragmentContainer, choicesFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}
