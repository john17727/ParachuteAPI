package dev.juanrincon.domain

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import dev.juanrincon.domain.models.JwtDetails
import java.util.*

fun generateToken(userId: Int, jwtDetails: JwtDetails) = JWT.create()
    .withAudience(jwtDetails.audience)
    .withIssuer(jwtDetails.issuer)
    .withClaim("userId", userId)
    .withExpiresAt(Date(System.currentTimeMillis() + 60000))
    .sign(Algorithm.HMAC512(jwtDetails.secret))