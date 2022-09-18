package dev.juanrincon.security.token

interface TokenService {
    fun generate(config: TokenConfig, vararg claim: TokenClaim): String
}