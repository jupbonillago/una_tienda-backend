package com.ingesoft2.emailTests;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import com.ingesoft2.mail.EmailBody;
import com.ingesoft2.mail.EmailService;
import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.pojo.MyCartshopItemPOJO;
import com.ingesoft2.pojo.MyCartshopItemPostPOJO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailTest {

  @Autowired
  private EmailService emailService;
  
  @Test
  void sendEmail() {
    EmailBody myEmail = new EmailBody();
    myEmail.setEmail("csolanoc@unal.edu.co");
    myEmail.setContent("<h3>this is an email test</h3> <img src=\"cid:logo\">");
    myEmail.setSubject("testing email feature");

    assertDoesNotThrow(() -> emailService.sendEmail(myEmail));
  }

  @Test
  void sendEmailWithoutReceiver(){
    EmailBody myEmail = new EmailBody();
    myEmail.setContent("<h3>this is an email test</h3> <img src=\"cid:logo\">");
    myEmail.setSubject("testing email feature");

    assertThrows(IllegalArgumentException.class, () -> emailService.sendEmail(myEmail));
  }

  @Test
  void sendConfirmationEmailBuyer(){

    //Buyer person
    PersonDTO buyer = new PersonDTO();
    buyer.setId(1);
    buyer.setUsername("test1");
    buyer.setPassword("qwertyuiop");
    buyer.setEmail("csolanoc@unal.edu.co");

    //Product to sell
    MyCartshopItemPostPOJO postPOJO = new MyCartshopItemPostPOJO();
    postPOJO.setTitle("test product");
    postPOJO.setPrice(10000);
    postPOJO.setStock(10);
    postPOJO.setId(1);

    MyCartshopItemPOJO item = new MyCartshopItemPOJO();
    item.setCartshopItemPost(postPOJO);
    item.setQuantity(1);

    List<MyCartshopItemPOJO> items = new ArrayList<>();
    items.add(item);

    assertDoesNotThrow(() -> emailService.sendConfirmationEmailBuyer(buyer, items));
  }

  @Test
  void sendConfirmationEmailSeller(){

    //Seller person
    PersonDTO seller = new PersonDTO();
    seller.setId(1);
    seller.setUsername("test1");
    seller.setPassword("qwertyuiop");
    seller.setEmail("csolanoc@unal.edu.co");

    //Product to sell
    MyCartshopItemPostPOJO postPOJO = new MyCartshopItemPostPOJO();
    postPOJO.setTitle("test product");
    postPOJO.setPrice(10000);
    postPOJO.setStock(10);
    postPOJO.setId(1);

    MyCartshopItemPOJO item = new MyCartshopItemPOJO();
    item.setCartshopItemPost(postPOJO);
    item.setQuantity(1);

    assertDoesNotThrow(() -> emailService.sendConfirmationEmailSeller(seller, item));
  }
}
