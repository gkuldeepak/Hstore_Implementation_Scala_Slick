package com.knoldus.bootstrap

import com.typesafe.config.Config
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import slick.util.AsyncExecutor
import slick.jdbc.MySQLProfile.api._

import scala.concurrent.ExecutionContext

class RepositoryInstantiator(dbConfig: Config)(implicit ec: ExecutionContext) {

  private val DEFAULT_CONNECTION_TIMEOUT: Int = 5000
  private val DEFAULT_QUEUE_SIZE: Int = 1000

  private val dbMaxConnection: Int = dbConfig.getInt("max-connections")

  private val hikariConfig = new HikariConfig()
  hikariConfig.setJdbcUrl(dbConfig.getString("url"))
  hikariConfig.setUsername(dbConfig.getString("user"))
  hikariConfig.setPassword(dbConfig.getString("password"))
  hikariConfig.setDriverClassName(dbConfig.getString("driver"))
  hikariConfig.setMaximumPoolSize(dbMaxConnection)
  hikariConfig.setConnectionTimeout(DEFAULT_CONNECTION_TIMEOUT)

  val dataSource = new HikariDataSource(hikariConfig)

  private val db: Database = Database.forDataSource(
    dataSource,
    Some(dbMaxConnection),
    AsyncExecutor
      .apply("postgres", dbMaxConnection, dbMaxConnection, DEFAULT_QUEUE_SIZE, dbMaxConnection)
  )

}
