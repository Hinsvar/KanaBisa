package com.calvin.skripsi.kanabisa

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem

class BerandaActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)

        this.title = "Beranda"

        mDrawerLayout = findViewById(R.id.drawer_layout)

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
                R.id.optionPengantar -> startActivity(Intent(this@BerandaActivity,PengantarActivity::class.java))
                R.id.optionDaftar -> startActivity(Intent(this@BerandaActivity,BunyiActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@BerandaActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@BerandaActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@BerandaActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@BerandaActivity,BerandaEvaluasiActivity::class.java))
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
