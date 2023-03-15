package org.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.generation.blogpessoal.model.Postagem;

import java.util.List;

@Repository
public interface PostagemRespository extends JpaRepository <Postagem, Long> {

    public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
