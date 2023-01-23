package com.example.menuexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    val TAG = "MAIN_ACTIVITY";
    var counter = 0
    var menu:Menu?=null
    var isOnPrepareOptionsMenuCalledDirectly = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setButtonClick()
    }

    private fun setButtonClick() {
        findViewById<Button>(R.id.redraw_menu_item).setOnClickListener {
            Log.v(TAG, "calling invalidateOptionsMenu")
            invalidateOptionsMenu()
        }
        findViewById<Button>(R.id.call_on_prepare_menu).setOnClickListener {
            isOnPrepareOptionsMenuCalledDirectly = true
            onPrepareOptionsMenu(menu)
            isOnPrepareOptionsMenuCalledDirectly = false
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        Log.v(TAG,"onPrepareOptionsMenu")
       if(isOnPrepareOptionsMenuCalledDirectly){
           menu?.findItem(R.id.dynamically_added_menu_id)?.isVisible = false // editing menu item
       }
        return super.onPrepareOptionsMenu(menu)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.v(TAG,"onCreateOptionsMenu")
        this.menu = menu
        menuInflater.inflate(R.menu.example_menu,menu)
        var menuItem = menu?.add(0,R.id.dynamically_added_menu_id,0,"dynamic item" + ++counter)
        menuItem?.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item_1->{
                Toast.makeText(applicationContext,"Menu item 1 pressed",Toast.LENGTH_LONG).show();
            }
            R.id.menu_item_2->{
                Toast.makeText(applicationContext,"Menu item 2 pressed",Toast.LENGTH_LONG).show();
            }
            else->{}
        }
        return true
    }



}