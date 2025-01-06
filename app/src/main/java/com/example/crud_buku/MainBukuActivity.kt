package com.example.crud_buku

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crud_buku.DbHelper.DbHelper
import com.example.crud_buku.adapter.BukuAdapter
import com.example.crud_buku.databinding.ActivityMainBinding
import com.example.crud_buku.screenpage.TambahDataBukuActivity

class MainBukuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var db: DbHelper
    private lateinit var bukuAdapter: BukuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi database dan adapter
        db = DbHelper(this)
        bukuAdapter = BukuAdapter(db.getAllDataBuku(), this)

        // Atur RecyclerView
        binding.rvDataBuku.layoutManager = LinearLayoutManager(this)
        binding.rvDataBuku.adapter = bukuAdapter

        // Tambah buku baru
        binding.btnPageTambah.setOnClickListener {
            val intent = Intent(this, TambahDataBukuActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val newBukuList = db.getAllDataBuku()
        bukuAdapter.refreshData(newBukuList)
    }
}
