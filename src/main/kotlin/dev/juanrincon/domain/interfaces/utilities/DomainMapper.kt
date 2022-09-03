package dev.juanrincon.domain.interfaces.utilities

interface DomainMapper<model> {
    fun toModel(): model
}