package com.ingesoft2.mail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.pojo.MyCartshopItemPOJO;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements EmailPort {

  private JavaMailSender sender;

  public EmailService(JavaMailSender sender) {
    this.sender = sender;
  }

  @Override
  public boolean sendEmail(EmailBody emailBody) {
    return sendEmailTool(emailBody.getContent(), emailBody.getEmail(), emailBody.getSubject());
  }

  private boolean sendEmailTool(String textMessage, String email, String subject) {
    boolean send = false;
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    MimeMultipart multipart = new MimeMultipart("related");
    try {
      
      MimeBodyPart textBody = new MimeBodyPart();
      textBody.setContent(textMessage, "text/html");
      multipart.addBodyPart(textBody);

      MimeBodyPart imageBody = new MimeBodyPart();

      java.io.InputStream inputStream = this.getClass().getResourceAsStream("../static/logo.png");

      ByteArrayDataSource ds = new ByteArrayDataSource(inputStream, "image/jpg");

      
      imageBody.setDataHandler(new DataHandler(ds));
      imageBody.setHeader("Content-ID", "<logo>");

      imageBody.setDisposition(javax.mail.Part.INLINE);

      multipart.addBodyPart(imageBody);


      helper.setTo(email);
      message.setContent(multipart);
      helper.setSubject(subject);
      sender.send(message);
      send = true;
    } catch (MessagingException|IOException e) {
      //Handle exception
    }
    return send;
  }

  public boolean sendConfirmationEmailBuyer(PersonDTO buyer, List<MyCartshopItemPOJO> items){
    try {
      List<String> itemsList = new ArrayList<>();
      for (MyCartshopItemPOJO item : items) {
        String temp = "<h3>Producto: " + item.getCartshopItemPost().getTitle() + "</h3>" +
                      "<h4>Cantidad: " + item.getQuantity() + "</h4>" + 
                      "<h4>Costo: " + (item.getQuantity() * item.getCartshopItemPost().getPrice()) + "</h4>";
        String base = "<div>" + temp + "</div>";
        itemsList.add(base);
      }
      StringBuilder sb = new StringBuilder();

      for(String str: itemsList){
        sb.append(str);
        sb.append("<hr>");
      }
      String itemsResult = sb.toString();

      String email = buyer.getEmail();
      String content = "<div style=\"background-color: #2c3e50; color: white;\"><img src=\"cid:logo\" alt=\"logo.png\" style=\"height: 100px;\"><div><h3>Confirmación de compras.</h3><h4>¡Hola!</h4><p>Las compras han sido verificadas y aquí tienes la información de tus compras realizadas:\n</p><hr>" + itemsResult + "</div></div>";
      String subject = "UNTienda: Confirmación de compras.";
      return this.sendEmail(new EmailBody(email, content, subject));
    } catch (Exception e) {
      // TODO: handle exception
    }
    return false;
  }

  public boolean sendConfirmationEmailSeller(PersonDTO seller, MyCartshopItemPOJO item){
    try {
      

      String itemInfo = "<h3>Producto vendido: " + item.getCartshopItemPost().getTitle() + "</h3>" +
                      "<h4>Cantidad: " + item.getQuantity() + "</h4>" + 
                      "<h4>Costo total: " + (item.getQuantity() * item.getCartshopItemPost().getPrice()) + "</h4>";

      String email = seller.getEmail();
      String content = "<div style=\"background-color: #2c3e50; color: white;\"><img src=\"cid:logo\" alt=\"logo.png\" style=\"height: 100px;\"><div><h3>Confirmación de compras.</h3><h4>¡Hola!</h4><p>Un usuario adquirió un producto tuyo, aquí tienes la información más detallada:\n</p><hr>" + itemInfo + "</div></div>";
      String subject = "UNTienda: Confirmación de venta.";

      return this.sendEmail(new EmailBody(email, content, subject));
    } catch (Exception e) {
      // TODO: handle exception
    }
    return false;
  }
}
