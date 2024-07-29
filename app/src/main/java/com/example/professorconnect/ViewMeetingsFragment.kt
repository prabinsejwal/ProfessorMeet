package com.example.professorconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ViewMeetingsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_view_meetings, container, false)
        val meetingsListView: ListView = view.findViewById(R.id.meetingsListView)

        val meetingsAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            MainActivity.meetingsList.map { it.subject + ": " + it.timeSlot }
        )

        meetingsListView.adapter = meetingsAdapter

        meetingsListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val meeting = MainActivity.meetingsList[position]
            val fragment = MeetingDetailsFragment.newInstance(meeting, position)
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        return view
    }
}
