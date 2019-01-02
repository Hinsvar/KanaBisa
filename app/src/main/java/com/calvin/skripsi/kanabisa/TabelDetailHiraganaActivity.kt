package com.calvin.skripsi.kanabisa

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.calvin.skripsi.kanabisa.model.Karakter
import java.util.ArrayList

class TabelDetailHiraganaActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabel_detail_hiragana)

        this.title = "Detail per Aksara"

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val arrKr: ArrayList<Karakter> = dbh.tabelKarakter()
        var tabel: TableLayout = findViewById(R.id.tabel_detail)

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
                R.id.optionBeranda -> startActivity(Intent(this@TabelDetailHiraganaActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@TabelDetailHiraganaActivity,PengantarActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@TabelDetailHiraganaActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@TabelDetailHiraganaActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@TabelDetailHiraganaActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@TabelDetailHiraganaActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()

            true
        }

        //table row > text view

        val tabelR1: TableRow = findViewById(R.id.baris_detail)
        val textV1: TextView = findViewById(R.id.header_aksara)

        for(i in 0..103) {
            var tabelR: TableRow = TableRow(this@TabelDetailHiraganaActivity)
            tabelR.layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, 0)
            tabelR.weightSum = tabelR1.weightSum

            for (j in 0..4) {
                var textV: TextView = TextView(this@TabelDetailHiraganaActivity)
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
}