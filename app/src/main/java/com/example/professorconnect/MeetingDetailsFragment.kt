package com.example.professorconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class MeetingDetailsFragment : Fragment() {

    companion object {
        private const val ARG_MEETING = "meeting"
        private const val ARG_POSITION = "position"

        fun newInstance(meeting: Meeting, position: Int): MeetingDetailsFragment {
            val fragment = MeetingDetailsFragment()
            val args = Bundle()
            args.putParcelable(ARG_MEETING, meeting)
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var meeting: Meeting
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            meeting = it.getParcelable(ARG_MEETING)!!
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meeting_details, container, false)

        val subjectTextView: TextView = view.findViewById(R.id.subjectTextView)
        val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)
        val timeSlotTextView: TextView = view.findViewById(R.id.timeSlotTextView)
        val typeTextView: TextView = view.findViewById(R.id.typeTextView)
        val editButton: Button = view.findViewById(R.id.editButton)
        val deleteButton: Button = view.findViewById(R.id.deleteButton)

        subjectTextView.text = meeting.subject
        descriptionTextView.text = meeting.description
        timeSlotTextView.text = meeting.timeSlot
        typeTextView.text = meeting.type

        editButton.setOnClickListener {
            val fragment = RequestMeetingFragment.newInstance(meeting, position)
            fragmentManager?.beginTransaction()
                ?.replace(R.id.fragment_container, fragment)
                ?.addToBackStack(null)
                ?.commit()
        }

        deleteButton.setOnClickListener {
            MainActivity.meetingsList.removeAt(position)
            fragmentManager.popBackStack()
        }

        return view
    }
}
