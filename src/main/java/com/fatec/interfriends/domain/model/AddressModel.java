package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatec.interfriends.domain.dto.address.AddressRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class AddressModel implements Serializable {

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
    private UserModel user;

    public AddressModel(AddressRequestDto addressRequestDto) {
        BeanUtils.copyProperties(addressRequestDto, this);
    }

}
