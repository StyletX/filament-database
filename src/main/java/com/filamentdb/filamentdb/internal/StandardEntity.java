package com.filamentdb.filamentdb.internal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@ToString(callSuper = true)
//@JsonView(JsonViews.Base.class)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public abstract class StandardEntity extends IdEntity<Long> {
    @CreatedDate
    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime creationDate;

    @UpdateTimestamp
    @LastModifiedDate
    @Setter(AccessLevel.NONE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modify_date", nullable = false, updatable = false)
//    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    protected LocalDateTime modificationDate;

    @NotNull
    @JsonIgnore
    protected boolean deleted;

    public void markDeleted() {
        deleted = true;
    }
}