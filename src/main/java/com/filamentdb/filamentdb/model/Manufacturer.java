package com.filamentdb.filamentdb.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Entity(name = "manufacturers")
//@Table(name = "manufacturers")
public class Manufacturer {
    @Id
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "manufacturers_name")
    private String name;

    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createDateTime;

    @CreationTimestamp
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updateDateTime;

}
