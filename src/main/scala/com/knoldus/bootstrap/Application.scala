package com.knoldus.bootstrap

import com.typesafe.config.ConfigFactory

import scala.concurrent.ExecutionContext.Implicits.global

object Application extends App {

  val conf = ConfigFactory.load()
  val repositoryInstantiator = new RepositoryInstantiator(conf.getConfig("db"))

}
