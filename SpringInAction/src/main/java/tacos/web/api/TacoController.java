package tacos.web.api;

import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tacos.Taco;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {

	private TacoRepository tacoRepo;
	
	public TacoController(TacoRepository tacoRepo) {
		 this.tacoRepo = tacoRepo;
	}
	
	@GetMapping(params="recent")
	public Iterable<Taco> recentTacos(){
		Pageable page= PageRequest.of(0, 12, Sort.by("createdAt").descending());
		return tacoRepo.findAll(page);
 	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id){
		Optional<Taco> optTaco= tacoRepo.findById(id);
		if(optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}