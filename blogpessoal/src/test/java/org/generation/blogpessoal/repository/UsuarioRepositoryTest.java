package org.generation.blogpessoal.repository;

import org.generation.blogpessoal.model.Usuario;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){

        usuarioRepository.deleteAll();

        usuarioRepository.save(new Usuario(0L, "João da Silva", "joao@email.com.br", "12345678", "http://"));

        usuarioRepository.save(new Usuario(0L, "Maria da Silva", "maria@email.com.br", "87654321", "http://"));

        usuarioRepository.save(new Usuario(0L, "Vinicius da Silva", "vinicius@email.com.br", "33333333", "http://"));

        usuarioRepository.save(new Usuario(0L, "Elizangela Xavier", "elizangela@email.com.br", "44444444", "http://"));

    }

    @Test
    @DisplayName("Retorna 1 usuario")
    public void deveRetornarUmUsuario(){

        Optional<Usuario> usuario = usuarioRepository.findByUsuario("joao@email.com.br");
        assertTrue(usuario.get().getUsuario().equals("joao@email.com.br"));
    }

    @Test
    @DisplayName("Retorna 3 usuarios")
    public void deveRetornarTresUsuarios(){

        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Silva");
        assertEquals(3, listaDeUsuarios.size());
        assertTrue(listaDeUsuarios.get(0).getNome().equals("João da Silva"));
        assertTrue(listaDeUsuarios.get(1).getNome().equals("Maria da Silva"));
        assertTrue(listaDeUsuarios.get(2).getNome().equals("Vinicius da Silva"));

    }

    @AfterAll
    public void end(){
        usuarioRepository.deleteAll();
    }
}
