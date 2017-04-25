package com.example.marton.stephane.mobsoft_lab.models;

import java.util.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.google.gson.annotations.SerializedName;




@ApiModel(description = "")
public class Profile   {
  
  @SerializedName("id")
  private Integer id = null;
  
  @SerializedName("username")
  private String username = null;
  
  @SerializedName("email")
  private String email = null;
  

  
  /**
   * User id.
   **/
  @ApiModelProperty(value = "User id.")
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  
  /**
   * First name.
   **/
  @ApiModelProperty(value = "First name.")
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  
  /**
   * Email address.
   **/
  @ApiModelProperty(value = "Email address.")
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(id, profile.id) &&
        Objects.equals(username, profile.username) &&
        Objects.equals(email, profile.email);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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
