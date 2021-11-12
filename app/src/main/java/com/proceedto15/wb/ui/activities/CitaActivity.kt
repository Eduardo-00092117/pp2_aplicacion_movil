package com.proceedto15.wb.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.proceedto15.wb.R
import com.proceedto15.wb.adapters.DaysAdapter
import com.proceedto15.wb.database.entities.Empleado
import com.proceedto15.wb.database.viewmodels.CitaViewModel
import com.proceedto15.wb.ui.fragments.ScheduleFragment
import com.proceedto15.wb.utilities.AppConstants.SELECTED_SERVICES_KEY
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CitaActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var citaViewModel: CitaViewModel
    private lateinit var employeeSpinner: Spinner
    private lateinit var services: ArrayList<Int>
    private lateinit var empleados: ArrayList<Empleado>
    private lateinit var spinnerString: ArrayList<String>
    private lateinit var dayList: List<String>
    private lateinit var weekDayList: List<String>

    private lateinit var recyclerDays: RecyclerView
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewAdapter: DaysAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cita)

        employees()
        initList()
        initData()
        initRecycler()
    }

    fun employees(){
        var temp =
            intent?.extras?.getString(SELECTED_SERVICES_KEY)?.removeSurrounding(",")?.
            split(",")?.toCollection(ArrayList())
        temp?.removeAt(0)
        services = temp?.map { it.toInt() } as ArrayList<Int>
    }

    fun initData(){
        citaViewModel = ViewModelProvider(this).get(CitaViewModel::class.java)
        employeeSpinner = findViewById(R.id.employee_spinner)
        recyclerDays = findViewById(R.id.recycler_days)

        empleados = arrayListOf()
        spinnerString = arrayListOf()

        services.forEach {
            citaViewModel.getEmpleadosPorServicio(it).observe(this, { empleadoList ->
                setEmployees(empleadoList.toCollection(ArrayList()))
            })
        }
    }

    fun initList(){
        val tempList: ArrayList<String> = arrayListOf()
        val tempWeekList: ArrayList<String> = arrayListOf()
        val calendario = Calendar.getInstance()
        val df = SimpleDateFormat("dd")
        val cdf = SimpleDateFormat("EEEE")

        val currentDay = df.format(calendario.time).toInt()
        val finalDay = finalDayOfMonth(SimpleDateFormat("MM").format(calendario.time).toInt(),
            SimpleDateFormat("yy").format(calendario.time).toInt())
        var day = ""

        for(i in (currentDay+1)..finalDay){
            calendario.add(Calendar.DATE, 1)
            day = cdf.format(calendario.time).toString()
            if(day != "Sunday"){
                tempWeekList.add(inSpanish(day))
                tempList.add(i.toString())
            }
        }
        for (i in 1..currentDay){
            calendario.add(Calendar.DATE, 1)
            day = cdf.format(calendario.time).toString()
            if(day != "Sunday"){
                tempWeekList.add(inSpanish(day))
                tempList.add(i.toString())
            }
        }

        weekDayList = tempWeekList.toList()
        dayList = tempList.toList()
    }

    fun finalDayOfMonth(month: Int, year: Int) : Int {
        when(month){
            1,3,5,7,8,10,12 -> return 31
            4,6,9,11 -> return 30
            2 -> {
                return if(year%4 == 0) 29
                else 28
            }
        }
        return 0
    }

    fun inSpanish(day: String) : String{
        when(day){
            "Monday" -> return getString(R.string.monday)
            "Tuesday" -> return getString(R.string.tuesday)
            "Wednesday" -> return getString(R.string.wednesday)
            "Thursday" -> return getString(R.string.thursday)
            "Friday" -> return getString(R.string.friday)
            "Saturday" -> return getString(R.string.saturday)
        }
        return ""
    }

    fun setEmployees(array: ArrayList<Empleado>){
        if(spinnerString.size == 0){
            empleados = array
            array.forEach {
                spinnerString.add(it.nombre + " " + it.apellido)
            }
        }
        else {
            array.forEach {
                if(!empleados.contains(it)) empleados.add(it)
                if(!spinnerString.contains(it.nombre + " " + it.apellido)) spinnerString.add(it.nombre + " " + it.apellido)
            }
        }
        employeeSpinner.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, R.id.item_spinner, spinnerString)
    }

    fun initRecycler(){
        viewManager = LinearLayoutManager(this)
        viewAdapter = DaysAdapter(dayList, weekDayList, { listItem: String -> onClicked(listItem)})
        recyclerDays.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    fun onClicked(day: String){
        val frag = ScheduleFragment()
        changeFragment(R.id.fragment_schedule, frag)
    }

    private fun changeFragment(id: Int, frag: Fragment){ supportFragmentManager.beginTransaction().replace(id, frag).commit() }
}