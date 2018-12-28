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

class HiraganaActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout
    val dbh = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hiragana)

        this.title = "Hiragana"
        val tabelH: TableLayout = findViewById(R.id.tabel_hiragana)
        tabelH.isClickable

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
                R.id.optionBeranda -> startActivity(Intent(this@HiraganaActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@HiraganaActivity,PengantarActivity::class.java))
                //2 -> tabel hiragana
                R.id.optionKatakana -> startActivity(Intent(this@HiraganaActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@HiraganaActivity,KompetensiActivity::class.java))
                //5 -> mulai Evaluasi
            }

            mDrawerLayout.closeDrawers()

            true
        }

        var cellNum = 0
        for (i in 0 until tabelH.childCount) {
            val tabelR: View = tabelH.getChildAt(i)
            if (tabelR is TableRow) {
                for (j in 0 until tabelR.childCount) {
                    val tabelC: View = tabelR.getChildAt(j)
                    if(tabelC is LinearLayout) {
                        cellNum++
                        tabelC.setTag(cellNum.toString())
                        tabelC.setOnClickListener {
                            //Toast.makeText(this@HiraganaActivity,tabelC.getTag().toString(),Toast.LENGTH_SHORT).show()
                            val kr: Karakter = dbh.cariKarakter(tabelC.getTag().toString().toInt())
                            val intent = Intent(this@HiraganaActivity, KarakterActivity::class.java)
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