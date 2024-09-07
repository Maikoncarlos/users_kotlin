package rest_api_.kotlin.users

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/api/user")
class UserController(val repository: UserRepository) {

    // TODO implementar logs
    // TODO implementar documentação com openAI / Swagger
    // TODO implementar tratamento de excessão
    // TODO implementar camada de service e interface para inversão de dependencia
    // TODO implementar validação dos parametros de request
    // TODO implementar alguns regras de negócio novas
    // TODO implementar kafka para alguma regra de envio de msg após o cadastro

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<User>> =
        ResponseEntity.ok(repository.findAll())

    // TODO implementar com URI
    @PostMapping
    fun createdUser(@RequestBody user: User): ResponseEntity<User> =
        ResponseEntity(repository.save(user), HttpStatus.CREATED)

    @GetMapping("/{id}")
    fun getUserIByd(@PathVariable("id") id: Int): ResponseEntity<User> {
        val userOptional = repository.findById(id).orElse(null) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(userOptional)
    }

    @PutMapping("/{id}")
    fun updateUserById(
        @PathVariable("id") id: Int,
        @RequestBody user: User
    ): ResponseEntity<User> {
        val userOptional = repository.findById(id).orElse(null) ?: return ResponseEntity.notFound().build()

        val updateUser = userOptional.copy(id = userOptional.id, name = user.name, email = user.email)
        repository.save(updateUser)

        return ResponseEntity.ok(updateUser)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") id: Int): ResponseEntity<Unit> {
        val userOptional = repository.findById(id).orElse(null) ?: return ResponseEntity.notFound().build()

        repository.delete(userOptional)

        return ResponseEntity.noContent().build()

    }
}