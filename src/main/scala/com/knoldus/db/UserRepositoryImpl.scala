package com.knoldus.db

import com.github.tminglei.slickpg._
import com.knoldus.dao.UserRepository
import com.knoldus.models.UserModel.User
import slick.lifted.ProvenShape
import slick.lifted.{TableQuery, Tag}



trait MyPostgresProfile extends ExPostgresProfile
  with PgArraySupport
  with PgHStoreSupport {

  override val api = MyAPI

  object MyAPI extends API
    with ArrayImplicits
    with HStoreImplicits {
    implicit val strListTypeMapper = new SimpleArrayJdbcType[String]("moreInfo").to(_.toList)
  }
}

object MyPostgresProfile extends MyPostgresProfile

import MyPostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

class UserRepositoryImpl (db: Database)(implicit ec: ExecutionContext)
  extends TableQuery(new UserTable(_))
    with UserRepository{

  def addUser(user: User): Future[Option[User]] = {
    val response = db.run(this returning this.map(_.userId) += user)
    response.map(_ => Some(user))
  }

}

class UserTable(tag: Tag) extends Table[User] (tag, "userInfo") {

  def userId: Rep[String] = column[String]("userId")

  def userName: Rep[String] = column[String]("userName")

  def moreInfo = column[Option[Map[String, String]]]("moreInfo")

  def * : ProvenShape[User] = (userId, userName, moreInfo) <> (User.tupled, User.unapply)

}
