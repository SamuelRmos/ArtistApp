package com.example.sampleapp.view.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.sampleapp.databinding.RegisterFragmentBinding
import java.util.*

class DatePickerFragment(private val binding: RegisterFragmentBinding) : DialogFragment(),
    DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        calendar.get(Calendar.YEAR)
        val maxDate = calendar.time.time

        val pickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        pickerDialog.datePicker.maxDate = maxDate
        return pickerDialog
    }

    override fun onDateSet(p0: DatePicker?, year: Int, p2: Int, day: Int) {
        val month = p2 + 1
        val birth = "$day/$month/$year"
        binding.etBirth.setText(birth)
    }
}