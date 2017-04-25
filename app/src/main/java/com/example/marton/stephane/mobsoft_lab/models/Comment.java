package com.example.marton.stephane.mobsoft_lab.models;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;


@ApiModel(description = "")
public class Comment   {

    @SerializedName("comment_id")
    private String commentId = null;

    @SerializedName("anime_id")
    private String animeId = null;

    @SerializedName("user_name")
    private String userName = null;

    @SerializedName("comment")
    private String comment = null;

    @SerializedName("time")
    private String time = null;



    /**
     * Comment's unique ID.
     **/
    @ApiModelProperty(value = "Comment's unique ID.")
    public String getCommentId() {
        return commentId;
    }
    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }


    /**
     * Anime's ID.
     **/
    @ApiModelProperty(value = "Anime's ID.")
    public String getAnimeId() {
        return animeId;
    }
    public void setAnimeId(String animeId) {
        this.animeId = animeId;
    }


    /**
     * User name who wrote the comment.
     **/
    @ApiModelProperty(value = "User name who wrote the comment.")
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }


    /**
     * User's comment to the Anime.
     **/
    @ApiModelProperty(value = "User's comment to the Anime.")
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }


    /**
     * When was the comment sent.
     **/
    @ApiModelProperty(value = "When was the comment sent.")
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }


    public Comment(String message_id, String anime_id, String user, String comment, String date) {
        this.commentId = message_id;
        this.animeId = anime_id;
        this.userName = user;
        this.comment = comment;
        this.time = date;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return Objects.equals(commentId, comment.commentId) &&
                Objects.equals(animeId, comment.animeId) &&
                Objects.equals(userName, comment.userName) &&
                Objects.equals(comment, comment.comment) &&
                Objects.equals(time, comment.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, animeId, userName, comment, time);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Comment {\n");

        sb.append("    commentId: ").append(toIndentedString(commentId)).append("\n");
        sb.append("    animeId: ").append(toIndentedString(animeId)).append("\n");
        sb.append("    userName: ").append(toIndentedString(userName)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    time: ").append(toIndentedString(time)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

