package com.knoldus.models

object UserModel {

  case class User(userId: String, userName: String, moreInfo: Option[Map[String, String]])

}
