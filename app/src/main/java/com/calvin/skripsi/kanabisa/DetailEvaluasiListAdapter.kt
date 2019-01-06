package com.calvin.skripsi.kanabisa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.calvin.skripsi.kanabisa.model.DetailEvaluasi
import com.calvin.skripsi.kanabisa.model.Karakter

open class DetailEvaluasiListAdapter(data: ArrayList<DetailEvaluasi>, context: Context): BaseAdapter() {

    val dbh = DBHelper(context)
    val arrKr: ArrayList<Karakter> = dbh.tabelKarakter()
    val mInflater: LayoutInflater = LayoutInflater.from(context)
    val locdat = data

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
        val lrh: ListRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_detail_histori, parent, false)
            lrh = ListRowHolder(view)
            view?.tag = lrh
        } else {
            view = convertView
            lrh = view.tag as ListRowHolder
        }
        lrh.kar.text = arrKr[locdat.get(index = position).id_karakter - 1].karakter + " (" +
                arrKr[locdat.get(index = position).id_karakter - 1].roman + ")"
        when (locdat.get(index = position).status) {
            false -> lrh.stat.text = "Salah"
            true -> lrh.stat.text = "Benar"
        }
        return view
    }

    private class ListRowHolder(row: View?) {
        val kar: TextView
        val stat: TextView

        init {
            this.kar = row?.findViewById(R.id.karakter) as TextView
            this.stat = row?.findViewById(R.id.status) as TextView
        }
    }
}