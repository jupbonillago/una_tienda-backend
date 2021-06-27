package com.ingesoft2.controller;

import com.ingesoft2.models.Cartshop;
import com.ingesoft2.models.PersonDTO;
import com.ingesoft2.models.CartshopItem;
import com.ingesoft2.pojo.MyCartshopItemPOJO;
import com.ingesoft2.services.CartshopItemService;
import com.ingesoft2.services.CartshopService;
import com.ingesoft2.services.PostService;
import com.ingesoft2.services.PersonService;
import com.ingesoft2.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/shopping-cart")
public class CartshopController {

  private PersonService personService;

  private CartshopService cartshopService;

  private CartshopItemService cartshopItemService;

  private PostService postService;

  @Autowired
  public CartshopController(PersonService personService, CartshopService cartshopService,
      CartshopItemService cartshopItemService, PostService postService) {
    this.personService = personService;
    this.cartshopService = cartshopService;
    this.cartshopItemService = cartshopItemService;
    this.postService = postService;
  }

  @GetMapping(value = { "/items" })
  public List<MyCartshopItemPOJO> getItems() {
    String username = SecurityContextHolder.getContext().getAuthentication().getName();
    PersonDTO person2 = personService.findByUsername(username);
    // Esta parte no require Pojos ya que no es información que entra o sale del
    // back
    Cartshop cartshop = cartshopService
        .findByPersonId(person2.getId()); /*
                                           * Falta controlar el caso en el que un usuario no tenga carrito O hacer que
                                           * siempre que se crean tengan carrito (seria lo ideal) entonces mas que nada
                                           * hay que controlar cuando tengan 0 items en el carrito
                                           */
    MyCartshopItemPOJO myCartShopItems = new MyCartshopItemPOJO();
    // Creo este objeto sencillamente para usar el metodo que luego me retorna la
    // lista.

    // List<Cartshop_item> items =
    return myCartShopItems.myCartshopItemPOJO(cartshopItemService.findByCartshop(cartshop.getId()));
  }

  @DeleteMapping({ "/delete-item" })
  public ResponseEntity<Void> deleteCSItem(@RequestParam("id") Integer id) {

    if (id > 0) {
      if (cartshopItemService.delete(id)) {
        return new ResponseEntity<>(HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }
    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  @PutMapping("/update")
  public ResponseEntity<Void> updateItem(@RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {

    CartshopItem cartshopItem = cartshopItemService.getByID(id);

    if (cartshopItem != null) {
      cartshopItem.setQuantity(quantity);
      cartshopItemService.update(cartshopItem);
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @PostMapping("/add")
  public ResponseEntity<Void> addItem(@RequestParam("quantity") Integer quantity, @RequestParam("postId") Integer postId) {

    if (quantity != 0 && postId != null) {
      CartshopItem item2 = new CartshopItem();
      item2.setQuantity(quantity);

      String username = SecurityContextHolder.getContext().getAuthentication().getName();
      PersonDTO person2 = personService.findByUsername(username);
      // Esta parte no require Pojos ya que no es información que entra o sale del
      // back
      Cartshop cartshop = cartshopService.findByPersonId(person2.getId());

      item2.setCartshop(cartshop);

      Post post = postService.getByID(postId);
      item2.setCartshopItemPostId(post);

      cartshopItemService.insert(item2);
      return new ResponseEntity<>(HttpStatus.CREATED);

    } else {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

}