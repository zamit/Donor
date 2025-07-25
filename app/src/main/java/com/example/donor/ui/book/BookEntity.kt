import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")  // Указываем имя таблицы
data class BookEntity(
    @PrimaryKey(autoGenerate = true)  // Автоинкремент для id
    val id: Int = 0,

    @ColumnInfo(name = "data")  // Поле "data" в таблице
    val data: String,

    @ColumnInfo(name = "hospital")  // Поле "hospital" в таблице
    val hospital: String,

    @ColumnInfo(name = "kommentarii")  // Поле "kommentarii" в таблице
    val kommentarii: String
)