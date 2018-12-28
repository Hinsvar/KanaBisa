package com.calvin.skripsi.kanabisa

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.charts.Pie
import com.calvin.skripsi.kanabisa.model.Karakter
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry

class KompetensiActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)
    var arrKr: ArrayList<Karakter> = dbh.tabelKarakter()
    val batasNilai = 0.6
    val batasBanyak = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kompetensi)

        this.title = "Kompetensi"

        mDrawerLayout = findViewById(R.id.drawer_layout)
        var pie: Pie = AnyChart.pie()
        val pieKompetensi: AnyChartView = findViewById(R.id.grafikKompetensi)
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
                //5 -> mulai Evaluasi
            }

            mDrawerLayout.closeDrawers()

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

        var data: ArrayList<DataEntry> = ArrayList<DataEntry>()
        data.add(ValueDataEntry("Dikuasai",pass))
        data.add(ValueDataEntry("Dipelajari",fail))

        pie.data(data)

        pieKompetensi.setChart(pie)
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