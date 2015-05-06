package domain;
import java.util.Scanner;
import java.util.ArrayList;

public class Usuario {
	private String userName;
	private ArrayList<Post> posts = new ArrayList<Post>();
	
	public Usuario(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}
	
	public boolean tienePosts() {
		return (!posts.isEmpty());
	}
	
	public Post postear() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Titulo: ");
		String titulo = sc.nextLine();
		System.out.print("Contenido: ");
		String contenido = sc.nextLine();
		System.out.println();
		Post nuevoPost = new Post(titulo, contenido, this);
		posts.add(nuevoPost);
		return nuevoPost;
	}
	
	public int puntaje() {
		int puntaje = 0;
		for (Post post: posts) {
			puntaje += post.valor();
		}
		return puntaje;
	}
	
	public boolean esNuevo() {
		for (Post post: posts) {
			if (post.tieneComentarios()) {
				return false;
			}
		}
		
		return true;
	}
	
	public Post postMasExitoso() {
		int maximo = -1;
		Post postMasExitoso = null;
		
		for (Post post: posts) {
			if (post.valor() > maximo) {
				maximo = post.valor();
				postMasExitoso = post;
			}
		}
			
		return postMasExitoso;
	}
	
	public int cantidadPostsInteresantes() {
		int cantidad = 0;
		for(Post post: posts) {
			if (post.esInteresante()) {
				++cantidad;
			}
		}
		return cantidad;
	}
	
	public void verPerfil() {
		int eleccion = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Perfil de " + userName);
			if (esNuevo()) {
				System.out.print(userName + " es nuevo.");
			}
			System.out.println("Tiene " + puntaje() + " puntos y " + cantidadPostsInteresantes() + " posts interesantes.");
			if (tienePosts()) {
				System.out.println("Su post mas exitoso fue \"" + postMasExitoso().getTitulo() + "\".");
			}
			System.out.println("1. Ver posts.");
			System.out.println("2. Reconocer.");
			System.out.println("3. Volver.");
			System.out.print("¿Que desea hacer? ");
			eleccion = sc.nextInt();
			System.out.println();
			
			// Limpio el buffer.
			sc.nextLine();
			
			switch (eleccion) {
				case 1:
					verPosts();
					break;
				case 2:
					reconocer();
					break;
			}
		} while (eleccion != 3);
	}
	
	public void verPosts() {
		if (tienePosts()) {
			int eleccion = 0;
			Scanner sc = new Scanner(System.in);
			for (int i = 0; i < posts.size(); ++i) {
				System.out.println(i + ". " + posts.get(i).getTitulo());
			}
			System.out.print("Escriba el numero del post que desee ver: ");
			eleccion = sc.nextInt();
			System.out.println();
			posts.get(eleccion).mostrar();
		} else {
			System.out.println("El usuario " + userName + " no publico ningun post aun.");
		}
		System.out.println();
	}

	public void reconocer() {
		if (this != Pagina_Web.getInstance().getUsuario()) {
			for (Post post: posts) {
				post.reconocer();
			}
		} else {
			System.out.println("No podes reconocerte a vos mismo.");
			System.out.println();
		}
	}
}
