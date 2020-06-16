package net.jakartaee.tutorial.jee102.services;

import java.util.List;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import net.jakartaee.tutorial.data.BookDAO;
import net.jakartaee.tutorial.exceptions.DatabaseException;
import net.jakartaee.tutorial.exceptions.NotDeletedException;
import net.jakartaee.tutorial.exceptions.NotFoundException;
import net.jakartaee.tutorial.model.Book;



@Path("book")
public class BookResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
 		try {
			List<Book> books = new BookDAO().getBooks();
	        return Response.ok(books, MediaType.APPLICATION_JSON).build();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(e.getErrorResponse()).build();
		}
    }

    @GET
    @Path("{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTagById( @PathParam("bookId") Long id) {				// Book has bookID as Long to handle SQL  NULL
		try {
			Book book = new BookDAO().getBookById(id);
	        return Response.ok(book, MediaType.APPLICATION_JSON).build();
		} catch (NotFoundException e) {
			return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).entity(e.getErrorResponse()).build();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(e.getErrorResponse()).build();
		}
    }   

    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTag(Book book) {
    	try {
			new BookDAO().insertBook(book);;
			return Response.ok(null, MediaType.APPLICATION_JSON).build();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(e.getErrorResponse()).build();
		}
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTag(Book book) {
    	try {
			new BookDAO().updateBook(book);
			return Response.ok(book, MediaType.APPLICATION_JSON).build();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(e.getErrorResponse()).build();
		}
    }
    
    @DELETE
    @Path("{bookId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTagById( @PathParam("bookId") Integer id) {
		try {
			new BookDAO().deleteBook(id);
	        return Response.ok(null, MediaType.APPLICATION_JSON).build();
		} catch (DatabaseException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(e.getErrorResponse()).build();
		}
    }   
    
}
