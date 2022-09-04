package dev.juanrincon.domain.interfaces.utilities

interface Repository<M, E>: ImmutableRepository<E>, MutableRepository<M, E>