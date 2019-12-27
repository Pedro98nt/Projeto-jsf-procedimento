package br.edu.faculdadedelta.projetoprocedimentojs.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Procedimento {

	private Long id;
	private String paciente;
	private Double valor;
	private Date dataInicio;
	private Date dataFim;
	private Integer quantidade;

	public Procedimento() {
		super();
	}

	public Procedimento(Long id, String paciente, Double valor, Date dataInicio, Date dataFim, Integer quantidade) {
		super();
		this.id = id;
		this.paciente = paciente;
		this.valor = valor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.quantidade = quantidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getValorTotal() {
		Double valorTotal = getValor() * quantidade;
		if (getValor() > 300) {
			valorTotal = valorTotal - (valorTotal * 0.05);
		}
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dataReferencia = sf.parse("01/01/2018");
			if (getDataInicio().after(dataReferencia)) {
				valorTotal = valorTotal - (valorTotal * 0.04);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return valorTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Procedimento other = (Procedimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
