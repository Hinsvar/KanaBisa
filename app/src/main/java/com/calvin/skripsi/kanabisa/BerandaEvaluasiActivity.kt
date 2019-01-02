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
import android.widget.Button

class BerandaEvaluasiActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_evaluasi)

        this.title = "Evaluasi"

        mDrawerLayout = findViewById(R.id.drawer_layout)
        var btnEval: Button = findViewById(R.id.buttonEvaluasi)

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
                R.id.optionBeranda -> startActivity(Intent(this@BerandaEvaluasiActivity, BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@BerandaEvaluasiActivity, PengantarActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@BerandaEvaluasiActivity, HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@BerandaEvaluasiActivity, KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@BerandaEvaluasiActivity, KompetensiActivity::class.java))
                //5 -> mulai evaluasi
            }

            mDrawerLayout.closeDrawers()

            true
        }

        btnEval.setOnClickListener {
            startActivity(Intent(this@BerandaEvaluasiActivity, EvaluasiActivity::class.java))
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