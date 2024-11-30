package com.example.contactlistexample

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistexample.adapter.ContactAdapter
import com.example.contactlistexample.data.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var internetStatusReceiver: InternetStatusReceiver

    private lateinit var adapter: ContactAdapter
    private val contactList = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Internet Receiver
        /*
        * /internetStatusReceiver = InternetStatusReceiver()
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(internetStatusReceiver, filter)
        **/

        // Form elements find
        val etName = findViewById<EditText>(R.id.etName)
        val etPhone = findViewById<EditText>(R.id.etPhone)
        val cbAvailable = findViewById<CheckBox>(R.id.cbAvailable)
        val btnSwitch = findViewById<com.google.android.material.switchmaterial.SwitchMaterial>(R.id.btnSwitch)

        val fabAddContact = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAddContact)
        fabAddContact.setOnClickListener{
            if(etName.text.isNotEmpty() && etPhone.text.isNotEmpty()){
                val newContact = Contact(etName.text.toString(), etPhone.text.toString(), cbAvailable.isChecked)
                contactList.add(newContact)
                setRecyclerViewAdapter(contactList)
                etName.text.clear()
                etPhone.text.clear()
                cbAvailable.isChecked = false
                //unregisterReceiver(internetStatusReceiver)
            } else {
                Log.d("MainActivity", "Name or Phone is empty")
            }
        }

        btnSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // El Switch está seleccionado (ON)
                Log.d("MainActivity", "Switch is on")
                val availableList = contactList.filter { it.isAvailable }
                setRecyclerViewAdapter(availableList)
            } else {
                // El Switch no está seleccionado (OFF)
                setRecyclerViewAdapter(contactList)
            }
        }

    }

    // RecyclerView
    private fun setRecyclerViewAdapter(contactList: List<Contact>) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        adapter = ContactAdapter(contactList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

}