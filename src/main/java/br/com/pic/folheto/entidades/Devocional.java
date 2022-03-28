package br.com.pic.folheto.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Devocional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    
	@OneToOne(cascade=CascadeType.ALL)
	private Referencia referencia;
	
	@JsonIgnore
    @ManyToOne(cascade=CascadeType.DETACH)
    @JoinColumn(name="igreja_id")
    private Igreja igreja;
	
	private String descricao;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate dataCriacao;
	
	private String textoReferencia;
	
	@Transient
	@Builder.Default
	private Boolean isAtual = false;
	
	@Transient
	@Builder.Default
	private Boolean isLido = false;
	
	private Boolean isDeletado;

	
}
