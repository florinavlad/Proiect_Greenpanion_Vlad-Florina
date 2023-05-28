package com.florina.greenpanion.service;

import com.florina.greenpanion.dto.MetalDTO;
import com.florina.greenpanion.model.Metal;
import com.florina.greenpanion.repository.MetalRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetalService {
    private final MetalRepository metalRepository;

    public Metal findById(Long id) {
        return metalRepository.findById(Long.valueOf(id)).orElseThrow(() -> new EntityNotFoundException("Metal not found: " + id));
    }

    public List<MetalDTO> findAll() {
        List<Metal> metals = new ArrayList<>();
        metals = metalRepository.findAll();

        List<MetalDTO> metalsDTO = new ArrayList<>();

        for (Metal metal : metals) {
            MetalDTO metalDTO = new MetalDTO();
            metalDTO.setId(metal.getId());
            metalDTO.setMetalType(metal.getName());

            metalsDTO.add(metalDTO);
        }
        return metalsDTO;
    }

    public MetalDTO create(MetalDTO metalDTO) {
        Metal metal = new Metal();
        metal.setId(metalDTO.getId());
        metal.setName(metalDTO.getMetalType());

        Metal metal1 = metalRepository.save(metal);

        MetalDTO metalDTO1 = new MetalDTO();
        metalDTO1.setId(metal1.getId());
        metalDTO1.setMetalType(metal1.getName());

        return metalDTO1;
    }

    public void delete(long id) {
        metalRepository.deleteById(id);
    }

    public MetalDTO update(Long id, MetalDTO metalDTO) {
        Metal metal = findById(id);
        metalDTO.setId(metal.getId());
        metalDTO.setMetalType(metal.getName());

        Metal metal1 = metalRepository.save(metal);

        MetalDTO metalDTO1 = new MetalDTO();
        metalDTO1.setId(metal1.getId());
        metalDTO1.setMetalType(metal1.getName());

        return metalDTO1;
    }

    public Integer calculatePointsMetal(List<MetalDTO> metals) {
        int sum = 0;
        for (MetalDTO metal : metals) {
            if (metal.getMetalType().name().equals("Conserva"))
                sum = sum + 50;
            else if (metal.getMetalType().name().equals("Doza"))
                sum = sum + 20;
        }
        return sum;
    }
}
