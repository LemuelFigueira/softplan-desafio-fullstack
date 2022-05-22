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
@Table(name = "tb_process_user", catalog = "primary")
public class ProcessUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_process_user_list_user"))
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "process", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_process_user_list_process"))
    private Process process;
}