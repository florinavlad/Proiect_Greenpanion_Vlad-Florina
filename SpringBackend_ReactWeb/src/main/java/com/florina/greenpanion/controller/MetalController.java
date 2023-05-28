package com.florina.greenpanion.controller;

import com.florina.greenpanion.dto.MetalDTO;
import com.florina.greenpanion.service.MetalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metal")
@RequiredArgsConstructor
@CrossOrigin("*")
public class MetalController {

    private final MetalService metalService;

    @GetMapping()
    public List<MetalDTO> findAll() {
        return metalService.findAll();
    }

    @PostMapping()
    public MetalDTO create(@RequestBody MetalDTO metal) {
        return metalService.create(metal);
    }

    @PutMapping("/{id}")
    public MetalDTO edit(@PathVariable Long id, @RequestBody MetalDTO metalDTO) {
        return metalService.update(id, metalDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        metalService.delete(id);
    }
}
