package dev.juanrincon.domain.interfaces

interface DomainMapper<model> {
    fun toModel(): model
}