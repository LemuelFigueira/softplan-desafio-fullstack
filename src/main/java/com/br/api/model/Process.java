package com.br.api.model;

import com.br.api.base.BaseEntity;
import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_process", catalog = "primary")
public class Process extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String title;

    private String subtitle;

    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_process_user"))
    private User user;
}