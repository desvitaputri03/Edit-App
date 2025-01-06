package com.example.crud_buku.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.crud_buku.R

class DetailBukuActivity : AppCompatActivity() {

    private lateinit var txtDetailJudul: TextView
    private lateinit var txtDetailPengarang: TextView
    private lateinit var txtDetailGenre: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_buku)

        txtDetailJudul = findViewById(R.id.txtDetailJudul)
        txtDetailPengarang = findViewById(R.id.txtDetailPengarang)
        txtDetailGenre = findViewById(R.id.txtDetailGenre)

        // Mendapatkan data dari Intent
        val detailJudul = intent.getStringExtra("judul")
        val detailPengarang = intent.getStringExtra("pengarang")
        val detailGenre = intent.getStringExtra("genre")

        // Menampilkan data ke TextView
        txtDetailJudul.text = detailJudul ?: "Judul tidak tersedia"
        txtDetailPengarang.text = detailPengarang ?: "Pengarang tidak tersedia"
        txtDetailGenre.text = detailGenre ?: "Genre tidak tersedia"

        // Menyesuaikan padding untuk sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
