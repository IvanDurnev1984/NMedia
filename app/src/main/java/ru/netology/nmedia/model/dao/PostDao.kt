package ru.netology.nmedia.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.netology.nmedia.model.dto.Post
import ru.netology.nmedia.model.entity.PostEntity

@Dao
interface PostDao {

    @Query("SELECT * FROM PostEntity ORDER BY id DESC")
    fun getAll(): LiveData<List<PostEntity>>

//    @Insert
//    fun savePost(postEntity: PostEntity)

    @Insert
    fun insert(postEntity: PostEntity)

    @Query("UPDATE PostEntity SET content = :content WHERE id = :id")
    fun updateContentById(id: Long, content: String)

    fun savePost(postEntity: PostEntity) =
        if (postEntity.id == 0L)
            insert(postEntity)
        else updateContentById(postEntity.id, postEntity.content)

    @Query("""
        UPDATE PostEntity SET
        likesCount = likesCount + CASE WHEN likedByMe THEN -1 ELSE 1 END,
        likedByMe = CASE WHEN likedByMe THEN 0 ELSE 1 END
        WHERE id = :id
    """)
    fun likeById(id: Long)

    @Query("""
        UPDATE PostEntity SET
        shareCount = shareCount + 1
        WHERE id = :id
    """)
    fun shareById(id: Long)

    @Query("""
        UPDATE PostEntity SET
        viewingCount = viewingCount + 1
        WHERE id = :id
    """)
    fun viewingById(id: Long)

    @Query("DELETE FROM PostEntity WHERE id = :id")
    fun removeById(id: Long)
}