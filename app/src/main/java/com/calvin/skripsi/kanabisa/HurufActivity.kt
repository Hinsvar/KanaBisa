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
import android.widget.ArrayAdapter
import android.widget.ListView
import com.calvin.skripsi.kanabisa.model.Karakter
import java.util.ArrayList

class HurufActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_huruf)

        mDrawerLayout = findViewById(R.id.drawer_layout)

        this.title = intent.extras.getString("jenis")
        val arrKr: ArrayList<Karakter> = dbh.tabelKarakter()

        var minRange = 1
        var maxRange = 2

        when (intent.extras.getInt("pos")) {
            0 -> minRange = 1
            1 -> minRange = 47
            2 -> minRange = 72
            3 -> minRange = 105
            4 -> minRange = 151
            5 -> minRange = 176
        }

        when (intent.extras.getInt("pos")) {
            0 -> maxRange = 46
            1 -> maxRange = 71
            2 -> maxRange = 104
            3 -> maxRange = 150
            4 -> maxRange = 175
            5 -> maxRange = 208
        }

        val arrKrHuruf = ArrayList<Karakter>()
        for (i in minRange..maxRange) {
            arrKrHuruf.add(arrKr[i-1])
        }

        val hurufLV: ListView = findViewById(R.id.listHuruf)

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
                R.id.optionBeranda -> startActivity(Intent(this@HurufActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@HurufActivity,PengantarActivity::class.java))
                R.id.optionDaftar -> startActivity(Intent(this@HurufActivity,BunyiActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@HurufActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@HurufActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()
            this.finish()

            true
        }

        val hurufAdapter = HurufListAdapter(arrKrHuruf, this)
        hurufLV.adapter = hurufAdapter
        hurufLV.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as Karakter
            val intent = Intent(this@HurufActivity,KarakterActivity::class.java)
            val bundle = Bundle()
            bundle.putString("roman",item.roman)
            bundle.putString("gambar",item.gambar)
            bundle.putInt("jenis",item.id_jenis)
            bundle.putInt("bunyi",item.id_bunyi)
            intent.putExtras(bundle)

            if(!item.status) {
                dbh.setKarakterDipelajari(item.id)
            }

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