package dev.sharek.ucc

object Hello extends App {

  override def main(args: Array[String]): Unit = {
    val conf = CommonConfig()
    println(conf.host)
  }

}