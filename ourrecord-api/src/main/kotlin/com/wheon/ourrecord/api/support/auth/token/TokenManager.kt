package com.wheon.ourrecord.api.support.auth.token

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Component
class TokenManager(
    private val jwtEnvProperties: JwtEnvProperties,
) {
    fun issue(userId: Long): IssuedToken {
        val refreshTokenId = UUID.randomUUID().toString()

        val accessTokenExpireTime = jwtEnvProperties.accessTokenExpireTime
        val refreshTokenExpireTime = jwtEnvProperties.refreshTokenExpireTime

        return IssuedToken(
            accessToken = createAccessToken(userId, accessTokenExpireTime),
            refreshToken = createRefreshToken(userId, refreshTokenId, refreshTokenExpireTime),
            refreshTokenId = refreshTokenId,
            accessTokenExpiresAt = LocalDateTime.now().plusSeconds(accessTokenExpireTime / 1000),
            refreshTokenExpiresAt = LocalDateTime.now().plusSeconds(refreshTokenExpireTime / 1000),
        )
    }

    private val signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtEnvProperties.secretKey))

    private fun createAccessToken(userId: Long, expireTime: Long): String {
        return Jwts.builder()
            .id(UUID.randomUUID().toString())
            .subject(userId.toString())
            .issuedAt(Date())
            .expiration(Date(Date().time + expireTime))
            .claim("tokenType", "ACCESS")
            .signWith(signingKey)
            .compact()
    }

    private fun createRefreshToken(userId: Long, refreshTokenId: String, expireTime: Long): String {
        return Jwts.builder()
            .id(refreshTokenId)
            .subject(userId.toString())
            .issuedAt(Date())
            .expiration(Date(Date().time + expireTime))
            .claim("tokenType", "REFRESH")
            .signWith(signingKey)
            .compact()
    }

    fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(signingKey)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}
