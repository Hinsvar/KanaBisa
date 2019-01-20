package com.calvin.skripsi.kanabisa

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.MenuItem
import android.widget.TextView

class PengantarActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengantar)

        this.title = "Pengantar"

        mDrawerLayout = findViewById(R.id.drawer_layout)

        var tap: TextView = findViewById(R.id.textAwalPengantar)
        var tph1: TextView = findViewById(R.id.textPengantarHiragana1)
        var tph2: TextView = findViewById(R.id.textPengantarHiragana2)
        var tpk: TextView = findViewById(R.id.textPengantarKatakana)
        var tpf1: TextView = findViewById(R.id.textPengantarFitur1)
        var tpf2: TextView = findViewById(R.id.textPengantarFitur2)

        tap.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        tph1.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        tph2.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        tpk.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        tpf1.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        tpf2.justificationMode = JUSTIFICATION_MODE_INTER_WORD

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
                R.id.optionBeranda -> startActivity(Intent(this@PengantarActivity,BerandaActivity::class.java))
                //1 -> pengantar
                R.id.optionHiragana -> startActivity(Intent(this@PengantarActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@PengantarActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@PengantarActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@PengantarActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()
            this.finish()

            true
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