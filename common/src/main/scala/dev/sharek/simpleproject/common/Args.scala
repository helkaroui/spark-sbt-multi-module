package dev.sharek.simpleproject.common

import scopt.OParser

case class Args(
                 verbose: Boolean = false,
                 debug: Boolean = false,
                 kwargs: Map[String, String] = Map()
               )

object Args {

  val builder = OParser.builder[Args]
  val parser1 = {
    import builder._
    OParser.sequence(
      opt[Unit]('v', "verbose")
        .action((_, c) => c.copy(verbose = true))
        .text("verbose is a flag"),
      opt[Unit]('d',"debug")
        .action((_, c) => c.copy(debug = true))
        .text("Enable debugging"),
      opt[Map[String, String]]("kwargs")
        .valueName("k1=v1,k2=v2...")
        .action((x, c) => c.copy(kwargs = x))
        .text("other arguments"),
      help("help")
    )
  }

  def apply(args: Array[String]): Args = {
    OParser.parse(parser1, args, Args()) match {
      case Some(config) => config
      case _ => throw new Exception(s"Parsing command line options failed. Used args are : ${args.mkString(", ")}")
    }
  }
}