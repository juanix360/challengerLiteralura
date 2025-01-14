//El paquete service suele contener la lógica de negocio de la aplicación.
package com.alura.literalura.service;

//Importa el modelo de datos Libro, que representa la entidad principal que se manipula en este servicio.
import com.alura.literalura.model.Libro;
//Importa el repositorio encargado de interactuar con la base de datos para la entidad Libro
import com.alura.literalura.repository.LibroRepository;
//Permite la inyección de dependencias de Spring
import org.springframework.beans.factory.annotation.Autowired;
//Marca esta clase como un servicio en el contenedor de Spring
import org.springframework.stereotype.Service;

//Representa listas de objetos (en este caso, listas de libros)
import java.util.List;
//Proporciona una forma segura de manejar valores que pueden ser null
import java.util.Optional;

@Service
//Indica que esta clase es un componente del tipo servicio.
//Los servicios contienen la lógica de negocio de la aplicación
//Spring gestiona esta clase como un bean dentro del contenedor
public class LibroService {


    //Uso clase principal
    //private LibroService libroService;

    @Autowired
    //Permite que Spring inyecte automáticamente una instancia de LibroRepository
    //LibroRepository es un componente de tipo Repositorio, encargado de las operaciones con la base de datos.

    //Define una dependencia hacia el repositorio de libros.
    private LibroRepository libroRepository;

    //Función: Devuelve una lista con todos los libros almacenados en la base de datos.
    public List<Libro> listarLibros(){
        //Metodo heredado de JpaRepository que recupera todos los registros de la tabla asociada a la entidad Libro.
        return libroRepository.findAll();

        //Uso clase Principal
//        libroService.listarLibros().forEach(libro -> {
//            System.out.println("------LIBRO--------");
//            System.out.println("Título: " + libro.getTitulo());
//            System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
//            System.out.println("Idioma: " + libro.getIdioma());
//            System.out.println("Número de descargas: " + libro.getNumeroDescargas());
//        })
    }

    //Función: Devuelve una lista de libros filtrados por idioma
    public List<Libro> listarLibrosPorIdioma(String idioma){
        //Metodo personalizado del repositorio que busca libros según el idioma
        return libroRepository.findByIdioma(idioma);

        //Uso clase Principal
//        libroService.listarLibrosPorIdioma(idioma).forEach(libro -> {
//            System.out.println("------LIBRO--------");
//            System.out.println("Título: " + libro.getTitulo());
//            System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
//            System.out.println("Idioma: " + libro.getIdioma());
//            System.out.println("Número de descargas: " + libro.getNumeroDescargas());
//        });
    }

    //Función: Guarda un nuevo libro en la base de datos.
    public Libro crearLibro(Libro libro) {
        //Metodo de JpaRepository para guardar o actualizar una entidad.
        //Si el libro no tiene un ID, se crea un nuevo registro. Si tiene un ID existente, se actualiza.
        return libroRepository.save(libro);

        //Uso en la clase Principal
        //libroService.crearLibro(libro);
    }

    //Función: Recupera un libro específico por su ID
    //Optional<Libro>: Envuelve la entidad Libro en un contenedor seguro para evitar problemas de null
    public Optional<Libro> obtenerLibroPorId(Long id){
        //Metodo de JpaRepository para buscar un libro por su ID.
        return libroRepository.findById(id);
    }

    //Función: Recupera un libro específico por su título, ignorando mayúsculas/minúsculas.
    public Optional<Libro> obtenerLibroPorTitulo(String titulo){
        //Metodo personalizado del repositorio que realiza una búsqueda insensible a mayúsculas/minúsculas
        return libroRepository.findByTituloIgnoreCase(titulo);
    }

    //Función: Actualiza los datos de un libro existente.
    public Libro actualizarLibro(Long id, Libro libroDetalles){

        //Busca el libro por su ID. Si no existe, lanza una excepción personalizada (RuntimeException).
        Libro libro = libroRepository.findById(id).orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        //Actualiza los atributos del libro con los datos proporcionados en libroDetalles
        libro.setTitulo(libroDetalles.getTitulo());
        libro.setTitulo(libroDetalles.getIdioma());
        libro.setNumeroDescargas(libroDetalles.getNumeroDescargas());
        libro.setAutor(libroDetalles.getAutor());
        //Guarda los cambios del libro en la base de datos.
        return libroRepository.save(libro);
    }

    //Función: Elimina un libro específico de la base de datos.
    public void  eliminarLibro(Long id){
        //Metodo de JpaRepository que elimina un registro de la base de datos según su ID.
        libroRepository.deleteById(id);
    }

}

//Resumen del Servicio

//Este servicio se encarga de realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) sobre la entidad Libro
//Utiliza el repositorio LibroRepository para interactuar con la base de datos.
//Maneja lógica adicional, como validaciones, actualizaciones específicas y excepciones, antes de interactuar con el repositorio.
//Implementa métodos personalizados para necesidades específicas, como listar libros por idioma o buscar por título.
