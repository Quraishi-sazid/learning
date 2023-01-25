package com.example.menuexample

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.core.view.ActionProvider

class CustomActionProvider(myContext: Context) : ActionProvider(myContext) {
    var iActionViewButtonClick:IActionViewButtonClick?=null;

    override fun onCreateActionView(): View {
        var layoutInflater = LayoutInflater.from(context)
        var view = layoutInflater.inflate(R.layout.custom_action_view, null)
        setButtonCLickListener(view)
        return view
    }

    private fun setButtonCLickListener(view: View?) {
        view?.findViewById<Button>(R.id.button1)?.setOnClickListener {
            iActionViewButtonClick?.onButtonClick("Button 1")
        }
        view?.findViewById<Button>(R.id.button2)?.setOnClickListener {
            iActionViewButtonClick?.onButtonClick("Button 2")
        }
    }
}