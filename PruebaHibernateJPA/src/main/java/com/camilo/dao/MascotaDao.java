package com.camilo.dao;

import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import com.camilo.aplicacion.JPAUtil;
import com.camilo.entidades.Mascota;

import controlador.Coordinador;



public class MascotaDao {
	private Coordinador micoordinador;
	
	
	EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
	
	
	public String registrarMascota (Mascota miMascota) {
		String res="";
	
			
			entityManager.getTransaction().begin();
			entityManager.persist(miMascota);
			entityManager.getTransaction().commit();
			
			res = "Mascota Regsitrada";
		
	
		return res;
	}
	
	
	public Mascota consultarMascota( Long idMascota) {
		
		
		Mascota miMascota = entityManager.find(Mascota.class,idMascota);
		
		if (miMascota!=null) {
			
			return miMascota;
		} else {
			return null;
		}
	}
	
	
	
	public List<Mascota> ConsultarListaMascotas (){
		
		List<Mascota> listaMascotas  = new ArrayList<Mascota>();
		Query query = entityManager.createQuery("SELECT m FROM Mascota m");
		listaMascotas = query.getResultList();
		
		return listaMascotas;
	}
	
	
	public List<Mascota> ConsultarListaMascotasPorSexo (String sexo){
		
		List<Mascota> listaMascotas  = new ArrayList<Mascota>();
		Query query = entityManager.createQuery("SELECT m FROM Mascota m WHERE m.sexo = : sexoMascota");
		query .setParameter("sexoMascota", sexo);
		listaMascotas = query.getResultList();
		
		return listaMascotas;
	}
	
	
	
	public String actualizarMascota(Mascota miMascota) {
		
		entityManager.getTransaction().begin();
		entityManager.merge(miMascota);
		entityManager.getTransaction().commit();
		
		String  res = "Mascota Actualizada";
		return res;		
	}
	
	
	
	public String eliminarMascota(Mascota miMascota) {
		
		entityManager.getTransaction().begin();
		entityManager.remove(miMascota);
		entityManager.getTransaction().commit();
		
		String  res = "Mascota Eliminada";
		return res;		
	}


	public void close() {
		
		entityManager.close();
		JPAUtil.shutdown();
	}


	public void setCoordinador(Coordinador miCoordinador) {
		this.micoordinador=miCoordinador;
		
	}
	
	
	

}
