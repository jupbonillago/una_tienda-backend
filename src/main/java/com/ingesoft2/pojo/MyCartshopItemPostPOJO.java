package com.ingesoft2.pojo;

import com.ingesoft2.models.Post;

public class MyCartshopItemPostPOJO {
    /*Lo que hacemos en este POJO es obtener la información de un post que necesitemos para mostar en el item del carrito,
    por ahora solo es el precio del post, debido a que solo crearemos esta clase en situaciones muy particulares,
    el constructor lo dejo de esa manera.*/
    private String title;
    private Integer price;
    private String image;
    private Integer stock;
    private Integer id;

    public MyCartshopItemPostPOJO myCartshopItemPostPOJO(Post post) {
        MyCartshopItemPostPOJO post2 = new MyCartshopItemPostPOJO();
        post2.setTitle(post.getTitle());
        post2.setPrice(post.getPrice());
        post2.setImage(post.getImage());
        post2.setStock(post.getStock());
        post2.setId(post.getId());
        return post2;
    }
    
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

}
