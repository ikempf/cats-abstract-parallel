package com.ikempf

import cats.Parallel
import cats.effect.{ExitCode, IO, IOApp}
import cats.syntax.functor._

object Main extends IOApp {

  private val tag = new Tagless[IO]

  override def run(args: List[String]): IO[ExitCode] =
    tag
      .fetchAB
      .as(ExitCode.Success)

  def ioParallelExample: IO[(Int, String)] =
    Parallel.parTuple2(
      IO(5),
      IO(" ")
    )(IO.ioParallel(contextShift))


}
