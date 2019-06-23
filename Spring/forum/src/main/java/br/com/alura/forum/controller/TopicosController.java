package br.com.alura.forum.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controller.dto.TopicoTdo;
import br.com.alura.forum.controller.form.AtualizarTopicoForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;


@RestController
public class TopicosController {
	
	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;
	
	@RequestMapping("home")
	public String Home() {
		return "teste";
	}
	
	@GetMapping("/topicos")
	@Cacheable(value = "listaDetopicos")
	public Page<TopicoTdo> lista(String nomeCurso,@PageableDefault(sort = "id", direction = Direction.DESC) Pageable pageable){
		//Topico topico = new Topico("Duvido", "Duvido com spring", new Curso("Spring" , "Programação"));
		//	Pageable pageable = PageRequest.of(pagina, qtd,Direction.ASC,ordenacao);
		
		if(nomeCurso == null) {
			Page<Topico> topicos = topicoRepository.findAll(pageable);
			return  TopicoTdo.converter(topicos);
		}else {
			Page<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso, pageable);
			return  TopicoTdo.converter(topicos);
		}
		
		
	}
	
	@PostMapping("/topicos")
	@CacheEvict(value="listaDetopicos", allEntries= true)
	public ResponseEntity<TopicoTdo> cadastrar(@RequestBody TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
		Topico topico = form.converter(cursoRepository);
		topicoRepository.save(topico);
		
		URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoTdo(topico));
	}
	
	@PutMapping("/topicos/{id}")
	@Transactional
	@CacheEvict(value="listaDetopicos", allEntries= true)
	public ResponseEntity<TopicoTdo> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizarTopicoForm form){
		Topico topico = form.atualizar(id, topicoRepository);
		return ResponseEntity.ok(new TopicoTdo(topico));
	}
  	
	@DeleteMapping("/topicos/{id}")
	@CacheEvict(value="listaDetopicos", allEntries= true)
	public ResponseEntity<?> remover(@PathVariable Long id){
		topicoRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
