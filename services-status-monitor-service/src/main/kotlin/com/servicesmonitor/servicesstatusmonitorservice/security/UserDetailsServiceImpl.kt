package com.servicesmonitor.servicesstatusmonitorservice.security

import com.servicesmonitor.servicesstatusmonitorservice.repo.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class UserDetailsServiceImpl(
    val userRepository: UserRepository
) : UserDetailsService {


    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userRepository.findByEmail(email)
            ?: throw UsernameNotFoundException("No user found with email: $email")
        val enabled = true
        val accountNonExpired = true
        val credentialsNonExpired = true
        val accountNonLocked = true

        return org.springframework.security.core.userdetails.User(
            user.email, user.password, enabled, accountNonExpired,
            credentialsNonExpired, accountNonLocked, getAuthorities(user.roles)
        )
    }

    private fun getAuthorities(roles: List<String>): List<GrantedAuthority> {
        val authorities: MutableList<GrantedAuthority> = ArrayList()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority(role))
        }
        return authorities
    }

}
