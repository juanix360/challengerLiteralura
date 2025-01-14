//Define el paquete en el que se encuentra esta clase
package com.alura.literalura;
//Importa la clase Principal
import com.alura.literalura.principal.Principal;
//Permite la inyección de dependencias de Spring de forma automática.
import org.springframework.beans.factory.annotation.Autowired;
//Las clases que la implementan pueden ejecutar lógica personalizada al inicio de la aplicación.
import org.springframework.boot.CommandLineRunner;
//Clase de utilidad principal para iniciar una aplicación Spring Boot.
import org.springframework.boot.SpringApplication;
//Configura automáticamente componentes de Spring Boot.
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Combina varias anotaciones de configuración
// // @Configuration: Marca esta clase como fuente de beans para el contenedor de Spring.
// // @EnableAutoConfiguration: Activa la configuración automática de Spring Boot para configurar componentes según las dependencias en el classpath.
// // @ComponentScan: Permite que Spring escanee y detecte componentes, servicios y controladores en el paquete actual y sus subpaquetes.

//implements CommandLineRunner: Indica que esta clase sobrescribirá el metodo run(String... args) para ejecutar lógica personalizada al iniciar la aplicación.
//fdfd
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	//Inyecta automáticamente una instancia de la clase Principal desde el contenedor de Spring.
	//Esto elimina la necesidad de crear manualmente un objeto Principal

	//Declara una dependencia de tipo Principal como final, indicando que debe ser inicializada en el constructor y no se puede cambiar después.
	private final Principal principal;

	//Constructor para inicializar el atributo principal
	public LiteraluraApplication(Principal principal){
		//Como el atributo es final, es obligatorio asignarle un valor en el constructor.
		this.principal = principal;
	}

	//Punto de entrada de la aplicación.
	public static void main(String[] args) {
		//Metodo para iniciar la aplicación Spring Boot
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	//Sobrescribe el metodo run de la interfaz CommandLineRunner.

	//Este metodo se ejecuta automáticamente después de que la aplicación Spring Boot se haya iniciado.
	public void run(String... args) throws Exception{
		//Llama al metodo mostrarMenu() de la clase Principal
		principal.mostrarMenu();
	}
}

//Resumen del Flujo

//Al ejecutar la aplicación, el metodo main invoca SpringApplication.run

//Spring Boot
//// Configura automáticamente los componentes de la aplicación
//// Detecta que LiteraluraApplication implementa CommandLineRunner

//Una vez que Spring Boot completa la configuración, llama al metodo run de LiteraluraApplication

//En el metodo run, se delega a principal.mostrarMenu() la responsabilidad de mostrar el menú principal y manejar la lógica inicial