package com.fatec.interfriends.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fatec.interfriends.domain.dto.phone.PhoneRequestDto;
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
@Table(name = "phone")
public class Phone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long phoneId;
    @Column(nullable = false)
    private String ddi;
    @Column(nullable = true)
    private String ddd;
    @Column(nullable = false)
    private String number;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Phone(PhoneRequestDto phoneRequestDto) {
        BeanUtils.copyProperties(phoneRequestDto, this);
    }

}
