package com.example.professorconnect

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment

class RequestMeetingFragment : Fragment() {

    companion object {
        private const val ARG_MEETING = "meeting"
        private const val ARG_POSITION = "position"

        fun newInstance(meeting: Meeting, position: Int): RequestMeetingFragment {
            val fragment = RequestMeetingFragment()
            val args = Bundle()
            args.putParcelable(ARG_MEETING, meeting)
            args.putInt(ARG_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    private var meeting: Meeting? = null
    private var position: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            meeting = it.getParcelable(ARG_MEETING)
            position = it.getInt(ARG_POSITION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_request_meeting, container, false)

        val subjectEditText: EditText = view.findViewById(R.id.subjectEditText)
        val descriptionEditText: EditText = view.findViewById(R.id.descriptionEditText)
        val timeSlotSpinner: Spinner = view.findViewById(R.id.timeSlotSpinner)
        val typeRadioGroup: RadioGroup = view.findViewById(R.id.typeRadioGroup)
        val saveButton: Button = view.findViewById(R.id.saveMeetingButton)
        val cancelButton: Button = view.findViewById(R.id.cancelButton)

        meeting?.let {
            subjectEditText.setText(it.subject)
            descriptionEditText.setText(it.description)
            timeSlotSpinner.setSelection((timeSlotSpinner.adapter as ArrayAdapter<String>).getPosition(it.timeSlot))
            if (it.type == "In-person") typeRadioGroup.check(R.id.radio_in_person)
            else typeRadioGroup.check(R.id.radio_online)
        }

        saveButton.setOnClickListener {
            val subject = subjectEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val timeSlot = timeSlotSpinner.selectedItem.toString()
            val typeRadioButtonId = typeRadioGroup.checkedRadioButtonId
            val typeRadioButton: RadioButton = view.findViewById(typeRadioButtonId)
            val type = typeRadioButton.text.toString()

            val newMeeting = Meeting(subject, description, timeSlot, type)
            if (position != null) {
                MainActivity.meetingsList[position!!] = newMeeting
            } else {
                MainActivity.meetingsList.add(newMeeting)
            }

            Toast.makeText(context, "Meeting Saved", Toast.LENGTH_SHORT).show()
            fragmentManager?.popBackStack()
        }

        cancelButton.setOnClickListener {
            Toast.makeText(context, "Action Cancelled", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
