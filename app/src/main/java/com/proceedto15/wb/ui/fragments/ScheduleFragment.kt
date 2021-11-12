package com.proceedto15.wb.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proceedto15.wb.R

class ScheduleFragment : Fragment() {

    private lateinit var day: String

    companion object {
        fun newInstance(item: String): ScheduleFragment{
            val newFragment = ScheduleFragment()
            newFragment.day = item
            return newFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("hola", day)
        if(day == getString(R.string.tuesday) || day == getString(R.string.friday)) return inflater.inflate(R.layout.fragment_schedule_variant_1, container, false)
        else if(day == getString(R.string.wednesday) || day == getString(R.string.saturday)) return inflater.inflate(R.layout.fragment_schedule_variant_2, container, false)
        return inflater.inflate(R.layout.fragment_schedule, container, false)
    }
}