package es.eoi.jdbc.service;

import java.util.List;

import es.eoi.jdbc.entity.Alumno;
import es.eoi.jdbc.repository.AlumnoRepository;
import es.eoi.jdbc.repository.AlumnoRepositoryImpl;

public class AlumnoServiceImpl implements AlumnoService {

	AlumnoRepository ar;

	public AlumnoServiceImpl() {
		ar = new AlumnoRepositoryImpl();
	}

	public Alumno findByDni(String dni) {
		return ar.findByDni(dni);
	}

	public List<Alumno> findAll() {
		return ar.findAll();
	}

	public boolean create(Alumno newAlumno) {
		return ar.create(newAlumno);
	}

	public boolean delete(String dni) {
		return ar.delete(dni);
	}

	public boolean update(String dni, String nombre, String apellidos, Integer edad) {
		return ar.update(dni, nombre, apellidos, edad);
	}

}
