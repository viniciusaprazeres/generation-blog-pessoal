package org.generation.blogpessoal.controller;

import org.generation.blogpessoal.model.Postagem;
import org.generation.blogpessoal.repository.PostagemRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

    @Autowired
    private PostagemRespository postagemRespository;

    @GetMapping
    public ResponseEntity<List<Postagem>> getAll(){
        return ResponseEntity.ok(postagemRespository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity <Postagem>  getById(@PathVariable Long id){
        return postagemRespository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity <List<Postagem>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(postagemRespository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity <Postagem> post(@Valid @RequestBody Postagem postagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(postagemRespository.save(postagem));
    }

    @PutMapping
    public ResponseEntity <Postagem> put(@Valid @RequestBody Postagem postagem){
        return postagemRespository.findById(postagem.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRespository.save(postagem))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional <Postagem> postagem = postagemRespository.findById(id);

        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        postagemRespository.deleteById(id);
    }

}
