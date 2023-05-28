package com.florina.greenpanion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plastic {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long Id;

        @Enumerated(EnumType.STRING)
        private EPlastic name;

        public Plastic(EPlastic name) {
                this.name = name;
        }
}
