package domain;
import java.util.ArrayList;
import java.util.Scanner;

public class Pagina_Web {
	private static Pagina_Web instance = null;
	private String nombre;
	private Usuario usuarioActual;
	private ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
	
	private Pagina_Web(String nombre) {
		this.nombre = nombre;
	}

	public static Pagina_Web getInstance(String nombre) {
		if (instance == null) {
			instance = new Pagina_Web(nombre);
		}
		
		return instance;
	}

	public static Pagina_Web getInstance() {
		return getInstance("");
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public Usuario getUsuario() {
		return usuarioActual;
	}

	private Usuario darUsuarioPorUserName(String userName) {
		for (Usuario usuario: usuarios) {
			if (usuario.getUserName().equals(userName)) {
				return usuario;
			}
		}
		
		return null;
	}
	
	private void agregarUsuario(String userName) {
		if (darUsuarioPorUserName(userName) == null) {
			usuarios.add(new Usuario(userName));
			System.out.println("El usuario " + userName + " fue registrado.");
		} else {		
			System.out.println("El usuario " + userName + " ya estaba registrado.");
		}
		
		System.out.println();
	}
	
	private void loggear(String userName) {
		Usuario usuarioPorUserName = darUsuarioPorUserName(userName);
		
		if (usuarioPorUserName != null) {
			usuarioActual = usuarioPorUserName;
			System.out.println("Bienvenido " + usuarioActual.getUserName() + ".");
		} else {
			System.out.println("El usuario " + userName + " no existe.");
		}
		
		System.out.println();
	}
	
	private void verPerfil(String userName) {
		Usuario usuario = darUsuarioPorUserName(userName);
		if (usuario != null) {
			System.out.println();
			usuario.verPerfil();
		} else {
			System.out.println("El usuario " + userName + " no existe.");
			System.out.println();
		}
	}
	
	private void cerrarSesion() {
		usuarioActual = null;
	}
		
	public void run() {
		int eleccion = 0;
		Scanner sc = new Scanner(System.in);
		
		do {
			while (getUsuario() == null) {
				System.out.println(getNombre());
				System.out.println("1. Crear usuario.");
				System.out.println("2. Loggearse.");
				System.out.println("3. Salir.");
				System.out.print("¿Que desea hacer? ");
				eleccion = sc.nextInt();
				
				// Limpio el buffer.
				sc.nextLine();
				
				switch (eleccion) {
					case 1:
						System.out.print("Nombre de usuario: ");
						agregarUsuario(sc.nextLine());
						break;
					case 2:
						System.out.print("Nombre de usuario: ");
						loggear(sc.nextLine());
						break;
					case 3:
						System.exit(0);
				}
			}
			
			do {				
				System.out.println("Pagina principal de " + getNombre());
				System.out.println("1. Crear un post.");
				System.out.println("2. Ir a un perfil.");
				System.out.println("3. Cerrar sesion.");
				System.out.print("¿Que desea hacer? ");
				eleccion = sc.nextInt();
				
				// Limpio el buffer.
				sc.nextLine();
				
				switch (eleccion) {
					case 1:
						getUsuario().postear();
						break;
					case 2:
						System.out.print("Nombre de usuario: ");
						verPerfil(sc.nextLine());
						break;
					case 3:
						cerrarSesion();
						System.out.println();
						break;
				}
			} while (eleccion != 3);
		} while (true);
	}
}
