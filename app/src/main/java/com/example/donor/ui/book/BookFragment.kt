package com.example.donor.ui.book

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.donor.R
import com.example.donor.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    private var _binding: FragmentBookBinding? = null
    private lateinit var bookViewModel: BookViewModel
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var adapter: BooksAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(BookViewModel::class.java)

        _binding = FragmentBookBinding.inflate(inflater, container, false)
        val root: View = binding.root

        databaseHelper = DatabaseHelper(requireContext())
        setupRecyclerView()
        setupClickListeners()
        loadBooks()
        return root
    }
    private fun setupRecyclerView() {
        adapter = BooksAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupClickListeners() {
        binding.btnAdd.setOnClickListener {
            addBook()
        }
    }

    private fun loadBooks() {
        val books = databaseHelper.getAllBooks()  // Получаем список книг
        adapter = BooksAdapter(books)            // Передаём в адаптер
        binding.recyclerView.adapter = adapter

    }

    private fun addBook() {
        val data = binding.etData.text.toString().trim()
        val hospital = binding.etHospital.text.toString().trim()
        val kommentarii = binding.etKommentarii.text.toString().trim()

        if (data.isEmpty() || hospital.isEmpty() || kommentarii.isEmpty()) {
            Toast.makeText(requireContext(), "Заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }

        val book = Book(data = data, hospital = hospital, kommentarii = kommentarii)
        val id =databaseHelper.addBook(book)

        if (id != -1L) {
            Toast.makeText(requireContext(), "Запись добавлена", Toast.LENGTH_SHORT).show()
            clearInputs()
            loadBooks()
        } else {
            Toast.makeText(requireContext(), "Ошибка при добавлении", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputs() {
        binding.etData.text.clear()
        binding.etHospital.text.clear()
        binding.etKommentarii.text.clear()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}