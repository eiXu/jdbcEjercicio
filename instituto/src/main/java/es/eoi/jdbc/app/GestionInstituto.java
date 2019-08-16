package es.eoi.jdbc.app;

import java.util.List;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.service.AlumnoService;
import es.eoi.jdbc.service.AlumnoServiceImpl;

public class GestionInstituto {

	public static void main(String[] args) {

		AlumnoService as = new AlumnoServiceImpl();

		System.out.println("-----------------");
		System.out.println("Mostrar todos los alumnos");
		List<Alumno> alumnos = as.findAll(); 						// MOSTRAR TODOS

		if (alumnos != null) {
			for (Alumno a : alumnos) {
				System.out.println(a);
			}
		}
		
		System.out.println("-----------------");
		System.out.println("Mostrar alumno con DNI = 555");
		System.out.println(as.findByDni("555")); 					// MOSTRAR 1

		System.out.println("-----------------");
		System.out.println("Creando un alumno con DNI = 666");						// CREAR UN ALUMNO
		if (as.create(new Alumno("666", "Uriel", "Cruz", 21))) {
			System.out.println("Se ha creado");
		} else {
			System.out.println("No se ha creado");
		}
		
		System.out.println("-----------------");
		System.out.println("Borrando del alumno con DNI = 666");	 	// BORRAR UN ALUMNO
		if (as.delete("666")) {
			System.out.println("Se ha borrado");
		} else {
			System.out.println("No se ha borrado");
		}
		
		System.out.println("-----------------");
		System.out.println("Modificando del alumno con DNI = 666");	 	// MODIFICAR UN ALUMNO
		if (as.update("555", "Adri", "Ortiz Cabezuelo", 26)) {
			System.out.println("Se ha modificadao");
		} else {
			System.out.println("No se ha modificado");
		}
		

	}

}
