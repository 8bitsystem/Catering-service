package com.Catering_Server.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Catering_Server.Entity.Venue;
import com.Catering_Server.Repository.VenueRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import jakarta.mail.MessagingException;

@Service
public class PdfGenerationService {

	@Autowired
	private VenueRepository venueRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	public byte[] generatePdfFromCustomerId(String htmlContent, String email) throws IOException, MessagingException {
		// Remove unnecessary characters from the HTML content
		htmlContent.replace("\n", "").trim();

		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				PdfWriter writer = new PdfWriter(outputStream)) {

			PdfDocument pdfDocument = new PdfDocument(writer);
			ConverterProperties converterProperties = new ConverterProperties();
			HtmlConverter.convertToPdf(htmlContent, pdfDocument, converterProperties);

			byte[] pdfBytes = outputStream.toByteArray();

			// Send email with attached PDF
			emailSenderService.sendEmailWithAttachment(email, "Venue Details",
					"Please find attached the venue details PDF.", pdfBytes, "VenueDetails.pdf");

			return pdfBytes;

		} catch (IOException | MessagingException e) {
			throw e;
		}
	}

}
