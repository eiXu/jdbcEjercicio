package es.eoi.jdbc.service;

import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public interface AlumnoService {

	public Alumno findByDni(String dni);
	public List<Alumno> findAll();
	public boolean create(Alumno newAlumno);
	public boolean delete(String dni);
	public boolean update(String dni, String nombre, String apellidos, Integer edad);
}
