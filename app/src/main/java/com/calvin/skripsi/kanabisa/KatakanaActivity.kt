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
import android.view.View
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.Toast
import com.calvin.skripsi.kanabisa.model.Karakter

class KatakanaActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_katakana)

        this.title = "Katakana"
        val tabelK: TableLayout = findViewById(R.id.tabel_katakana)
        tabelK.isClickable

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
                R.id.optionBeranda -> startActivity(Intent(this@KatakanaActivity, BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@KatakanaActivity, PengantarActivity::class.java))
                R.id.optionDaftar -> startActivity(Intent(this@KatakanaActivity,BunyiActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@KatakanaActivity, HiraganaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@KatakanaActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@KatakanaActivity,BerandaEvaluasiActivity::class.java))
            }

            mDrawerLayout.closeDrawers()
            this.finish()

            true
        }

        var cellNum = 104
        for (i in 0 until tabelK.childCount) {
            val tabelR: View = tabelK.getChildAt(i)
            if (tabelR is TableRow) {
                for (j in 0 until tabelR.childCount) {
                    val tabelC: View = tabelR.getChildAt(j)
                    if(tabelC is LinearLayout) {
                        cellNum++
                        tabelC.setTag(cellNum.toString())
                        tabelC.setOnClickListener {
                            val kr: Karakter = dbh.cariKarakter(tabelC.getTag().toString().toInt())
                            val intent = Intent(this@KatakanaActivity, KarakterActivity::class.java)
                            val bundle = Bundle()
                            bundle.putString("roman",kr.roman)
                            bundle.putString("gambar",kr.gambar)
                            bundle.putInt("jenis",kr.id_jenis)
                            bundle.putInt("bunyi",kr.id_bunyi)
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                    }
                }
            }
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