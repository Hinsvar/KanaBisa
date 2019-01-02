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
import android.view.ViewGroup
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
        var tabel: TableLayout = findViewById(R.id.tabel_detail)
        var pieChartView: PieChartView = findViewById(R.id.grafikKompetensi)
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
                R.id.optionHiragana -> startActivity(Intent(this@KompetensiActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@KompetensiActivity,KatakanaActivity::class.java))
                //4 -> kompetensi kana
                R.id.optionEvaluasi -> startActivity(Intent(this@KompetensiActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()

            true
        }

        val arrKr: ArrayList<Karakter> = dbh.tabelKarakter()

        for (i in arrKr.indices) {
            if(arrKr[i].eval_nilai >= batasNilai && arrKr[i].eval_banyak >= batasBanyak) {
                pass++
            }
            else {
                fail++
            }
        }

        var pieData: ArrayList<SliceValue> = ArrayList()
        pieData.add(SliceValue(pass.toFloat(), Color.GREEN).setLabel("Dikuasai"))
        pieData.add(SliceValue(fail.toFloat(), Color.RED).setLabel("Dipelajari"))

        val pieChartData: PieChartData = PieChartData(pieData)
        pieChartData.setHasLabels(true)
        pieChartView.pieChartData = pieChartData

        val textDikuasai: TextView = findViewById(R.id.textDikuasai)
        val textDipelajari: TextView = findViewById(R.id.textDipelajari)

        textDikuasai.text = "Dikuasai: " + pass.toString()
        textDipelajari.text = "Dipelajari: " + fail.toString()

        //table row > text view

        val tabelR1: TableRow = findViewById(R.id.baris_detail)
        val textV1: TextView = findViewById(R.id.header_aksara)

        for(i in arrKr.indices) {
            var tabelR: TableRow = TableRow(this@KompetensiActivity)
            tabelR.layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 0)
            tabelR.weightSum = tabelR1.weightSum

            for (j in 0..4) {
                var textV: TextView = TextView(this@KompetensiActivity)
                textV.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, 64, 0.2f)
                textV.textSize = 16f
                textV.gravity = textV1.gravity
                when (j) {
                    0 -> textV.text = arrKr[i].karakter
                    1 -> textV.text = arrKr[i].roman
                    2 -> textV.text = arrKr[i].eval_banyak.toString()
                    3 -> textV.text = arrKr[i].eval_benar.toString()
                    4 -> textV.text = String.format("%.2f", arrKr[i].eval_nilai.toFloat())
                }

                tabelR.addView(textV)
            }

            tabel.addView(tabelR)
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