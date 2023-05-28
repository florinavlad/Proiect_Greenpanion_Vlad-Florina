package com.florina.greenpanion.controller;

import com.florina.greenpanion.dto.PaperDTO;
import com.florina.greenpanion.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paper")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PaperController {

    private final PaperService paperService;

    @GetMapping()
    public List<PaperDTO> findAll() {
        return paperService.findAll();
    }

    @PostMapping()
    public PaperDTO create(@RequestBody PaperDTO paper) {
        return paperService.create(paper);
    }

    @PutMapping("/{id}")
    public PaperDTO edit(@PathVariable Long id, @RequestBody PaperDTO paperDTO) {
        return paperService.update(id, paperDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        paperService.delete(id);
    }
}
