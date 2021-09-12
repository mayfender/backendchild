package com.may.ple.backendchild.action;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.StreamingOutput;

import org.apache.log4j.Logger;

import com.may.ple.backendchild.entity.Customer;

@Path("msg")
public class MyMessage {
	private static final Logger LOG = Logger.getLogger(MyMessage.class.getName());

	@GET
	@Path("/test1")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getMessage() {
		LOG.info("test1");
		Customer customer = new Customer();
		customer.setName("mayfender");
		customer.setAge(10);
        return customer;
    }

	@GET
	@Path("/test2")
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage2() {
		LOG.info("test2");
		return "testing";
	}

	@GET
	@Path("/exportByCriteria")
	public Response exportByCriteria() throws Exception {
		try {
			String fileName = "report.pdf";

			ResponseBuilder response = Response.ok(new StreamingOutput() {

				@Override
				public void write(OutputStream out) throws IOException, WebApplicationException {
					try {
						String str = "test";
						out.write(str.getBytes());
					} catch (Exception e) {
						LOG.error(e.toString(), e);
					} finally {
						try { if(out != null) out.close(); } catch (Exception e2) {}
					}
				}

			});

//			response.header("fileName", new URLEncoder().encode(fileName, Charset.defaultCharset()));
			response.header("content-disposition","attachment; filename = myfile.pdf");

			return response.build();
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			throw e;
		}
	}

}
