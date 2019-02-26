package com.ikempf

import cats.{Applicative, Parallel}
import ParallelExec.nonEmptyParallel

class Tagless[F[_] : ParallelExec : Applicative] {

  def fetchAB: F[(String, Int)] =
    Parallel.parTuple2(
      fetchA,
      fetchB
    )

  def fetchA: F[String] = Applicative[F].pure("A")

  def fetchB: F[Int] = Applicative[F].pure(5)

}
