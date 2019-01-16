package com.calvin.skripsi.kanabisa

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import com.calvin.skripsi.kanabisa.model.Karakter
import java.util.*
import kotlin.random.Random

class EvaluasiActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evaluasi)

        this.title = "Evaluasi"

        val builder = AlertDialog.Builder(this)
        val dbh = DBHelper(this)
        val arrKr: ArrayList<Karakter> = dbh.tabelKarakter()
        val randNum = Random.nextInt(1,208)
        val inProgress: Boolean = intent.extras.getBoolean("inProg")
        var idEval = intent.extras.getInt("id")
        var evalBanyak = intent.extras.getInt("banyak")
        var evalBenar = intent.extras.getInt("benar")

        var gambarKarakter: ImageView = findViewById(R.id.gambarEval)
        var radioG: RadioGroup = findViewById(R.id.radGroup)
        var btnJawab: Button = findViewById(R.id.buttonJawab)
        var btnKeluar: Button = findViewById(R.id.buttonKeluar)
        var kr: Karakter = Karakter(
            arrKr[randNum].id,
            arrKr[randNum].karakter,
            arrKr[randNum].roman,
            arrKr[randNum].id_jenis,
            arrKr[randNum].id_bunyi,
            arrKr[randNum].gambar,
            arrKr[randNum].eval_banyak,
            arrKr[randNum].eval_benar,
            arrKr[randNum].eval_nilai)
        var nama_gambar = ""
        val truerom = kr.roman

        when (kr.id_jenis) {
            1 -> nama_gambar = nama_gambar + "h_dasar_" + kr.gambar + "_large"
            2 -> nama_gambar = nama_gambar + "k_dasar_" + kr.gambar + "_large"
        }

        val res_id: Int = this.resources.getIdentifier(nama_gambar,"drawable",this.packageName)
        gambarKarakter.setImageResource(res_id)

        val radNum1 = kr.id
        var radNum2 = 0
        var radNum3 = 0
        var radNum4 = 0

        do {
            radNum2 = Random.nextInt(1,104)
            radNum3 = Random.nextInt(1,104)
            radNum4 = Random.nextInt(1,104)
        } while (
            !intArrayOf(radNum1,radNum2,radNum3,radNum4).count().equals(
                intArrayOf(radNum1,radNum2,radNum3,radNum4).distinct().count()
            )
        )

        val kr2 = arrKr[radNum2].roman
        val kr3 = arrKr[radNum3].roman
        val kr4 = arrKr[radNum4].roman

        val radRom: ArrayList<String> = ArrayList()
        radRom.add(kr.roman)
        radRom.add(kr2)
        radRom.add(kr3)
        radRom.add(kr4)
        radRom.shuffle()

        for (i in 0 until radioG.childCount) {
            val radBut: View = radioG.getChildAt(i)
            if (radBut is RadioButton) {
                radBut.text = radRom[i]
            }
        }

        btnJawab.setOnClickListener{
            if(radioG.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Belum ada jawaban yang dipilih!", Toast.LENGTH_SHORT).show()
            }
            else {
                for (i in 0 until radioG.childCount) {
                    val radBut: View = radioG.getChildAt(i)
                    if (radBut is RadioButton) {
                        if (radBut.isChecked) {
                            if (radBut.text.toString().equals(truerom)) {
                                if (!inProgress) {
                                    evalBanyak++
                                    evalBenar++
                                    dbh.mulaiEvaluasi(evalBenar)
                                    dbh.prosesEvaluasi(true, kr)
                                    dbh.rekamDetailEvaluasi(idEval + 1, kr, true)
                                    idEval = dbh.maxEvalId()
                                }
                                else {
                                    evalBanyak++
                                    evalBenar++
                                    dbh.prosesEvaluasi(true, kr)
                                    dbh.rekamEvaluasi(idEval, evalBanyak, evalBenar)
                                    dbh.rekamDetailEvaluasi(idEval, kr, true)
                                }
                                builder.setTitle("Jawaban Benar")
                                builder.setMessage("Selamat, jawaban Anda benar!")
                                builder.setPositiveButton("Lanjutkan") {dialogInterface, i ->
                                    val intent = Intent(this@EvaluasiActivity,EvaluasiActivity::class.java)
                                    val bundle = Bundle()
                                    bundle.putBoolean("inProg", true)
                                    bundle.putInt("id", idEval)
                                    bundle.putInt("banyak", evalBanyak)
                                    bundle.putInt("benar", evalBenar)
                                    intent.putExtras(bundle)
                                    startActivity(intent)
                                    this.finish()
                                }
                                builder.setNegativeButton("Keluar") {dialogInterface, i ->
                                    startActivity(Intent(this@EvaluasiActivity,BerandaEvaluasiActivity::class.java))
                                    this.finish()
                                }
                                builder.setCancelable(false)
                                builder.show()
                            }
                            else {
                                if(!inProgress) {
                                    evalBanyak++
                                    dbh.mulaiEvaluasi(evalBenar)
                                    dbh.prosesEvaluasi(false, kr)
                                    dbh.rekamDetailEvaluasi(idEval + 1, kr, false)
                                    idEval = dbh.maxEvalId()
                                }
                                else {
                                    evalBanyak++
                                    dbh.prosesEvaluasi(false, kr)
                                    dbh.rekamEvaluasi(idEval, evalBanyak, evalBenar)
                                    dbh.rekamDetailEvaluasi(idEval, kr, false)
                                }
                                builder.setTitle("Jawaban Salah")
                                builder.setMessage("Maaf, jawaban Anda salah! Jawaban yang benar adalah "+truerom+".")
                                builder.setPositiveButton("Lanjutkan") {dialogInterface, i ->
                                    val intent = Intent(this@EvaluasiActivity,EvaluasiActivity::class.java)
                                    val bundle = Bundle()
                                    bundle.putBoolean("inProg", true)
                                    bundle.putInt("id", idEval)
                                    bundle.putInt("banyak", evalBanyak)
                                    bundle.putInt("benar", evalBenar)
                                    intent.putExtras(bundle)
                                    startActivity(intent)
                                    this.finish()
                                }
                                builder.setNegativeButton("Keluar") {dialogInterface, i ->
                                    startActivity(Intent(this@EvaluasiActivity,BerandaEvaluasiActivity::class.java))
                                    this.finish()
                                }
                                builder.setCancelable(false)
                                builder.show()
                            }
                        }
                    }
                }

            }
        }

        btnKeluar.setOnClickListener{
            builder.setTitle("Keluar")
            builder.setMessage("Apakah Anda yakin ingin berhenti melakukan evaluasi?")
            builder.setPositiveButton("Ya") {dialogInterface, i ->
                startActivity(Intent(this@EvaluasiActivity,BerandaEvaluasiActivity::class.java))
                this.finish()
            }
            builder.setNegativeButton("Tidak") {dialogInterface, i ->

            }
            builder.setCancelable(false)
            builder.show()
        }
    }

    override fun onBackPressed() {

    }
}

// Random: https://github.com/Kotlin/KEEP/blob/master/proposals/stdlib/random.md