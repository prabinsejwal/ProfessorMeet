package com.example.professorconnect

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    companion object {
        val meetingsList = mutableListOf<Meeting>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewMeetingsButton: Button = findViewById(R.id.viewMeetingsButton)
        val requestMeetingButton: Button = findViewById(R.id.requestMeetingButton)

        viewMeetingsButton.setOnClickListener {
            loadFragment(ViewMeetingsFragment())
        }

        requestMeetingButton.setOnClickListener {
            loadFragment(RequestMeetingFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_view_meetings -> {
                loadFragment(ViewMeetingsFragment())
                true
            }
            R.id.action_request_meeting -> {
                loadFragment(RequestMeetingFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
