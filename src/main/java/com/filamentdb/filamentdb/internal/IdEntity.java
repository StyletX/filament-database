package com.filamentdb.filamentdb.internal;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@ToString
@MappedSuperclass
@EqualsAndHashCode
//@JsonView(JsonViews.Base.class)
public class IdEntity<V> implements Serializable {
    @Id
    @Setter(AccessLevel.NONE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    protected V id;
}
