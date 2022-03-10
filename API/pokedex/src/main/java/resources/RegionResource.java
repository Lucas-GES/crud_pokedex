package resources;

import com.api.pokedex.entities.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import services.RegionService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/region")
public class RegionResource {

    @Autowired
    private RegionService service;

    @GetMapping
    public ResponseEntity<List<Region>> findAll(){
        List<Region> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Region> insert(@RequestBody Region obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Region> update(@PathVariable Long id, @RequestBody Region obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }


}
