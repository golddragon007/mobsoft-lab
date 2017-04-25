package com.example.marton.stephane.mobsoft_lab.network.Comments;


import com.example.marton.stephane.mobsoft_lab.models.Comment;
import com.example.marton.stephane.mobsoft_lab.models.CommentWithoutId;

import retrofit2.Call;
import retrofit2.http.*;

import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CommentsApi {
  
  /**
   * Comments for a specific anime
   * This will return all the comments for a specific Anime.
   * @param animeId Set the anime, which you want to get it&#39;s comments.
   * @return Call<List<Comment>>
   */
  
  @GET("comments")
  Call<List<Comment>> commentsGet(
    @Query("anime_id") Integer animeId
  );

  
  /**
   * Create a new comment.
   * This will a new comment for a specific Anime.
   * @param data Comment data.
   * @return Call<Void>
   */
  
  @PUT("comments")
  Call<Void> commentsPut(
    @Body CommentWithoutId data
  );

  
  /**
   * Edit specific Anime.
   * Use this for edit an Anime.
   * @param data Comment data.
   * @return Call<Void>
   */
  
  @POST("comments")
  Call<Void> commentsPost(
    @Body Comment data
  );

  
  /**
   * Delete comment
   * This will delete a specific comment.
   * @param commentId Set the comment, which you want to delete.
   * @return Call<Void>
   */
  
  @FormUrlEncoded
  @DELETE("comments")
  Call<Void> commentsDelete(
    @Field("comment_id") Integer commentId
  );

  
}
