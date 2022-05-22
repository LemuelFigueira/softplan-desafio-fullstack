package com.br.api.model;

import com.br.api.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "tb_note", catalog = "primary")
public class Note extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_note_user"))
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "process", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_note_process"))
    private Process process;
}