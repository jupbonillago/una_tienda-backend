package com.ingesoft2.pojo;

import java.util.ArrayList;
import java.util.List;

import com.ingesoft2.models.CartshopItem;

public class MyCartshopItemPOJO {
    /*Lo que hacemos en este POJO es guardae toda la información necesaria que se pidio en el front para luego devolverla, notese
    que esta clase usa al POJO  MyCartshopItemPostPOJO, el caso del constructor es lo mismo que el POJO Mycartshoppost, y pues con solo
    ingresarle la lista que ya retornaba Juan Pablo, me crea la lista nueva con todos los datos <3*/
    private Integer id;
    private MyCartshopItemPostPOJO cartshopItemPost;
    private Integer quantity;
    private Integer cartshopId;

    public List<MyCartshopItemPOJO> myCartshopItemPOJO(List<CartshopItem> cartShopItems) {

        List<MyCartshopItemPOJO> cartShopItems2= new ArrayList<>();
        MyCartshopItemPostPOJO cartShopItemsPost = new MyCartshopItemPostPOJO();
        for(int i = 0; i < cartShopItems.size();i++){

            cartShopItems2.add( new MyCartshopItemPOJO() );

        }
        for(int i = 0; i < cartShopItems.size();i++){
            cartShopItems2.get(i).setId(cartShopItems.get(i).getId());
            cartShopItems2.get(i).setQuantity(cartShopItems.get(i).getQuantity());
            cartShopItems2.get(i).setCartshopId(cartShopItems.get(i).getCartshop().getId());

            cartShopItems2.get(i).setQuantity(cartShopItems.get(i).getQuantity());
            cartShopItems2.get(i).setCartshopItemPost(cartShopItemsPost.myCartshopItemPostPOJO(cartShopItems.get(i).getCartshopItemPostId()));
            /* El primero le asigna la cantidad y el segundo los atributos propios de post, especificamente titulo y precio*/
        }

        return cartShopItems2;


    }

    public MyCartshopItemPostPOJO getCartshopItemPost() {
        return cartshopItemPost;
    }

    public void setCartshopItemPost(MyCartshopItemPostPOJO cartshopItemPost) {
        this.cartshopItemPost = cartshopItemPost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getCartshopId() {
        return this.cartshopId;
    }

    public void setCartshopId(Integer id) {
        this.cartshopId = id;
    }

    
    
}
