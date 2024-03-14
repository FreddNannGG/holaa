package guerrero.erick.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

//Clase
class MainActivity : AppCompatActivity() {
    lateinit var etTitulo: EditText
    lateinit var btnGuardar:Button
    lateinit var listaTareas:RecyclerView
    lateinit var adapter:TareasAdapter
    private var monto_entrada: Int = 1
    //private var MontoCete:double = 11.49
    private var mes_entrada: Int = 1
    private lateinit var tvMes: TextView
    private lateinit var rsMeses: RangeSlider
    private lateinit var tvTitulo: TextView
    private lateinit var btnCalcular:Button

    private val tareasViewModel:TareasViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        //calcular()

        etTitulo = findViewById(R.id.etNombre)
        btnGuardar = findViewById(R.id.btnGuardar)
        listaTareas = findViewById(R.id.rvTareas)


        tareasViewModel.elementos.add(
            Tarea("Ingresa la cantidad",
                "cete",
            false)
        )

        adapter = TareasAdapter(tareasViewModel.elementos)

        listaTareas.adapter = adapter
        listaTareas.layoutManager = GridLayoutManager(this,
            1)


        btnGuardar.setOnClickListener {
            val monto_entrada1 = etTitulo.text.toString()
            val resp = (mes_entrada * monto_entrada1.toInt())
            val resp2 = (resp * 0.1149)
            val resp3 = resp + resp2

            Log.i("fredd", "el cetes es: $resp3")


            tareasViewModel.elementos.add(Tarea(resp3.toString(),"porcentaje de: 11.49%",false))
            adapter.notifyDataSetChanged()
            Toast.makeText(this,"${tareasViewModel.elementos.size}",Toast.LENGTH_SHORT).show()

            //calcular()
        }

    }


    private fun initComponents(){
        tvMes = findViewById(R.id.tvMes)
        tvTitulo = findViewById(R.id.tvTitulo)
        rsMeses = findViewById(R.id.rsMeses)
        btnCalcular = findViewById(R.id.btnGuardar)
    }

    private fun initListeners() {
        rsMeses.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            mes_entrada = df.format(value).toInt()
            tvMes.text = "$mes_entrada Meses"
        }
    }
}