package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatec.interfriends.domain.dto.address.AddressRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long addressId;
    @Column(nullable = false)
    private String publicLocal;
    @Column(nullable = false)
    private Integer number;
    @Column(nullable = true)
    private String complement;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Order> orders = new ArrayList<>();

    public Address(AddressRequestDto addressRequestDto) {
        BeanUtils.copyProperties(addressRequestDto, this);
    }

}
