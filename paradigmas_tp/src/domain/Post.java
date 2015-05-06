package domain;
import java.util.Scanner;
import java.util.ArrayList;

public class Post {
	private String titulo;
	private String contenido;
	private int puntaje = 0;
	private Usuario autor;
	private ArrayList<Comentario> comentarios = new ArrayList<Comentario>();
	
	public Post(String titulo, String contenido, Usuario autor) {
		this.titulo = titulo;
		this.contenido = contenido;
		this.autor = autor;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public int getPuntaje() {
		return puntaje;
	}
	
	public void mostrar() {
		int eleccion = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println(titulo);
			System.out.println(contenido);
			System.out.println("El post tiene " + puntaje + " puntos.");
			System.out.println("1. Comentar.");
			System.out.println("2. Ver comentarios.");
			System.out.println("3. Puntuar.");
			System.out.println("4. Volver.");
			System.out.print("¿Que desea hacer? ");
			eleccion = sc.nextInt();
			
			// Limpio el buffer.
			sc.nextLine();
			
			switch (eleccion) {
				case 1:
					comentar();
					break;
				case 2:
					verComentarios();
					break;
				case 3:
					puntuar();
					break;
			}
		} while (eleccion != 4);
	}
	
	public void verComentarios() {
		if (tieneComentarios()) {
			for (int i = 0; i < comentarios.size(); ++i) {
				System.out.println(comentarios.get(i).mostrar());
				System.out.println();
			}
		} else {
			System.out.println("El post no tiene comentarios.");
			System.out.println();
		}
	}
	
	public int puntuar() {
		int puntosDados = 0;
		
		if (this.autor != Pagina_Web.getInstance().getUsuario()) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Puntos a dar (1 a 10): ");
			puntosDados = sc.nextInt();
			if (puntosDados >= 1 && puntosDados <= 10) {
				puntaje += puntosDados;
			} else {
				puntosDados = 0;
				System.out.println("El puntaje debe estar entre 1 y 10.");
				System.out.println();
			}
		} else {
			System.out.println("No podes puntuarte a vos mismo.");
			System.out.println();
		}
		
		return puntosDados;
	}
	
	public int valor() {
		return puntaje + comentarios.size();
	}
	
	public void reconocer() {
		if (cantidadComentarios() > 5) {
			puntaje *= 2;
		}
	}
	
	public int cantidadComentariosLargos() {
		int cantidad = 0;
		for(Comentario comentario: comentarios) {
			if (comentario.esLargo()) {
				++cantidad;
			}
		}
		return cantidad;
	}
	
	public int cantidadComentarios() {
		return comentarios.size();
	}
	
	public boolean tieneComentarios() {
		return (!comentarios.isEmpty());
	}
	
	public boolean esInteresante() {
		return (puntaje > 50 || cantidadComentariosLargos() >= 20);
	}
	
	public Comentario comentar() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Escriba su comentario: ");
		String comentario = sc.nextLine();
		System.out.println();
		Comentario nuevoComentario = new Comentario(comentario);
		comentarios.add(nuevoComentario);
		return nuevoComentario;
	}
}
