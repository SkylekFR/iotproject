package com.example.iotproject

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentContainer
import com.example.iotproject.databinding.FragmentWineAddDialogBinding
import com.example.iotproject.model.Wine
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.*

class WineAddDialogFragment : DialogFragment() {
    // TODO: Rename and change types of parameters

    lateinit var wineNameEditText: EditText
    lateinit var addButton: Button

    lateinit var date: Date

    lateinit var binding : FragmentWineAddDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWineAddDialogBinding.inflate(inflater,container, false )
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WineAddDialogFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.wineAddButton.setOnClickListener(addButtonOnClickListener())



        binding.wineAddDateEdittext.setOnClickListener {
            Log.d("failed", "clicked")
            val datePickerDialog = DatePickerDialog(requireContext())
            datePickerDialog.setOnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                val cal = Calendar.getInstance()
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                binding.wineAddDateEdittext.setText(DateFormat.getInstance().format(cal.time))
                date = cal.time
            }
            datePickerDialog.show()

        }

    }

    private fun addButtonOnClickListener() : View.OnClickListener {
        return View.OnClickListener {
            val reference = FirebaseDatabase.getInstance().reference
            val wine = Wine(binding.wineAddNameEdittext.text.toString(),
                binding.wineAddCreatorEdittext.text.toString(),
                date,
                binding.wineAddTypeEdittext.text.toString())
            reference.child("/cave/wines/").child("${wine.name}").setValue(wine)
                .addOnSuccessListener {
                this.dismiss()
                }
                .addOnFailureListener { exception ->
                    Log.d("failed", exception.localizedMessage)
                }

        }
    }
}