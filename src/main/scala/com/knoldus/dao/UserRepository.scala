package com.knoldus.dao

import com.knoldus.models.UserModel.User

import scala.concurrent.Future

trait UserRepository {

  def addUser(user: User): Future[Option[User]]

}
