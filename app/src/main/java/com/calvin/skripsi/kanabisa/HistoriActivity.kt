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
import android.widget.AdapterView
import android.widget.ListView
import com.calvin.skripsi.kanabisa.model.Evaluasi

class HistoriActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_histori)

        this.title = "Histori"

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val arrEv: ArrayList<Evaluasi> = dbh.tabelEvaluasi()
        val historiLV: ListView = findViewById(R.id.listHistori)

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
                R.id.optionBeranda -> startActivity(Intent(this@HistoriActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@HistoriActivity,PengantarActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@HistoriActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@HistoriActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@HistoriActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@HistoriActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()

            true
        }

        var historiAdapter = EvaluasiListAdapter(arrEv, this)
        historiLV.adapter = historiAdapter

        historiLV.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Evaluasi
            val intent = Intent(this@HistoriActivity,DetailHistoriActivity::class.java)
            intent.putExtra("id", item.id)
            startActivity(intent)
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