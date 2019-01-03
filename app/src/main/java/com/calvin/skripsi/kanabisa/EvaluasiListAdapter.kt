package com.calvin.skripsi.kanabisa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.calvin.skripsi.kanabisa.model.Evaluasi
import com.calvin.skripsi.kanabisa.model.Karakter

open class EvaluasiListAdapter(data: ArrayList<Evaluasi>, context: Context): BaseAdapter() {

    val dbh = DBHelper(context)
    val arrKr: ArrayList<Karakter> = dbh.tabelKarakter()
    val mInflater: LayoutInflater = LayoutInflater.from(context)
    var locdat = data

    override fun getCount(): Int {
        return locdat.size
    }

    override fun getItem(position: Int): Any {
        return locdat[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {

        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_histori, parent, false)
            vh = ListRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.kar.text = arrKr[locdat.get(index = position).id_karakter - 1].karakter + " (" +
                arrKr[locdat.get(index = position).id_karakter - 1].roman + ")"
        vh.tang.text = locdat.get(index = position).tanggal
        when (locdat.get(index = position).status) {
            false -> vh.stat.text = "Salah"
            true -> vh.stat.text = "Benar"
        }
        return view
    }

    private class ListRowHolder(row: View?) {
        val kar: TextView
        val tang: TextView
        val stat: TextView

        init {
            this.kar = row?.findViewById(R.id.karakter) as TextView
            this.tang = row?.findViewById(R.id.tanggal) as TextView
            this.stat = row?.findViewById(R.id.status) as TextView
        }
    }
}