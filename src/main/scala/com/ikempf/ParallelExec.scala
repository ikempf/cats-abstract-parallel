package com.ikempf

import cats.NonEmptyParallel
import cats.effect.{ContextShift, IO}

trait ParallelExec[F[_]] {
  type G[_]
  val evidence: NonEmptyParallel[F, G]
}

object ParallelExec {

  def apply[F[_]](implicit P: ParallelExec[F]): ParallelExec[F] = P

  implicit def ioInstance(implicit contextShift: ContextShift[IO]): ParallelExec[IO] =
    new ParallelExec[IO] {
      override type G[A] = IO.Par[A]
      override val evidence: NonEmptyParallel[IO, IO.Par] = IO.ioParallel
    }

  implicit def nonEmptyParallel[F[_]](implicit parallelExec: ParallelExec[F]): NonEmptyParallel[F, parallelExec.G] =
    parallelExec.evidence

}
