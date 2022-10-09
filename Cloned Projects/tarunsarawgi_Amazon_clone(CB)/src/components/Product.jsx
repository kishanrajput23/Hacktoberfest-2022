import React from "react";
import Book from "./Book";

function Product() {
  return (
    <div class="product">
      <div class="product_row d-flex">
        <Book />
        <div class="product_body">
          <div class="product_description">
            <p>
              Kenwood kMix Stand Mixer for Baking, Stylish Kitchen Mixer with
              K-beater, Dough Hook and Whisk, 5 Litre Glass Bowl
            </p>
          </div>
          <div class="product_price">
            <p>
              &#8377; <span>2000</span>
            </p>
          </div>
          <ul class="rating d-flex">
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="far fa-star"></i>
            </li>
          </ul>
          <div class="product_img d-flex">
            <img src="assets/product2.jpg" alt="" />
          </div>
          <div class="button-cart d-flex">
            <a href="#">
              <button>Add To Busket</button>
            </a>
          </div>
        </div>
        <div class="product_body">
          <div class="product_description">
            <p>Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor</p>
          </div>
          <div class="product_price">
            <p>
              &#8377; <span>2500</span>
            </p>
          </div>
          <ul class="rating d-flex">
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="far fa-star"></i>
            </li>
          </ul>
          <div class="product_img d-flex">
            <img src="assets/product3.jpg" alt="" />
          </div>
          <div class="button-cart d-flex">
            <a href="#">
              <button>Add To Busket</button>
            </a>
          </div>
        </div>
      </div>

      <div class="product_row d-flex">
        <div class="product_body">
          <div class="product_description">
            <p>
              Amazon Echo (3rd generation) | Smart speaker with Alexa, Charcoal
              Fabric
            </p>
          </div>
          <div class="product_price">
            <p>
              &#8377; <span>3000</span>
            </p>
          </div>
          <ul class="rating d-flex">
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="far fa-star"></i>
            </li>
          </ul>
          <div class="product_img d-flex">
            <img src="assets/product4.jpg" alt="" />
          </div>
          <div class="button-cart d-flex">
            <a href="#">
              <button>Add To Busket</button>
            </a>
          </div>
        </div>
        <div class="product_body">
          <div class="product_description">
            <p>
              New Apple iPad Pro (12.9-inch, Wi-Fi, 128GB) - Silver (4th
              Generation)
            </p>
          </div>
          <div class="product_price">
            <p>
              &#8377; <span>10000</span>
            </p>
          </div>
          <ul class="rating d-flex">
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="far fa-star"></i>
            </li>
          </ul>
          <div class="product_img d-flex">
            <img src="assets/product5.jpg" alt="" />
          </div>
          <div class="button-cart d-flex">
            <a href="#">
              <button>Add To Busket</button>
            </a>
          </div>
        </div>
      </div>
      <div class="product_row d-flex">
        <div class="product_body">
          <div class="product_description">
            <p>
              Samsung LC49RG90SSUXEN 49' Curved LED Gaming Monitor - Super Ultra
              Wide Dual WQHD 5120 x 1440
            </p>
          </div>
          <div class="product_price">
            <p>
              &#8377; <span>25099</span>
            </p>
          </div>
          <ul class="rating d-flex">
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="fas fa-star"></i>
            </li>
            <li>
              <i class="far fa-star"></i>
            </li>
          </ul>
          <div class="product_img d-flex">
            <img src="assets/product6.jpg" alt="" />
          </div>
          <div class="button-cart d-flex">
            <a href="#">
              <button>Add To Busket</button>
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Product;
