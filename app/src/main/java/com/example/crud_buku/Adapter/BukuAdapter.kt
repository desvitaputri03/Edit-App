package com.example.crud_buku.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.crud_buku.DbHelper.DbHelper
import com.example.crud_buku.R
import com.example.crud_buku.model.ModelBuku
import com.example.crud_buku.screenpage.UpdateBukuActivity

class BukuAdapter(
    private var listBuku: List<ModelBuku>,
    context: Context
) : RecyclerView.Adapter<BukuAdapter.BukuViewHolder>() {

    private val db: DbHelper = DbHelper(context)

    class BukuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtJudul: TextView = itemView.findViewById(R.id.txtJudul)
        val txtPengarang: TextView = itemView.findViewById(R.id.txtPengarang)
        val txtGenre: TextView = itemView.findViewById(R.id.txtGenre)
        val btnEdit: ImageView = itemView.findViewById(R.id.btnEditItem)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnDeleteItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BukuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_data_buku, parent, false
        )
        return BukuViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listBuku.size
    }

    override fun onBindViewHolder(holder: BukuViewHolder, position: Int) {
        val buku = listBuku[position]
        holder.txtJudul.text = buku.judul
        holder.txtPengarang.text = buku.pengarang
        holder.txtGenre.text = buku.genre

        holder.btnDelete.setOnClickListener {
            db.deleteBuku(buku.id)
            refreshData(db.getAllDataBuku())
            Toast.makeText(
                holder.itemView.context,
                "Berhasil menghapus Buku: ${buku.judul}",
                Toast.LENGTH_LONG
            ).show()
        }

        holder.btnEdit.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateBukuActivity::class.java).apply {
                putExtra("id_buku", buku.id)
            }
            holder.itemView.context.startActivity(intent)
        }
    }

    fun refreshData(newBuku: List<ModelBuku>) {
        listBuku = newBuku
        notifyDataSetChanged()
    }
}
