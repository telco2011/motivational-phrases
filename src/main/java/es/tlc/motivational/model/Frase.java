package es.tlc.motivational.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "motivacion")
public class Frase {

	@Id
	private ObjectId id;
	private Integer contador;
	private String frase;
	private String autor;
	private Boolean mostrado;

	public Frase(){}
	
	public Frase(String contador, String frase, String autor, Boolean mostrado) {
		this.contador = new Integer(contador);
		this.frase = frase;
		this.autor = autor;
		this.mostrado = mostrado;
	}

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public Integer getContador() {
		return contador;
	}

	public void setContador(Integer contador) {
		this.contador = contador;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Boolean getMostrado() {
		return mostrado;
	}

	public void setMostrado(Boolean mostrado) {
		this.mostrado = mostrado;
	}

	@Override
	public String toString() {
		return "Frase [id=" + id + ", Frase #=" + contador + ", frase=" + frase + ", autor=" + autor + "]";
	}

}