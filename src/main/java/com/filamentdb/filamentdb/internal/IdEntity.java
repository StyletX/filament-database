package com.filamentdb.filamentdb.internal;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@MappedSuperclass
@EqualsAndHashCode
public class IdEntity<V> implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(updatable = false, nullable = false)
    protected V id;
}
