package com.calvin.skripsi.kanabisa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.ImageView
import com.calvin.skripsi.kanabisa.model.Karakter

class HurufListAdapter(data: ArrayList<Karakter>, context: Context): BaseAdapter() {

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
        val context: Context = parent.context
        val view: View?
        val lrh: ListRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.item_huruf, parent, false)
            lrh = ListRowHolder(view)
            view?.tag = lrh
        } else {
            view = convertView
            lrh = view.tag as ListRowHolder
        }

        var nama_gambar = ""
        when (locdat.get(index = position).id_jenis) {
            1 -> nama_gambar = nama_gambar + "h_dasar_" + locdat.get(index = position).gambar
            2 -> nama_gambar = nama_gambar + "k_dasar_" + locdat.get(index = position).gambar
        }

        lrh.roman.text = locdat.get(index = position).roman
        lrh.gambar.setImageResource(context.resources.getIdentifier(nama_gambar,"drawable",context.packageName))
        return view
    }

    private class ListRowHolder(row: View?) {
        val roman: TextView
        val gambar: ImageView

        init {
            this.roman = row?.findViewById(R.id.roman) as TextView
            this.gambar = row?.findViewById(R.id.gambar) as ImageView
        }
    }
}