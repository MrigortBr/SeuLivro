package service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.Period;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import model.DAO;

public class Essentials {

	public byte[] tranformImageTo64(Part filePart) throws IOException, ServletException {
		byte[] imageBytes = null;
    	//Pega o nome extensão enviada pelo usuario
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        
        //Cria um objeto File com os dados passados anteriormente
        File file = new File("C:/server/userPictures", fileName);
        
        //Cria a imagem 64
        InputStream fileContent = filePart.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
        	   outputStream.write(buffer, 0, bytesRead);
        }
        imageBytes = outputStream.toByteArray();
        
        return imageBytes;
	}
	
    public int calculateAge(String dateOfBirth) {
        LocalDate today = LocalDate.now();
        LocalDate birthDate = LocalDate.parse(dateOfBirth);

        Period period = Period.between(birthDate, today);

        return period.getYears();
    }


}
