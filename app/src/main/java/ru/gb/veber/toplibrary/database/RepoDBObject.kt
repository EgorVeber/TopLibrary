package ru.gb.veber.toplibrary.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


//Старый способ
//@Entity(tableName = "repos", foreignKeys =
//[ForeignKey(
//    entity = UserDbObject::class,
//    parentColumns = ["id"],
//    childColumns = ["userId"],
//    onDelete = ForeignKey.CASCADE
//)])

@Entity(tableName = "repos")
class RepoDBObject(
    @PrimaryKey
    @ColumnInfo(name = PRIMARY_KEY) // можно менять название
    val id: Int,
    val forks: Int,
    val name: String,
    @ColumnInfo(name = FOREIGN_USER_KEY)
    val userId: Int,
) {
    companion object {
        const val PRIMARY_KEY = "id"
        const val FOREIGN_USER_KEY = "userId"
    }
}