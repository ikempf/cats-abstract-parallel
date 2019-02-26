## Abstracting over cats' Parallel


When using tagless final it is not possible to abstract over Parallel[M[_], F[_]] event though IO has a Parallel instance (Parallel[IO, IO.Par]).

We can't abstract over Parallel[M[_], F[_]] since it needs a concrete F[_] (for instance IO.par for IO)