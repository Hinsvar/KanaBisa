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
import com.calvin.skripsi.kanabisa.model.Evaluasi

class BunyiActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bunyi)

        this.title = "Daftar Kana"

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val bunyiLV: ListView = findViewById(R.id.listBunyi)
        val bunyi = listOf("Hiragana - Seion", "Hiragana - Dakuon", "Hiragana - Yoon",
                            "Katakana - Seion", "Katakana - Dakuon", "Katakana - Yoon")
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            bunyi
        )

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
                R.id.optionBeranda -> startActivity(Intent(this@BunyiActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@BunyiActivity,PengantarActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@BunyiActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@BunyiActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@BunyiActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@BunyiActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()
            this.finish()

            true
        }

        bunyiLV.adapter = adapter
        bunyiLV.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position) as String
            val intent = Intent(this@BunyiActivity,HurufActivity::class.java)
            val bundle = Bundle()
            bundle.putString("jenis",item)
            bundle.putInt("pos", position)
            intent.putExtras(bundle)
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