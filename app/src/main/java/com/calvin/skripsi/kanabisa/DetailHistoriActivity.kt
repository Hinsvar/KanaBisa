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
import android.widget.ListView
import com.calvin.skripsi.kanabisa.model.DetailEvaluasi

class DetailHistoriActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_histori)

        val idEval = intent.extras.getInt("id")
        this.title = "Detail Histori - Evaluasi " + idEval.toString()

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val arrDetEv: ArrayList<DetailEvaluasi> = dbh.tabelDetailEvaluasi()
        var arrDetEvIdEval = ArrayList<DetailEvaluasi>()
        val detHistoriLV: ListView = findViewById(R.id.listDetailHistori)

        for (i in arrDetEv.indices) {
            if (arrDetEv[i].id_evaluasi == idEval) {
                arrDetEvIdEval.add(arrDetEv[i])
            }
        }

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
                R.id.optionBeranda -> startActivity(Intent(this@DetailHistoriActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@DetailHistoriActivity,PengantarActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@DetailHistoriActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@DetailHistoriActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@DetailHistoriActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@DetailHistoriActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()

            true
        }

        var historiAdapter = DetailEvaluasiListAdapter(arrDetEvIdEval, this)
        detHistoriLV.adapter = historiAdapter
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