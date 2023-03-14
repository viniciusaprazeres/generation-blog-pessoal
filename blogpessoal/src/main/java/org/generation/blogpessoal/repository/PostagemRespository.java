package org.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.generation.blogpessoal.model.Postagem;

@Repository
public interface PostagemRespository extends JpaRepository <Postagem, Long> {

}
