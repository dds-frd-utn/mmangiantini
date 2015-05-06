package domain;

public class Comentario {
	private String texto;
	
	public Comentario(String comentario) {
		this.texto = comentario;
	}
	
	public String mostrar() {
		return texto;
	}
	
	public boolean esLargo() {
		return (texto.length() > 100);
	}
}
