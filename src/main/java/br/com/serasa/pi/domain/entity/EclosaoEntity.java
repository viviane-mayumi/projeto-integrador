package br.com.serasa.pi.domain.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "eclosao")
public class EclosaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_eclosao")
	private Integer idEclosao;

	@Column(name = "numero_cova")
	private String numeroCova;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	@Column(name = "especie")
	private String especie;

	@Column(name = "quantidade_filhote_vivo")
	private Integer quantidadeFilhoteVivo;

	@Column(name = "quantidade_ovo_inviavel")
	private Integer quantidadeOvoInviavel;

	@Column(name = "quantidade_ovo_infertil")
	private Integer quantidadeOvoInfertil;

	@Column(name = "quantidade_filhote_morto_formiga")
	private Integer quantidadeFilhoteMortoFormiga;

	@Column(name = "quantidade_filhote_morto_bicheira")
	private Integer quantidadeFilhoteMortoBicheira;

	@Column(name = "quantidade_filhote_morto_outros")
	private Integer quantidadeFilhoteMortoOutros;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idVoluntario", referencedColumnName = "id")
	private UsuarioEntity voluntario;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idViagem", referencedColumnName = "id_viagem")
	private ViagemEntity viagem;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EclosaoEntity other = (EclosaoEntity) obj;
		return Objects.equals(dataNascimento, other.dataNascimento) && Objects.equals(especie, other.especie)
				&& Objects.equals(idEclosao, other.idEclosao) && Objects.equals(numeroCova, other.numeroCova)
				&& Objects.equals(quantidadeFilhoteMortoBicheira, other.quantidadeFilhoteMortoBicheira)
				&& Objects.equals(quantidadeFilhoteMortoFormiga, other.quantidadeFilhoteMortoFormiga)
				&& Objects.equals(quantidadeFilhoteMortoOutros, other.quantidadeFilhoteMortoOutros)
				&& Objects.equals(quantidadeFilhoteVivo, other.quantidadeFilhoteVivo)
				&& Objects.equals(quantidadeOvoInfertil, other.quantidadeOvoInfertil)
				&& Objects.equals(quantidadeOvoInviavel, other.quantidadeOvoInviavel);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataNascimento, especie, idEclosao, numeroCova, quantidadeFilhoteMortoBicheira,
				quantidadeFilhoteMortoFormiga, quantidadeFilhoteMortoOutros, quantidadeFilhoteVivo,
				quantidadeOvoInfertil, quantidadeOvoInviavel);
	}
}
