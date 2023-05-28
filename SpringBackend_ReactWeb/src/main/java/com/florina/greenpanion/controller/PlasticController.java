package com.florina.greenpanion.controller;

import com.florina.greenpanion.dto.PlasticDTO;
import com.florina.greenpanion.service.PlasticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plastic")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PlasticController {
    private final PlasticService plasticService;

    @GetMapping()
    public List<PlasticDTO> findAll() {
        return plasticService.findAll();
    }

    @PostMapping()
    public PlasticDTO create(@RequestBody PlasticDTO plastic) {
        return plasticService.create(plastic);
    }

    @PutMapping("/{id}")
    public PlasticDTO edit(@PathVariable Long id, @RequestBody PlasticDTO plasticDTO) {
        return plasticService.update(id, plasticDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        plasticService.delete(id);
    }
}
