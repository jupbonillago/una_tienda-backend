package com.ingesoft2.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ingesoft2.mail.EmailService;
import com.ingesoft2.models.Cartshop;
import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.Post;
import com.ingesoft2.models.Transaction;
import com.ingesoft2.pojo.MyCartshopItemPOJO;
import com.ingesoft2.services.*;
import com.ingesoft2.services.CartshopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/transactions")
public class TransactionController {

  private TransactionService transactionService;
  private PersonService personService;
  private CartshopService cartshopService;
  private CartshopItemService cartshopItemService;
  private PostService postService;
  private EmailService emailService;

  @Autowired
  public TransactionController(TransactionService transactionService, PersonService personService,
      CartshopService cartshopService, CartshopItemService cartshopItemService, PostService postService,
      EmailService emailService) {
    this.transactionService = transactionService;
    this.personService = personService;
    this.cartshopItemService = cartshopItemService;
    this.postService = postService;
    this.cartshopService = cartshopService;
    this.emailService = emailService;
  }

  @GetMapping(value = { "/all" })
  public List<Transaction> getAllTransactions() {
    return transactionService.getTransactions();
  }

  @GetMapping(value = { "/my-transactions" })
  public List<Transaction> getTransactionsByPerson() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    PersonDTO person = personService.findByUsername(username);
    if (person == null) {
      return Collections.emptyList();
    } else {
      return transactionService.findByPerson(person);
    }
  }

  @GetMapping(value = { "/my-seller-transactions" })
  public List<Transaction> getMySellerHistorial() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Integer myId = personService.findByUsername(username).getId();// Con el id ubicare todas las transacciones como
                                                                  // vendedor de esa persona
    List<Transaction> allTransactions = transactionService.getTransactions();
    List<Transaction> myTransactions = new ArrayList<>();
    Transaction transaction;

    for (int i = 0; i < allTransactions.size(); i++) {
      /*
       * En este for busco dentro de todas las transacciones su respectivos post,
       * luego comparo la id de estos post con myId, si son iguales entonces es una de
       * mis transacciones como vendedor y se agrega a la lista que retorno
       */
      // Esto es super ineficiente pero por el momento lo dejo así D:
      transaction = allTransactions.get(i);
      if (transaction.getPostId().getId().equals(myId)) {
        myTransactions.add(transaction);
      }
    }
    return myTransactions;
  }

  @PostMapping("/buy")
  public ResponseEntity<Void> createTransaction() {

    Transaction transaction = new Transaction();

    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    Post post;

    PersonDTO person2 = personService.findByUsername(username);
    // Esta parte no require Pojos ya que no es información que entra o sale del
    // back
    Cartshop cartshop = cartshopService.findByPersonId(person2.getId());

    MyCartshopItemPOJO myCartShopItems = new MyCartshopItemPOJO();
    // Creo este objeto sencillamente para usar el metodo que luego me retorna la
    // lista.

    List<MyCartshopItemPOJO> items = myCartShopItems
        .myCartshopItemPOJO(cartshopItemService.findByCartshop(cartshop.getId()));

    if (items != null) {

      emailService.sendConfirmationEmailBuyer(person2, items);

      for (MyCartshopItemPOJO item : items) {
        post = postService.getByID(item.getCartshopItemPost().getId());
        PersonDTO seller = post.getSellerId();
        transaction.setBuyerPerson(person2);
        transaction.setPostId(post);
        transaction.setQuantity(item.getQuantity());
        transaction.setStockPrice(post.getPrice());

        // crea un transaction por cada item
        transactionService.insert(transaction);

        // elimina el cartshop item
        cartshopItemService.delete(item.getId());

        // actualiza el stock en post
        post.setStock(post.getStock() - transaction.getQuantity());
        postService.update(post);

        emailService.sendConfirmationEmailSeller(seller, item); 
      }
      
      return new ResponseEntity<>(HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

  }
}
