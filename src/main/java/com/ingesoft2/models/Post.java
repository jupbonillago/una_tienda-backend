package com.ingesoft2.models;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")

public class Post {

    @Id
    @SequenceGenerator(name = "POST_POSTID_GENERATOR", sequenceName = "public.post_post_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "POST_POSTID_GENERATOR", strategy = GenerationType.SEQUENCE)
    @Column(name = "post_id")

    private Integer id;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private PersonDTO sellerId;

    @OneToOne
    @JoinColumn(name = "categoryId", nullable = true)
    private Category categoryId;

    @Column(name = "title")

    private String title;

    @Column(name = "product_name")

    private String productName;

    @Column(name = "image", nullable = true)

    private String image;

    @Column(name = "description")

    private String description;

    @Column(name = "total_review")
    private double totalReview;

    @Column(name = "price")
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "state")
    private Boolean state;

    @OneToOne(mappedBy = "cartshopItemPostId")
    private CartshopItem cartshopItem;

    @OneToMany(mappedBy = "postReviewed")
    private List<Review> reviews;

    @OneToMany(mappedBy = "postAsked")
    private List<Question> questions;

    public Post() {
        this.totalReview = 0;
        this.state = true;
    }

    public Post(String title, String productName, String image, String description, Integer price, Integer stock, Boolean state) {
    /*Solo agrego los atributos que usamos por ahora, quito los demas.*/
        this.title = title;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.totalReview = 0;
        this.price = price;
        this.stock = stock;
        this.state = state;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PersonDTO getSellerId() {
        return this.sellerId;
    }

    public void setSellerId(PersonDTO sellerId) {
        this.sellerId = sellerId;
    }

    public Category getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTotalReview() {
        return this.totalReview;
    }

    public void setTotalReview(double totalReview) {
        this.totalReview = totalReview;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    

    public Post id(Integer id) {
        this.id = id;
        return this;
    }

    public Post sellerId(PersonDTO sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public Post categoryId(Category categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public Post title(String title) {
        this.title = title;
        return this;
    }

    public Post productName(String productName) {
        this.productName = productName;
        return this;
    }

    public Post image(String image) {
        this.image = image;
        return this;
    }

    public Post description(String description) {
        this.description = description;
        return this;
    }

    public Post totalReview(double totalReview) {
        this.totalReview = totalReview;
        return this;
    }

    public Post price(Integer price) {
        this.price = price;
        return this;
    }

    public Post stock(Integer stock) {
        this.stock = stock;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Post)) {
            return false;
        }
        Post post = (Post) o;

        return Objects.equals(id, post.id) && Objects.equals(sellerId, post.sellerId)
                && Objects.equals(categoryId, post.categoryId) && Objects.equals(title, post.title)
                && Objects.equals(productName, post.productName) && Objects.equals(image, post.image)
                && Objects.equals(description, post.description) && totalReview == post.totalReview
                && Objects.equals(price, post.price) && Objects.equals(stock, post.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sellerId, categoryId, title, productName, image, description, totalReview, price,
                stock);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", sellerId='" + getSellerId() + "'" + ", categoryId='"
                + getCategoryId() + "'" + ", title='" + getTitle() + "'" + ", productName='" + getProductName() + "'"
                + ", image='" + getImage() + "'" + ", description='" + getDescription() + "'" + ", totalReview='"
                + getTotalReview() + "'" + ", price='" + getPrice() + "'" + ", stock='" + getStock() + "'" + "}";
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

}
