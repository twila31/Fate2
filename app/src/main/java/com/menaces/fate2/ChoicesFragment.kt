package com.menaces.fate2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class ChoicesFragment : Fragment() {

    lateinit var leftBtn: Button
    lateinit var rightBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choices, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // references for buttons
        leftBtn = view.findViewById(R.id.left_button)
        rightBtn = view.findViewById(R.id.right_button)

        // update button text
        updateButtonText()

        val model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
//        Log.d("OLD COUNTER:", model.returnCounter().toString())

        // button functionality
        leftBtn.setOnClickListener {
            val increment = model.getIncrementVal(true)
//            Log.d("INCREMENT:", increment.toString())

//            val counter = model.incrementCounter(increment)
//            Log.d("NEW COUNTER:", counter.toString())
            model.incrementCounter(1)

            updateContent()
        }
        rightBtn.setOnClickListener {
            val increment = model.getIncrementVal(false)
//            Log.d("INCREMENT:", increment.toString())

//            val counter = model.incrementCounter(increment)
//            Log.d("NEW COUNTER:", counter.toString())
            model.incrementCounter(1)

            updateContent()
        }
    }

    private fun updateContent() {
        val model = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        model.updateScreen()

        // TODO: update data to data to be displayed next (counter, get from list)

        // TODO: check if the screen to be displayed is a choice screen (using Screen object)

        // TODO: if condition
        // switch screens if necessary
        activity?.supportFragmentManager?.commit {
            val continueFragment = ContinueFragment()
            replace(R.id.fragmentContainer, continueFragment)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    private fun updateButtonText() {
        // button text changes
        val model = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        model.leftText.observe(viewLifecycleOwner, Observer {
            // updating left button text
            leftBtn.text = it
        })

        model.rightText.observe(viewLifecycleOwner, Observer {
            // updating right button text
            rightBtn.text = it
        })
    }
}