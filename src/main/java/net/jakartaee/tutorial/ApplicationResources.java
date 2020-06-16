package net.jakartaee.tutorial;

import javax.servlet.ServletContext;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Context;

import org.glassfish.jersey.server.ResourceConfig;

import net.jakartaee.tutorial.data.SQLiteDatabase;
import net.jakartaee.tutorial.exceptions.DatabaseException;
import net.jakartaee.tutorial.exceptions.DatabaseExistsException;

@ApplicationPath("api")
public class ApplicationResources extends ResourceConfig {

	public ApplicationResources(@Context ServletContext servletContext) {
		System.out.println("Application init.");
		
		try {
			SQLiteDatabase.createDatabase();
			System.out.println("Creating JEE Tutorial database: "+SQLiteDatabase.DB_PATH);
		} catch (DatabaseExistsException e) {
			System.out.println("Opening JEE Tutorial database: "+SQLiteDatabase.DB_PATH);
		} catch (DatabaseException e) {
			System.out.println("Error Creating JEE Tutorial database: "+SQLiteDatabase.DB_PATH);
		}	
	}

	
}
