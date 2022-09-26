package com.fatec.interfriends.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "size")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SizeModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sizeId;
    @Column(nullable = false)
    private String size;
    @Column(nullable = true)
    private String description;


}
