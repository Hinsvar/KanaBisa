package com.calvin.skripsi.kanabisa

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.*
import com.calvin.skripsi.kanabisa.model.Karakter
import lecho.lib.hellocharts.model.PieChartData
import lecho.lib.hellocharts.model.SliceValue
import lecho.lib.hellocharts.view.PieChartView

class KompetensiActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)
    val batasNilai = 0.6
    val batasBanyak = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kompetensi)

        this.title = "Kompetensi"

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val arrKr: ArrayList<Karakter> = dbh.tabelKarakter()
        var pieChartView: PieChartView = findViewById(R.id.baganKompetensi)
        val btnDetailH: Button = findViewById(R.id.buttonDetailHiragana)
        val btnDetailK: Button = findViewById(R.id.buttonDetailKatakana)
        var pass = 0
        var fail = 0

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        val actionbar: ActionBar? = supportActionBar
        actionbar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.optionBeranda -> startActivity(Intent(this@KompetensiActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@KompetensiActivity,PengantarActivity::class.java))
                R.id.optionDaftar -> startActivity(Intent(this@KompetensiActivity,BunyiActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@KompetensiActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@KompetensiActivity,KatakanaActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@KompetensiActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()
            this.finish()

            true
        }

        for (i in arrKr.indices) {
            if(arrKr[i].eval_nilai >= batasNilai && arrKr[i].eval_banyak >= batasBanyak) {
                pass++
            }
            else {
                fail++
            }
        }

        var pieData: ArrayList<SliceValue> = ArrayList()
        pieData.add(SliceValue(pass.toFloat(), Color.rgb(53, 101, 154)).setLabel("Dikuasai"))
        pieData.add(SliceValue(fail.toFloat(), Color.rgb(175,56,41)).setLabel("Dipelajari"))

        val pieChartData = PieChartData(pieData)
        pieChartData.setHasLabels(true)
        pieChartView.pieChartData = pieChartData

        val textDikuasai: TextView = findViewById(R.id.textDikuasai)
        val textDipelajari: TextView = findViewById(R.id.textDipelajari)

        textDikuasai.text = "Dikuasai: " + pass.toString()
        textDipelajari.text = "Dipelajari: " + fail.toString()

        btnDetailH.setOnClickListener {
            startActivity(Intent(this@KompetensiActivity,TabelDetailHiraganaActivity::class.java))
        }

        btnDetailK.setOnClickListener {
            startActivity(Intent(this@KompetensiActivity,TabelDetailKatakanaActivity::class.java))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                mDrawerLayout.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}