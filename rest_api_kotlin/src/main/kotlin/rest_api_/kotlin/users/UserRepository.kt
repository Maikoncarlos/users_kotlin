package rest_api_.kotlin.users

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int>