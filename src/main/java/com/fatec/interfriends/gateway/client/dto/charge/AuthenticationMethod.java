package com.fatec.interfriends.gateway.client.dto.charge;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationMethod {

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "cavv")
    private String cavv;

    @JsonProperty(value = "xid")
    private String xid;

    @JsonProperty(value = "eci")
    private String eci;

    @JsonProperty(value = "version")
    private String version;

    @JsonProperty(value = "dstrans_id")
    private String dstransId;

}
