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
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_karakter.*

class KarakterActivity: AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_karakter)

        this.title = "Karakter"
        var tampil_urutan: Boolean = false
        val imgKar: ImageView = findViewById(R.id.imgKarakter)
        val txtKar: TextView = findViewById(R.id.txtKarakter)
        val btnKar: Button = findViewById(R.id.btnKarakter)
        val roman: String = intent.extras.getString("roman")
        val gambar: String = intent.extras.getString("gambar")
        val jenis: Int = intent.extras.getInt("jenis")
        val bunyi: Int = intent.extras.getInt("bunyi")

        txtKar.text = roman

        var nama_gambar = ""
        var nama_gambar_dasar = ""
        var nama_gambar_urutan = ""

        when (jenis) {
            1 -> nama_gambar = nama_gambar.plus("h_")
            2 -> nama_gambar = nama_gambar.plus("k_")
        }

        nama_gambar_dasar = nama_gambar_dasar.plus(nama_gambar + "dasar_" + gambar + "_large")
        val res_id_dasar: Int = this.resources.getIdentifier(nama_gambar_dasar, "drawable", this.packageName)
        var res_id_urutan = 0
        //Toast.makeText(this,nama_gambar_dasar,Toast.LENGTH_SHORT).show()

        if(bunyi.equals(2)) {
            btnKar.visibility = View.GONE
        }
        else {
            btnKar.visibility = View.VISIBLE
            btnKar.text = "LIHAT URUTAN"
            nama_gambar_urutan = nama_gambar_urutan.plus(nama_gambar + "urutan_" + gambar)
            res_id_urutan = this.resources.getIdentifier(nama_gambar_urutan, "drawable", this.packageName)
        }

        imgKar.setImageResource(res_id_dasar)

        btnKar.setOnClickListener{
            if (!tampil_urutan) {
                tampil_urutan = true
                imgKar.setImageResource(res_id_urutan)
                btnKar.text = "LIHAT TULISAN DASAR"
                //Toast.makeText(this,nama_gambar_dasar,Toast.LENGTH_SHORT).show()
            }
            else {
                tampil_urutan = false
                imgKar.setImageResource(res_id_dasar)
                btnKar.text = "LIHAT URUTAN"
                //Toast.makeText(this,nama_gambar_urutan,Toast.LENGTH_SHORT).show()
            }
        }

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
                R.id.optionBeranda -> startActivity(Intent(this@KarakterActivity,BerandaActivity::class.java))
                R.id.optionPengantar -> startActivity(Intent(this@KarakterActivity,PengantarActivity::class.java))
                R.id.optionDaftar -> startActivity(Intent(this@KarakterActivity,BunyiActivity::class.java))
                R.id.optionHiragana -> startActivity(Intent(this@KarakterActivity,HiraganaActivity::class.java))
                R.id.optionKatakana -> startActivity(Intent(this@KarakterActivity,KatakanaActivity::class.java))
                R.id.optionKompetensi -> startActivity(Intent(this@KarakterActivity,KompetensiActivity::class.java))
                R.id.optionEvaluasi -> startActivity(Intent(this@KarakterActivity,BerandaEvaluasiActivity::class.java))
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

    override fun onBackPressed() {
        this.finish()
    }
}

// res id: https://stackoverflow.com/questions/11921121/android-howto-get-a-resource-id-of-an-android-resource

