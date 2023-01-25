package com.example.menuexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.view.MenuItemCompat
import androidx.core.view.iterator

class MainActivity : AppCompatActivity() {
    val TAG = "MAIN_ACTIVITY";
    var counter = 0
    var menu: Menu? = null
    var isOnPrepareOptionsMenuCalledDirectly = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.example_menu, menu)
        customizeActionView(menu);
        return true
    }

    private fun customizeActionView(menu: Menu?) {
        if (menu == null)
            return
        setExpansionCloseListenerToMenuItem(menu)
        setButtonClickCallbackToMenuItem(menu)
    }

    private fun setButtonClickCallbackToMenuItem(menu: Menu) {
        var menuItem = menu?.findItem(R.id.menu_item_1)
        if(menuItem == null)
            return
        var actionProvider = MenuItemCompat.getActionProvider(menuItem) as CustomActionProvider
        if (actionProvider != null) {
            actionProvider.iActionViewButtonClick = object : IActionViewButtonClick {
                override fun onButtonClick(buttonName: String) {
                    Toast.makeText(applicationContext, buttonName + "clicked", Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }
    }

    private fun setExpansionCloseListenerToMenuItem(menu: Menu) {
        val expandListener = object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(p0: MenuItem): Boolean {
                changeOtherMenuItemVisibility(menu, false)
                return true
            }
            override fun onMenuItemActionCollapse(p0: MenuItem): Boolean {
                changeOtherMenuItemVisibility(menu, true)
                return true
            }
        }
        menu?.findItem(R.id.menu_item_1)?.setOnActionExpandListener(expandListener);
    }

    private fun changeOtherMenuItemVisibility(menu: Menu, isVisible: Boolean) {
        var idOfMenuItemWithCustomView :Int = R.id.menu_item_1
        for (i in 0 until menu.size()) {
            var menuItem = menu.getItem(i);
            if (menuItem.itemId != idOfMenuItemWithCustomView)
                menuItem.isVisible = isVisible
        }
    }


}