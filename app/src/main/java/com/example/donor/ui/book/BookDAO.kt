package com.example.donor.ui.book
import BookEntity
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    suspend fun insert(book: BookEntity): Long
    
    @Query("SELECT * FROM books ORDER BY id DESC")
    fun getAllBooks(): Flow<List<BookEntity>>
}



