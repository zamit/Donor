package com.example.donor.ui.book

import android.view.LayoutInflater
import com.example.donor.R
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class BooksAdapter(private val books: List<Book>) : RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvData: TextView = itemView.findViewById(R.id.tv_data)
        val tvHospital: TextView = itemView.findViewById(R.id.tv_hospital)
        val tvKommentarii: TextView = itemView.findViewById(R.id.tv_kommentarii)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentBook = books[position]
        holder.tvData.text = currentBook.data
        holder.tvHospital.text = currentBook.hospital
        holder.tvKommentarii.text = currentBook.kommentarii
    }

    override fun getItemCount() = books.size
}