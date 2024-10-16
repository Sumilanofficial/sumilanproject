package com.matrix.myjournal.questionresdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.matrix.myjournal.Entity.CombinedResponseEntity

@Dao
interface QuestionResDao {

    @Insert
    fun insertCombinedResponse(combinedResponseEntity: CombinedResponseEntity)

    @Update
    fun updateCombinedResponse(combinedResponseEntity: CombinedResponseEntity)

    @Query("SELECT COUNT(*) FROM CombinedResponseEntity")
    fun getTotalJournalEntries(): LiveData<Int>

    @Query("SELECT * FROM CombinedResponseEntity")
    fun getCombinedResponses(): List<CombinedResponseEntity>

    @Query("SELECT * FROM CombinedResponseEntity WHERE id = :combinedResponseId")
    fun getCombinedResponseById(combinedResponseId: Int): CombinedResponseEntity?

    @Query("DELETE FROM CombinedResponseEntity WHERE id = :combinedResponseId")
    fun deleteCombinedResponse(combinedResponseId: Int)

    @Query("SELECT entryDate, SUM(LENGTH(combinedResponse) - LENGTH(REPLACE(combinedResponse, ' ', '')) + 1) as wordCount FROM CombinedResponseEntity GROUP BY entryDate")
    fun getWordCountPerDay(): List<WordCountPerDay>

    @Query("SELECT COUNT(*) FROM CombinedResponseEntity WHERE imageDataBase64 IS NOT NULL AND imageDataBase64 <> ''")
    fun getTotalImagesCount(): LiveData<Int>

    @Query("SELECT SUM(LENGTH(combinedResponse) - LENGTH(REPLACE(combinedResponse, ' ', '')) + 1) FROM CombinedResponseEntity")
    fun getTotalWordCount(): LiveData<Int>

    @Query("""
        SELECT COUNT(*) FROM (
            SELECT COUNT(*) FROM CombinedResponseEntity
            GROUP BY strftime('%Y-%m-%d', entryDate)
        )
        
    """)
    fun getTotalStreaks(): LiveData<Int>
}

data class WordCountPerDay(
    val entryDate: String,
    val wordCount: Int
)
